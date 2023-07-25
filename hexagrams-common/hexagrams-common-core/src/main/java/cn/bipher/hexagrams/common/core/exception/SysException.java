package cn.bipher.hexagrams.common.core.exception;

/**
 * 系统异常
 *
 * @author Bipher
 * @version 1
 * @date 2023/6/26 23:29
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 6390664693208009235L;

    public SysException(String code, String msg, Object[] args) {
        super(code, msg, args);
    }
}