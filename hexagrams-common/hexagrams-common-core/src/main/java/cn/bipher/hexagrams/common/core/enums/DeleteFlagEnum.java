package cn.bipher.hexagrams.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除标志
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/17 22:45
 */
@Getter
@AllArgsConstructor
public enum DeleteFlagEnum {

    /**
     * 未删除
     */
    NOT_DELETED(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除"),
    ;

    private final Integer code;

    private final String msg;
}