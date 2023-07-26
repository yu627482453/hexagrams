package cn.bipher.hexagrams.redis.lock;

import cn.bipher.hexagrams.common.core.lock.DistributedLock;
import cn.bipher.hexagrams.common.core.constant.LockConstant;
import cn.bipher.hexagrams.common.core.lock.LockException;
import cn.bipher.hexagrams.common.core.lock.ZLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redisson分布式锁实现，基本锁功能的抽象实现
 * 本接口能满足绝大部分的需求，高级的锁功能，请自行扩展或直接使用原生api
 * https://gitbook.cn/gitchat/activity/5f02746f34b17609e14c7d5a
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:07
 */
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnProperty(prefix = "zlt.lock", name = "lockerType", havingValue = "REDIS", matchIfMissing = true)
public class RedissonDistributedLock implements DistributedLock {
    @Resource
    private RedissonClient redisson;

    private ZLock getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redisson.getFairLock(LockConstant.LOCK_KEY_PREFIX + ":" + key);
        } else {
            lock =  redisson.getLock(LockConstant.LOCK_KEY_PREFIX + ":" + key);
        }
        return new ZLock(lock, this);
    }

    @Override
    public ZLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        ZLock zLock = getLock(key, isFair);
        RLock lock = (RLock)zLock.getLock();
        lock.lock(leaseTime, unit);
        return zLock;
    }

    @Override
    public ZLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws InterruptedException {
        ZLock zLock = getLock(key, isFair);
        RLock lock = (RLock)zLock.getLock();
        if (lock.tryLock(waitTime, leaseTime, unit)) {
            return zLock;
        }
        return null;
    }

    @Override
    public void unlock(Object lock) {
        if (lock != null) {
            if (lock instanceof RLock) {
                RLock rLock = (RLock)lock;
                if (rLock.isLocked()) {
                    rLock.unlock();
                }
            } else {
                throw new LockException("requires RLock type");
            }
        }
    }
}