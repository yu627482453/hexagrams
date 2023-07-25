package cn.bipher.hexagrams.common.core.code;

import lombok.Getter;

/**
 * 抽象 {@link IBaseCodeType} 的实现类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 11:23
 */
@Getter
public class AbstractBaseCodeType implements IBaseCodeType {

    private final String code;

    private final String msg;

    public AbstractBaseCodeType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}