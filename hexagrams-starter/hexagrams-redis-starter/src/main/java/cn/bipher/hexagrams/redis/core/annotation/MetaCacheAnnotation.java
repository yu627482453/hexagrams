package cn.bipher.hexagrams.redis.core.annotation;

import java.lang.annotation.*;

/**
 * 用于标识切点的元注解
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MetaCacheAnnotation {

}
