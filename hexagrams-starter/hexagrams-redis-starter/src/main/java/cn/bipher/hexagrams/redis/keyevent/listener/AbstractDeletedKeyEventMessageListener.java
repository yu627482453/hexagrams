package cn.bipher.hexagrams.redis.keyevent.listener;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

/**
 * key deleted event
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public abstract class AbstractDeletedKeyEventMessageListener extends AbstractKeySpaceEventMessageListener {

	private static final Topic KEYEVENT_DELETED_TOPIC = new PatternTopic("__keyevent@*__:del");

	/**
	 * Creates new {@link MessageListener} for specific messages.
	 * @param listenerContainer must not be {@literal null}.
	 */
	protected AbstractDeletedKeyEventMessageListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	@Override
	public Topic getKeyEventTopic() {
		return KEYEVENT_DELETED_TOPIC;
	}

}
