package cn.bipher.hexagrams.common.core.request;

/**
 * 预处理
 * 切面捕获 进行处理
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/16 23:01
 */
public interface IPreHandler {

    /**
     * 预处理
     */
    default void preHandler() {

    }
}

