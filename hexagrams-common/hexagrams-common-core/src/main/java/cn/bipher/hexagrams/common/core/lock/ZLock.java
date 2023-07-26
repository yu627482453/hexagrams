package cn.bipher.hexagrams.common.core.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:13
 */
@AllArgsConstructor
public class ZLock implements AutoCloseable {

    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}