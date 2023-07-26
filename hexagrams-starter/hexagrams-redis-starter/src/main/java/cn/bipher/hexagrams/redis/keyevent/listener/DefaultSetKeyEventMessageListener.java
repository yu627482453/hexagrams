package cn.bipher.hexagrams.redis.keyevent.listener;

import cn.bipher.hexagrams.redis.keyevent.template.KeySetEventMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * default key set event handler
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Slf4j
public class DefaultSetKeyEventMessageListener extends AbstractSetKeyEventMessageListener {

	protected List<KeySetEventMessageTemplate> keySetEventMessageTemplates;

	/**
	 * Creates new {@link MessageListener} for specific messages.
	 * @param listenerContainer must not be {@literal null}.
	 */
	public DefaultSetKeyEventMessageListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	public DefaultSetKeyEventMessageListener(RedisMessageListenerContainer listenerContainer,
			ObjectProvider<List<KeySetEventMessageTemplate>> objectProvider) {
		super(listenerContainer);
		objectProvider.ifAvailable(templates -> this.keySetEventMessageTemplates = new ArrayList<>(templates));
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		if (CollectionUtils.isEmpty(keySetEventMessageTemplates)) {
			return;
		}
		super.onMessage(message, pattern);
		String setKey = message.toString();
		// 监听key信息新增/修改事件
		for (KeySetEventMessageTemplate keySetEventMessageTemplate : keySetEventMessageTemplates) {
			if (keySetEventMessageTemplate.support(setKey)) {
				if (log.isTraceEnabled()) {
					log.trace("use template[{}]handle key set event,the set key is[{}]",
							keySetEventMessageTemplate.getClass().getName(), setKey);
				}
				keySetEventMessageTemplate.handleMessage(setKey);
			}
		}

	}

}
