package cn.bipher.hexagrams.common.core.code;

/**
 * 异常操作接口类（各服务定义异常code需要实现该接口）
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 16:14
 */
public interface IBaseCodeType {

    /**
     * 获取编号
     * @return code
     */
    String getCode();

    /**
     * 获取信息
     * @return msg
     */
    String getMsg();
}
