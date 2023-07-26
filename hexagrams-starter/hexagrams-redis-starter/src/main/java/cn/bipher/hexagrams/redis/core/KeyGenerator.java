package cn.bipher.hexagrams.redis.core;

import cn.bipher.hexagrams.common.core.exception.ExceptionFactory;
import cn.bipher.hexagrams.common.core.exception.UtilException;
import cn.bipher.hexagrams.common.utils.util.Assert;
import cn.bipher.hexagrams.common.utils.util.SpelUtil;
import cn.bipher.hexagrams.redis.config.CachePropertiesHolder;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 缓存key的生成工具类，主要用于解析spel, 进行拼接key的生成
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class KeyGenerator {

    /**
     * SpEL 上下文
     */
    StandardEvaluationContext spelContext;

    public KeyGenerator(Object target, Method method, Object[] arguments) {
        this.spelContext = SpelUtil.getSpelContext(target, method, arguments);
    }

    /**
     * 根据 keyPrefix 和 keyJoint 获取完整的 key 信息
     *
     * @param keyPrefix key 前缀
     * @param keyJoint  key 拼接元素，值为 spel 表达式，可为空
     * @return 拼接完成的 key
     */
    public String getKey(String keyPrefix, String keyJoint) {
        // 根据 keyJoint 判断是否需要拼接
        if (keyJoint == null || keyJoint.length() == 0) {
            return keyPrefix;
        }
        // 获取所有需要拼接的元素, 组装进集合中
        String joint = SpelUtil.parseValueToString(spelContext, keyJoint);
        Assert.notNull(joint, ExceptionFactory.exception(UtilException.class, "Key joint cannot be null!"));

        if (!StringUtils.hasText(keyPrefix)) {
            return joint;
        }
        // 拼接后返回
        return jointKey(keyPrefix, joint);
    }

    public List<String> getKeys(String keyPrefix, String keyJoint) {
        // keyJoint 必须有值
        Assert.notBlank(keyJoint, ExceptionFactory.exception(UtilException.class, "[getKeys] keyJoint cannot be " +
                "null"));

        // 获取所有需要拼接的元素, 组装进集合中
        List<String> joints = SpelUtil.parseValueToStringList(spelContext, keyJoint);
        Assert.notEmpty(joints, ExceptionFactory.exception(UtilException.class, "[getKeys] keyJoint must be resolved " +
                "to a non-empty collection!"));

        if (!StringUtils.hasText(keyPrefix)) {
            return joints;
        }
        // 拼接后返回
        return joints.stream().map(x -> jointKey(keyPrefix, x)).collect(Collectors.toList());
    }

    /**
     * 拼接key, 默认使用 ：作为分隔符
     *
     * @param keyItems 用于拼接 key 的元素列表
     * @return 拼接完成的 key
     */
    public String jointKey(List<String> keyItems) {
        return String.join(CachePropertiesHolder.delimiter(), keyItems);
    }

    /**
     * 拼接key, 默认使用 ：作为分隔符
     *
     * @param keyItems 用于拼接 key 的元素列表
     * @return 拼接完成的 key
     */
    public String jointKey(String... keyItems) {
        return jointKey(Arrays.asList(keyItems));
    }

}
