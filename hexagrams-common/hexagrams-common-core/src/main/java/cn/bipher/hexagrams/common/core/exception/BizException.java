package cn.bipher.hexagrams.common.core.exception;

/**
 * 业务错误类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 15:36
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1962751136104959248L;

    public BizException(String code, String msg, Object[] args) {
        super(code, msg, args);
    }


}