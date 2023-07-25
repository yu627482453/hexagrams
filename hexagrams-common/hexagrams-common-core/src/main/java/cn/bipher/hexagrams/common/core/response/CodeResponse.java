package cn.bipher.hexagrams.common.core.response;

import lombok.Getter;

import java.io.Serializable;

/**
 * 编号 响应
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/21 14:14
 */
@Getter
public class CodeResponse implements IBaseRes {
    private static final long serialVersionUID = 2978286371939610921L;

    /**
     * 编号
     */
    private final String code;

    public CodeResponse(String code) {
        this.code = code;
    }
}