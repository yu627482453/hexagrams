package cn.bipher.hexagrams.common.utils.util;

import cn.bipher.hexagrams.common.core.code.IBaseCodeType;
import cn.bipher.hexagrams.common.core.exception.ExceptionFactory;

import java.util.Map;

/**
 * 断言<br>
 * 断言某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/20 23:50
 */
public class Assert {

    private Assert() {

    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出给定的异常<br>
     *
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, IllegalArgumentException::new);
     * </pre>
     *
     * @param expression   布尔值
     * @param baseCodeType 指定断言不通过时抛出的异常
     */
    public static void isTrue(boolean expression, IBaseCodeType baseCodeType) {
        if (!expression) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言是否为假，如果为 {@code true} 抛出指定类型异常<br>
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     *  Assert.isFalse(i &gt; 0, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param expression   布尔值
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @since 5.4.5
     */
    public static void isFalse(boolean expression, IBaseCodeType baseCodeType) {
        if (expression) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出指定类型异常
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.isNull(value, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param object       被检查的对象
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @since 5.4.5
     */
    public static void isNull(Object object, IBaseCodeType baseCodeType) {
        if (null != object) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出指定类型异常
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notNull(clazz, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param object       被检查对象
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @since 5.4.5
     */
    public static void notNull(Object object, IBaseCodeType baseCodeType) {
        if (null == object) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出自定义异常。
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notBlank(name, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <T>          字符串类型
     * @param text         被检查字符串
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @see StringUtil#isNotBlank(CharSequence)
     */
    public static <T extends CharSequence> void notBlank(T text, IBaseCodeType baseCodeType) {
        if (StringUtil.isBlank(text)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言给定数组是否包含元素，数组必须不为 {@code null} 且至少包含一个元素
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notEmpty(array, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <T>          数组元素类型
     * @param array        被检查的数组
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @see ArrayUtil#isNotEmpty(Object[])
     * @since 5.4.5
     */
    public static <T> void notEmpty(T[] array, IBaseCodeType baseCodeType) {
        if (ArrayUtil.isEmpty(array)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }


    /**
     * 断言给定集合非空
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notEmpty(collection, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <E>          集合元素类型
     * @param <T>          集合类型
     * @param collection   被检查的集合
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @see CollectionUtil#isNotEmpty(Iterable)
     * @since 5.4.5
     */
    public static <E, T extends Iterable<E>> void notEmpty(T collection, IBaseCodeType baseCodeType) {
        if (CollectionUtil.isEmpty(collection)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言给定Map非空
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.notEmpty(map, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <K>          Key类型
     * @param <V>          Value类型
     * @param <T>          Map类型
     * @param map          被检查的Map
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @see MapUtil#isNotEmpty(Map)
     * @since 5.4.5
     */
    public static <K, V, T extends Map<K, V>> void notEmpty(T map, IBaseCodeType baseCodeType) {
        if (MapUtil.isEmpty(map)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言给定对象是否是给定类的实例
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo, "foo must be an instance of class Foo");
     * </pre>
     *
     * @param <T>          被检查对象泛型类型
     * @param type         被检查对象匹配的类型
     * @param obj          被检查对象
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @see Class#isInstance(Object)
     */
    public static <T> void isInstanceOf(Class<?> type, T obj, IBaseCodeType baseCodeType) {
        notNull(type, baseCodeType);
        if (!type.isInstance(obj)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言 {@code superType.isAssignableFrom(subType)} 是否为 {@code true}.
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass);
     * </pre>
     *
     * @param superType    需要检查的父类或接口
     * @param subType      需要检查的子类
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @throws IllegalArgumentException 如果子类非继承父类，抛出此异常
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, IBaseCodeType baseCodeType) {
        notNull(superType, baseCodeType);
        notNull(subType, baseCodeType);
        if (!superType.isAssignableFrom(subType)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 检查boolean表达式，当检查结果为false时抛出 {@code IllegalStateException}。
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     * Assert.state(id == null, ()-&gt;{
     *      // to query relation message
     *      return "relation message to return ";
     *  });
     * </pre>
     *
     * @param expression   boolean 表达式
     * @param baseCodeType 指定断言不通过时抛出的异常
     * @throws IllegalStateException 表达式为 {@code false} 抛出此异常
     */
    public static void state(boolean expression, IBaseCodeType baseCodeType) {
        if (!expression) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }

    /**
     * 断言两个对象是否不相等,如果两个对象相等,抛出指定类型异常,并使用指定的函数获取错误信息返回
     *
     * @param obj1         对象1
     * @param obj2         对象2
     * @param baseCodeType 指定断言不通过时抛出的异常
     */
    public static void notEquals(Object obj1, Object obj2, IBaseCodeType baseCodeType) {
        if (ObjectUtil.equals(obj1, obj2)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }


    /**
     * 断言两个对象是否相等,如果两个对象不相等,抛出指定类型异常,并使用指定的函数获取错误信息返回
     *
     * @param obj1         对象1
     * @param obj2         对象2
     * @param baseCodeType 指定断言不通过时抛出的异常
     */
    public static void equals(Object obj1, Object obj2, IBaseCodeType baseCodeType) {
        if (ObjectUtil.notEqual(obj1, obj2)) {
            throw ExceptionFactory.bizException(baseCodeType);
        }
    }
}
