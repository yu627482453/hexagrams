package cn.bipher.hexagrams.common.core.request;

/**
 * 参数校验接口
 * 切面进行捕获 处理
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/16 23:02
 */
public interface IParamCheck {

    /**
     * 校验
     * 书写校验逻辑
     */
    default void check() {

    }
}
