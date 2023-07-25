package cn.bipher.hexagrams.common.core.exception;

import cn.bipher.hexagrams.common.core.constant.BaseConstants;
import lombok.Getter;

import java.util.StringJoiner;

/**
 * 校验错误类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 15:47
 */
@Getter
public class ValidateException extends BaseException {

    private static final long serialVersionUID = 8619923637358564016L;
    private final String msg;

    public ValidateException(String code, String msg, Object[] args) {
        super(code, msg, args);
        // msg
        StringJoiner sj = new StringJoiner(BaseConstants.LINE_FEED);
        sj.add(msg);
        for (Object arg : args) {
            sj.add(String.valueOf(arg));
        }
        this.msg = sj.toString();

    }

}