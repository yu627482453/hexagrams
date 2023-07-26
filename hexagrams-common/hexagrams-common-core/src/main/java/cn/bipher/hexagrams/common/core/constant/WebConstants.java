package cn.bipher.hexagrams.common.core.constant;

/**
 * Web 相关常量
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:53
 */
public class WebConstants {

    /**
     * www主域
     */
    public static final String WWW = "www.";
    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi:";
    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap:";
    /**
     * LDAPS 远程方法调用
     */
    public static final String LOOKUP_LDAPS = "ldaps:";
    /**
     * http请求
     */
    public static final String HTTP = "http://";
    /**
     * https请求
     */
    public static final String HTTPS = "https://";
    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;
    /**
     * 登录成功状态
     */
    public static final String LOGIN_SUCCESS_STATUS = "1";
    /**
     * 登录失败状态
     */
    public static final String LOGIN_FAIL_STATUS = "0";
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";
    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";
    /**
     * 注册
     */
    public static final String REGISTER = "Register";
    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 请求时间戳
     */
    public static final String REQ_TIME = "reqTime";

    /**
     * 请求sign
     */
    public static final String SIGN = "sign";

    /**
     * SECRET_ID
     */
    public static final String SECRET_ID = "secretId";



    private WebConstants() {

    }
}