package cn.bipher.hexagrams.common.core.exception;

/**
 * 工具错误类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 15:34
 */
public class UtilException extends BaseException {

    private static final long serialVersionUID = 2951216745230867136L;

    public UtilException(String code, String msg, Object[] args) {
        super(code, msg, args);
    }
}