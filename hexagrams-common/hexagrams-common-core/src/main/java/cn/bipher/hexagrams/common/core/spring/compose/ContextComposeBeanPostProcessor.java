package cn.bipher.hexagrams.common.core.spring.compose;

import cn.bipher.hexagrams.common.core.context.ContextComponent;
import cn.bipher.hexagrams.common.core.spring.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lingting 2022/10/22 15:10
 */
@Component
public class ContextComposeBeanPostProcessor implements BeanPostProcessor {

	@Override
	public boolean isProcess(Object bean, String beanName, boolean isBefore) {
		return bean != null && ContextComponent.class.isAssignableFrom(bean.getClass());
	}

	@Override
	public Object postProcessAfter(Object bean, String beanName) {
		((ContextComponent) bean).onApplicationStart();
		return bean;
	}

}
