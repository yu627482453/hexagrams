package cn.bipher.hexagrams.common.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统错误编号
 * </br>
 * 编号规则:
 * - 5位定位编号 + 1 位类型编号 + 3位唯一编号
 * - 基础错误编号
 *   - 参考 HttpStatus
 * - 系统错误编号
 *   - 定位编号: 00000
 *   - 类型编号:
 *     - 0: 非运行时错误
 *     - 1: 工具类错误
 *     - 2: 配置错误
 * - 业务错误编号
 *   - 每个服务参照 {@link BaseErrorCodeEnum} 自定义对应的ErrorCodeEnum implements {@link IBaseCodeType}
 *   - 定位编号: 服务端口号
 *   - 类型编号:
 *     - 0: 校验错误
 *     - 1: 数据错误
 *     - 2: 业务错误
 *  - 唯一编号:
 *     - 第一位用于定位 api的Controller 位置
 *     - 第二、三位 用于唯一索引
 * @author Bipher
 * @version 1
 * @date 2023/2/6 11:25
 */
@Getter
@AllArgsConstructor
public enum BaseErrorCodeEnum implements IBaseCodeType {

    // ----------------------------
    //           基础编号
    // ----------------------------
    /**
     * 成功
     */
    SUCCESS("200", "成功"),

    /**
     * 参数异常
     */
    PARAM_ERROR("304", "参数异常"),

    /**
     * 身份无法识别
     */
    UN_AUTHORIZED("401", "身份无法识别"),
    /**
     * 客户端地址不正确，请重新登录
     */
    REMOTING_ADDR_WRONG("402", "客户端地址不正确，请重新登录"),
    /**
     * 非常抱歉，您没有权限使用该功能！
     */
    NO_AUTHORITY("403", "非常抱歉，您没有权限使用该功能！"),
    /**
     * 没有找到资源
     */
    NOT_FOUND("404", "没有找到资源"),

    /**
     * 系统维护中，请稍候再试
     */
    INTERNAL_SERVER_ERROR("500", "系统维护中，请稍候再试"),

    /**
     * 请求的数据不存在或已删除
     */
    DATA_NOT_FIND("5010", "请求的数据不存在或已删除"),
    /**
     * 参数不正确
     */
    PARAMETER_INVALID("5011", "参数不正确"),
    /**
     * 请求地址不正确
     */
    HTTP_URL_INVALID("5012", "请求地址不正确"),
    /**
     * 请求格式不正确
     */
    HTTP_MEDIA_TYPE_INVALID("5013", "请求格式不正确"),
    /**
     * 请求方式不正确
     */
    HTTP_METHOD_INVALID("5014", "请求方式不正确"),
    /**
     * mybatisPlus处理查询条件异常
     */
    MYBATIS_QUERY_ERROR("5015", "mybatisPlus处理查询条件异常"),

    /**
     * 未登录或登录超时
     */
    NO_LOGIN("5016", "未登录或登录超时"),
    /**
     * 请求来源不正确或已过期
     */
    CSRF_EXCEPTION("5017", "请求来源不正确或已过期"),
    /**
     * token过期
     */
    TOKEN_EXPIRED("5018", "token过期"),
    /**
     * token错误
     */
    TOKEN_ERROR("5019", "token错误"),
    /**
     * 签名错误
     */
    SIGNATURE_ERROR("5020", "签名错误"),

    /**
     * 其他
     */
    OTHER("-1", "错误"),
    ;

    static final Map<String, BaseErrorCodeEnum> POOL = new HashMap<>();

    static {
        for (BaseErrorCodeEnum each : BaseErrorCodeEnum.values()) {
            POOL.put(each.getCode(), each);
        }
    }

    /**
     * code
     */
    private final String code;
    /**
     * msg
     */
    private final String msg;

    /**
     * 根据code获取枚举
     *
     * @param code 编号
     * @return 枚举
     */
    public static BaseErrorCodeEnum getByCode(String code) {
        return POOL.getOrDefault(code, OTHER);
    }


    /**
     * 是否存在该编号
     * true: 存在
     *
     * @param code 编号
     * @return if exist
     */
    public static boolean exist(String code) {
        return BaseErrorCodeEnum.getByCode(code) != OTHER;
    }
}