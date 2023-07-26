package cn.bipher.hexagrams.common.core.request.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * 修改parameterMap
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class ModifyParamMapRequestWrapper extends HttpServletRequestWrapper {

	private final Map<String, String[]> parameterMap;

	public ModifyParamMapRequestWrapper(HttpServletRequest request, Map<String, String[]> parameterMap) {
		super(request);
		this.parameterMap = parameterMap;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return this.parameterMap;
	}

}