package cn.bipher.hexagrams.security.captcha;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 验证码的校验结果
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 18:48
 */
@Getter
@Accessors(chain = true)
public class CaptchaValidateResult {

	/**
	 * 是否成功
	 */
	private final boolean success;

	/**
	 * 信息
	 */
	private final String message;

	public CaptchaValidateResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public static CaptchaValidateResult success() {
		return success("success");
	}

	public static CaptchaValidateResult success(String successMessage) {
		return new CaptchaValidateResult(true, successMessage);
	}

	public static CaptchaValidateResult failure() {
		return failure("captcha validate failure");
	}

	public static CaptchaValidateResult failure(String failureMessage) {
		return new CaptchaValidateResult(false, failureMessage);
	}

}
