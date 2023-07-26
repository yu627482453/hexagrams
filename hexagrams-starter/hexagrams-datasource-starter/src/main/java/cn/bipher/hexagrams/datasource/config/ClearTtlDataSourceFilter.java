package cn.bipher.hexagrams.datasource.config;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.springframework.core.Ordered;
import org.springframework.web.filter.GenericFilterBean;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 清空上文的DS 设置避免污染当前线程
 * @author Bipher
 * @version 1
 * @date 2022/12/30 13:46
 */
public class ClearTtlDataSourceFilter extends GenericFilterBean implements Ordered {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		DynamicDataSourceContextHolder.clear();
		filterChain.doFilter(servletRequest, servletResponse);
		DynamicDataSourceContextHolder.clear();
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
