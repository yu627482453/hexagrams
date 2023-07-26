package cn.bipher.hexagrams.common.utils.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * Spring Expression Language
 *
 * @author Bipher
 * @version 1
 * @date 2022/12/30 13:46
 */
@UtilityClass
public class SpelUtil {

    /**
     * 支持SPEL表达式
     * @param spel 表达式
     * @param method 目标方法
     * @param args 方法入参
     * @return 解析后的字符串
     */
    public static String parse(String spel, Method method, Object[] args) {
        return parse(spel, method, args, String.class);
    }

    /**
     * 支持SPEL表达式
     * @param spel 表达式
     * @param method 目标方法
     * @param args 方法入参
     * @return 解析后的字符串
     */
    public static <T> T parse(String spel, Method method, Object[] args,Class<T> t) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(spel).getValue(context, t);
    }

    /**
     * 支持 #p0 参数索引的表达式解析
     * @param rootObject 根对象,method 所在的对象
     * @param spel 表达式
     * @param method ，目标方法
     * @param args 方法入参
     * @return 解析后的字符串
     */
    public static String parse(Object rootObject, String spel, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new MethodBasedEvaluationContext(rootObject,method,args,u);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(spel).getValue(context, String.class);
    }
}
