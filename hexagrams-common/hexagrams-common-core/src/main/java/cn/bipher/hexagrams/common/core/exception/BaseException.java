package cn.bipher.hexagrams.common.core.exception;

import cn.bipher.hexagrams.common.core.code.IBaseCodeType;
import lombok.Getter;

/**
 * 基础错误类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/16 23:13
 */
@Getter
public class BaseException extends RuntimeException implements IBaseCodeType {

    private static final long serialVersionUID = 7940053461667431923L;

    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String msg;
    /**
     * 异常消息参数
     */
    private final Object[] args;

    public BaseException(String code, String msg, Object[] args) {
        this.code = code;
        this.args = args;
        this.msg = msg;
    }

    public IBaseCodeType getErrorCode() {
        return null;
    }

    public String getCode() {
        return getErrorCode() == null ? this.code : getErrorCode().getCode();
    }

    public String getMsg() {
        return getErrorCode() == null ? this.msg : getErrorCode().getMsg();
    }

}