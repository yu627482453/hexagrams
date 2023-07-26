package cn.bipher.hexagrams.common.utils.exception;

import cn.bipher.hexagrams.common.core.exception.BaseException;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CommandTimeoutException extends BaseException {

    public CommandTimeoutException(String code, String msg, Object[] args) {
        super(code, msg, args);
    }

    public CommandTimeoutException(String msg) {
        super("", msg, null);
    }
}
