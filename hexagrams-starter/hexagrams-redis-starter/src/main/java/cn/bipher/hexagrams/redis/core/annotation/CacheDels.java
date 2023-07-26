package cn.bipher.hexagrams.redis.core.annotation;

import java.lang.annotation.*;

/**
 * 删除
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MetaCacheAnnotation
public @interface CacheDels {

	CacheDel[] value();

}
