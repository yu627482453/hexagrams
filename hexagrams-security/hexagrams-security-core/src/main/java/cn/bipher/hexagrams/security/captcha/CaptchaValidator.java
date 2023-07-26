package cn.bipher.hexagrams.security.captcha;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码验证器
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 18:48
 */
public interface CaptchaValidator {

	/**
	 * 校验验证码
	 * @param request the current request
	 * @return {@link CaptchaValidateResult}
	 */
	CaptchaValidateResult validate(HttpServletRequest request);

}
