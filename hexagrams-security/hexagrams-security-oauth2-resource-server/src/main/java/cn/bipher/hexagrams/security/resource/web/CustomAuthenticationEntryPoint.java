package cn.bipher.hexagrams.security.resource.web;

import cn.bipher.hexagrams.common.core.response.ApiResponse;
import cn.bipher.hexagrams.common.utils.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/9/25 22:04
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {

		String utf8 = StandardCharsets.UTF_8.toString();

		httpServletResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		httpServletResponse.setCharacterEncoding(utf8);
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		ApiResponse<Object> r = ApiResponse.fail(e.getMessage());
		httpServletResponse.getWriter().write(JsonUtil.toJson(r));
	}

}
