package cn.bipher.hexagrams.common.core.thread;

import cn.bipher.hexagrams.common.core.context.ContextComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 11:15
 */
@Slf4j
public abstract class AbstractThreadContextComponent extends Thread implements ContextComponent {

    protected void init() {

    }

    public boolean isRun() {
        return !isInterrupted() && isAlive();
    }

    @Override
    public void onApplicationStart() {
        setName(getClass().getSimpleName());
        if (!isAlive()) {
            start();
        }
    }

    @Override
    public void onApplicationStop() {
        log.warn("{} 线程: {}; 开始关闭!", getClass().getSimpleName(), getId());
        interrupt();
    }

    public String getSimpleName() {
        return getClass().getSimpleName();
    }

    @Override
    public abstract void run();

}
