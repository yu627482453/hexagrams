package cn.bipher.hexagrams.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 当数据以存在时的导入动作
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:51
 */
@Getter
@AllArgsConstructor
public enum ImportModeEnum {

    /**
     * 跳过已存在的数据
     */
    SKIP_EXISTING,

    /**
     * 覆盖已存在的数据
     */
    OVERWRITE_EXISTING;

}
