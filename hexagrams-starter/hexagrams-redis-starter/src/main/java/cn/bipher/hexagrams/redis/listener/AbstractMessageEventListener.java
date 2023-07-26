package cn.bipher.hexagrams.redis.listener;

import cn.bipher.hexagrams.common.utils.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * redis消息监听处理器
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public abstract class AbstractMessageEventListener<T> implements MessageEventListener {

	@Resource
	protected StringRedisTemplate stringRedisTemplate;

	protected final Class<T> clz;

	@SuppressWarnings("unchecked")
	protected AbstractMessageEventListener() {
		Type superClass = getClass().getGenericSuperclass();
		ParameterizedType type = (ParameterizedType) superClass;
		clz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] channelBytes = message.getChannel();
		RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
		String channelTopic = stringSerializer.deserialize(channelBytes);
		String topic = topic().getTopic();
		if (topic.equals(channelTopic)) {
			byte[] bodyBytes = message.getBody();
			String body = stringSerializer.deserialize(bodyBytes);
			T decodeMessage = JsonUtil.toObj(body, clz);
			handleMessage(decodeMessage);
		}
	}

	/**
	 * 处理消息
	 * @param decodeMessage 反系列化之后的消息
	 */
	protected abstract void handleMessage(T decodeMessage);

}
