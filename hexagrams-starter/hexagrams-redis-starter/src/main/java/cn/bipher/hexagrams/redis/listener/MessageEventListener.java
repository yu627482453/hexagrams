package cn.bipher.hexagrams.redis.listener;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.Topic;

/**
 * PUB/SUB 模式中的消息监听者
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public interface MessageEventListener extends MessageListener {

	/**
	 * 订阅者订阅的话题
	 * @return topic
	 */
	Topic topic();

}
