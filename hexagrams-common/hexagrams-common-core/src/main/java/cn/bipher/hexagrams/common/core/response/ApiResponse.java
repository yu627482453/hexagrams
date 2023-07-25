package cn.bipher.hexagrams.common.core.response;

import cn.bipher.hexagrams.common.core.code.BaseErrorCodeEnum;
import cn.bipher.hexagrams.common.core.code.IBaseCodeType;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用响应
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 11:40
 */
@Data
public class ApiResponse<T> implements IBaseCodeType, IBaseRes {

    private static final long serialVersionUID = 1L;

    /**
     * 接口状态码
     */
    protected String code = BaseErrorCodeEnum.SUCCESS.getCode();

    /**
     * 接口响应说明
     */
    protected String msg = BaseErrorCodeEnum.SUCCESS.getMsg();

    /**
     * 接口响应数据
     */
    private T data;

    public ApiResponse() {

    }

    public ApiResponse(String code, String msg, T data) {
        if (code != null && !"".equals(code.trim())) {
            this.code = code;
        }

        if (msg != null && !"".equals(msg.trim())) {
            this.msg = msg;
        }
        this.data = data;
    }

    /**
     * 通用失败响应
     * @return {@link ApiResponse}
     */
    public static ApiResponse<Object> fail() {
        return new ApiResponse<>(BaseErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
                BaseErrorCodeEnum.INTERNAL_SERVER_ERROR.getMsg(), null);
    }

    /**
     * @param <T> T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create() {
        return new ApiResponse<>();
    }

    /**
     * @param code code
     * @param msg  msg
     * @param data data
     * @param <T>  T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create(String code, String msg, T data) {
        return new ApiResponse<>(code, msg, data);
    }

    /**
     * 推荐使用
     *
     * @param codeType {@link IBaseCodeType}
     * @param data     data
     * @param <T>      T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create(IBaseCodeType codeType, T data) {
        return new ApiResponse<>(codeType.getCode(), codeType.getMsg(), data);
    }

    /**
     * 推荐使用
     *
     * @param codeType {@link IBaseCodeType}
     * @param <T>      T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create(IBaseCodeType codeType) {
        return new ApiResponse<>(codeType.getCode(), codeType.getMsg(), null);
    }

    /**
     * @param code code
     * @param msg  msg
     * @param <T>  T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create(String code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    /**
     * @param data data
     * @param <T>  T
     * @return {@link ApiResponse}
     */
    public static <T> ApiResponse<T> create(T data) {
        return new ApiResponse<>(null, null, data);
    }

    /**
     * 判断返回结果是否正确
     *
     * @return boolean
     */
    public boolean isSuccess() {
        return BaseErrorCodeEnum.SUCCESS.getCode().equals(this.code);
    }

}