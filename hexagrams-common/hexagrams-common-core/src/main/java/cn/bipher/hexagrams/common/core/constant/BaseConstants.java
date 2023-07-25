package cn.bipher.hexagrams.common.core.constant;


/**
 * 常量类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 15:26
 */
public final class BaseConstants {

    public static final String LINE_FEED = "\n";
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";
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
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";
    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";
    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;
    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 公共日期格式
     */
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLE_MONTH_FORMAT = "yyyyMM";
    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    public static final String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";
    public static final String TIME_ZONE_GMT8 = "GMT+8";

    private BaseConstants() {

    }

}
