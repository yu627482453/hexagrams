package cn.bipher.hexagrams.common.core.handler;

/**
 * 异常日志处理类
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 11:06
 */
public interface GlobalExceptionHandler {

    /**
     * 在此处理错误信息 进行落库，入ES， 发送报警通知等信息
     * @param throwable 异常
     */
    void handle(Throwable throwable);

}