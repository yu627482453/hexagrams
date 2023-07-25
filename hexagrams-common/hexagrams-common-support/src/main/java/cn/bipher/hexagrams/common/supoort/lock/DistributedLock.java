package cn.bipher.hexagrams.common.supoort.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁顶级接口
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:14
 */
public interface DistributedLock {

    /**
     * 获取锁，如果获取不成功则一直等待直到lock被获取
     *
     * @param key       锁的key
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit      {@code leaseTime} 参数的时间单位
     * @param isFair    是否公平锁
     * @return 锁对象
     */
    ZLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) throws Exception;

    /**
     * 获取锁，如果获取不成功则一直等待直到lock被获取
     *
     * @param key       锁的key
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit      {@code leaseTime} 参数的时间单位
     * @return 锁对象
     */
    default ZLock lock(String key, long leaseTime, TimeUnit unit) throws Exception {
        return this.lock(key, leaseTime, unit, false);
    }

    /**
     * 获取锁，如果获取不成功则一直等待直到lock被获取
     *
     * @param key    锁的key
     *               如果leaseTime为-1，则保持锁定直到显式解锁
     * @param isFair 是否公平锁
     * @return 锁对象
     */
    default ZLock lock(String key, boolean isFair) throws Exception {
        return this.lock(key, -1, null, isFair);
    }

    /**
     * 获取锁，如果获取不成功则一直等待直到lock被获取
     *
     * @param key 锁的key
     * @return 锁对象
     */
    default ZLock lock(String key) throws Exception {
        return this.lock(key, -1, null, false);
    }

    /**
     * 尝试获取锁，如果锁不可用则等待最多waitTime时间后放弃
     *
     * @param key       锁的key
     * @param waitTime  获取锁的最大尝试时间(单位 {@code unit})
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit      {@code waitTime} 和 {@code leaseTime} 参数的时间单位
     * @param isFair    是否是公平锁
     * @return 锁对象，如果获取锁失败则为null
     */
    ZLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws Exception;

    /**
     * 尝试获取锁，如果锁不可用则等待最多waitTime时间后放弃
     *
     * @param key       锁的key
     * @param waitTime  获取锁的最大尝试时间(单位 {@code unit})
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit      {@code waitTime} 和 {@code leaseTime} 参数的时间单位
     * @return 锁对象，如果获取锁失败则为null
     */
    default ZLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) throws Exception {
        return this.tryLock(key, waitTime, leaseTime, unit, false);
    }

    /**
     * 尝试获取锁，如果锁不可用则等待最多waitTime时间后放弃
     *
     * @param key      锁的key
     * @param waitTime 获取锁的最大尝试时间(单位 {@code unit})
     * @param unit     {@code waitTime} 和 {@code leaseTime} 参数的时间单位
     * @param isFair   是否是公平锁
     * @return 锁对象，如果获取锁失败则为null
     */
    default ZLock tryLock(String key, long waitTime, TimeUnit unit, boolean isFair) throws Exception {
        return this.tryLock(key, waitTime, -1, unit, isFair);
    }

    /**
     * 尝试获取锁，如果锁不可用则等待最多waitTime时间后放弃
     *
     * @param key      锁的key
     * @param waitTime 获取锁的最大尝试时间(单位 {@code unit})
     * @param unit     {@code waitTime} 和 {@code leaseTime} 参数的时间单位
     * @return 锁对象，如果获取锁失败则为null
     */
    default ZLock tryLock(String key, long waitTime, TimeUnit unit) throws Exception {
        return this.tryLock(key, waitTime, -1, unit, false);
    }

    /**
     * 释放锁
     *
     * @param lock 锁对象
     */
    void unlock(Object lock) throws Exception;

    /**
     * 释放锁
     *
     * @param zLock 锁抽象对象
     */
    default void unlock(ZLock zLock) throws Exception {
        if (zLock != null) {
            this.unlock(zLock.getLock());
        }
    }
}