package cn.bipher.hexagrams.common.supoort.lock;

import cn.bipher.hexagrams.common.core.exception.BaseException;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:25
 */
public class LockException extends BaseException {
    private static final long serialVersionUID = 1833883997315379771L;

    public LockException(String msg) {
        super("", msg, null);
    }
}