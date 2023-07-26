package cn.bipher.hexagrams.common.support.enums;

import cn.bipher.hexagrams.common.utils.contract.SeqNoEnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务编号枚举
 *
 * @author Bipher
 * @version 1
 * @date 2023/6/26 22:59
 */
@AllArgsConstructor
@Getter
public enum SeqNoEnum implements SeqNoEnumInterface {

    /**
     * 用户编号
     */
    USER_CODE("seq:user:code", "LU", "用户编号", "%3d"),
    ;

    static final Map<String, SeqNoEnum> POOL = new HashMap<>();

    static {
        for (SeqNoEnum each : SeqNoEnum.values()) {
            POOL.put(each.getCode(), each);
        }
    }

    private final String key;
    private final String code;
    private final String msg;
    private final String digit;

    /**
     * 根据code获取枚举
     *
     * @param code 编号
     * @return {@link SeqNoEnum}
     */
    public static SeqNoEnum getByCode(String code) {
        return POOL.get(code);
    }


}