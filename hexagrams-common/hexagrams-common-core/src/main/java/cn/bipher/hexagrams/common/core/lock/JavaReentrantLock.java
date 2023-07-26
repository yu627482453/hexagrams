package cn.bipher.hexagrams.common.core.lock;

import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 11:08
 */
@Getter
public class JavaReentrantLock {

    /**
     * 锁
     */
    protected final ReentrantLock lock = new ReentrantLock();

    /**
     * 激活与休眠线程
     */
    protected final Condition defaultCondition = lock.newCondition();

    public Condition newCondition() {
        return getLock().newCondition();
    }

    public void lock() {
        getLock().lock();
    }

    public void lockInterruptibly() throws InterruptedException {
        getLock().lockInterruptibly();
    }

    public void runLock(Runnable runnable) throws InterruptedException {
        ReentrantLock reentrantLock = getLock();
        reentrantLock.lock();
        try {
            runnable.run();
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void runLockInterruptibly(Runnable runnable) throws InterruptedException {
        ReentrantLock reentrantLock = getLock();
        reentrantLock.lockInterruptibly();
        try {
            runnable.run();
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void unlock() {
        getLock().unlock();
    }

    public void signal() {
        getDefaultCondition().signal();
    }

    public void signalAll() {
        getDefaultCondition().signalAll();
    }

    public void await() throws InterruptedException {
        getDefaultCondition().await();
    }

    /**
     * @return 是否被唤醒
     */
    public boolean await(long time, TimeUnit timeUnit) throws InterruptedException {
        return getDefaultCondition().await(time, timeUnit);
    }

    /**
     * @author Bipher
     * @version 1
     * @date 2023/7/26 11:08
     */
    public interface Runnable {

        void run() throws InterruptedException;

    }

}
