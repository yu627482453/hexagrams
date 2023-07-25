package cn.bipher.hexagrams.common.core.exception;

import cn.bipher.hexagrams.common.core.code.BaseErrorCodeEnum;
import cn.bipher.hexagrams.common.core.code.IBaseCodeType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 异常工厂
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/16 23:15
 */
public class ExceptionFactory {

    private ExceptionFactory() {

    }

    /**
     * 获取 exception
     *
     * @param clazz {@link BaseException}
     * @param code  code
     * @param msg   msg
     * @param args  args
     * @param <T>   T extend BaseException
     * @return Exception
     */
    public static <T> T exception(Class<T> clazz, String code, String msg, Object[] args) {

        if (!BaseException.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("异常工厂 报错");
        }

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(String.class, String.class, Object[].class);
            return constructor.newInstance(code, msg, args);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 exception
     *
     * @param clazz    {@link BaseException}
     * @param baseCode {@link IBaseCodeType}
     * @param args     args
     * @param <T>      T extend BaseException
     * @return Exception
     */
    public static <T> T exception(Class<T> clazz, @NotNull IBaseCodeType baseCode, Object[] args) {
        return ExceptionFactory.exception(clazz, baseCode.getCode(), baseCode.getMsg(), args);
    }

    /**
     * 获取 exception
     *
     * @param clazz    {@link BaseException}
     * @param baseCode {@link IBaseCodeType}
     * @param <T>      T extend BaseException
     * @return Exception
     */
    public static <T> T exception(Class<T> clazz, @NotNull IBaseCodeType baseCode) {
        return ExceptionFactory.exception(clazz, baseCode.getCode(), baseCode.getMsg(), null);
    }

    /**
     * 获取 biz exception
     *
     * @param baseCode {@link IBaseCodeType}
     * @return BizException
     */
    public static BizException bizException(@NotNull IBaseCodeType baseCode) {
        return ExceptionFactory.exception(BizException.class, baseCode.getCode(), baseCode.getMsg(), null);
    }

    public static <T> T exception(Class<T> clazz, String msg) {
        return ExceptionFactory.exception(clazz, BaseErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), msg, null);
    }

    /**
     * 获取 BaseException
     *
     * @param baseCode {@link IBaseCodeType}
     * @return BaseException
     */
    public static BaseException baseException(@NotNull IBaseCodeType baseCode) {
        return new BaseException(baseCode.getCode(), baseCode.getMsg(), null);
    }

    /**
     * 获取 BaseException
     *
     * @param code code
     * @param msg  msg
     * @return BaseException
     */
    public static BaseException baseException(String code, String msg) {
        return new BaseException(code, msg, null);
    }

    /**
     * 获取 SysException
     *
     * @param baseCode {@link IBaseCodeType}
     * @return SysException
     */
    public static SysException sysException(@NotNull IBaseCodeType baseCode) {
        return new SysException(baseCode.getCode(), baseCode.getMsg(), null);
    }

    /**
     * 获取 SysException
     *
     * @param code code
     * @param msg  msg
     * @return SysException
     */
    public static SysException sysException(String code, String msg) {
        return new SysException(code, msg, null);
    }

}