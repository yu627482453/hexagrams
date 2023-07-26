package cn.bipher.hexagrams.common.core.constant;

/**
 * MDC 相关常量
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public final class MDCConstants {

    /**
     * 跟踪ID，用于一次请求或执行方法时，产生的各种日志间的数据关联
     */
    public static final String TRACE_ID_KEY = "traceId";

    private MDCConstants() {
    }

}
