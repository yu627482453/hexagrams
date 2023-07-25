package cn.bipher.hexagrams.common.utils.autoconfigure;

import cn.bipher.hexagrams.common.utils.util.SpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * SpringUtils自动装载类
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 17:55
 */
@Configuration
@Import(SpringUtil.class)
public class SpringUtilAutoConfiguration {
}