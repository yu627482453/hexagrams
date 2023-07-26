package cn.bipher.hexagrams.db.config;

import cn.bipher.hexagrams.db.handler.CreatorDataScopeSqlHandler;
import cn.bipher.hexagrams.db.handler.SqlHandler;
import cn.bipher.hexagrams.db.interceptor.CustomTenantInterceptor;
import cn.bipher.hexagrams.db.interceptor.DataScopeInnerInterceptor;
import cn.bipher.hexagrams.db.interceptor.EnableQuerySqlLogInnerInterceptor;
import cn.bipher.hexagrams.db.properties.DataScopeProperties;
import cn.bipher.hexagrams.db.properties.MybatisPlusAutoFillProperties;
import cn.bipher.hexagrams.db.properties.TenantProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * mybatis-plus自动配置
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@EnableConfigurationProperties({MybatisPlusAutoFillProperties.class, DataScopeProperties.class})
public class MybatisPlusAutoConfigure {
    @Autowired
    private TenantLineHandler tenantLineHandler;

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private MybatisPlusAutoFillProperties autoFillProperties;

    @Autowired
    private DataScopeProperties dataScopeProperties;

    @Bean
    @ConditionalOnMissingBean
    public SqlHandler sqlHandler(){
        return new CreatorDataScopeSqlHandler();
    }

    /**
     * 分页插件，自动识别数据库类型
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(SqlHandler sqlHandler) {
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        boolean enableTenant = tenantProperties.getEnable();
        //是否开启多租户隔离
        if (enableTenant) {
            CustomTenantInterceptor tenantInterceptor = new CustomTenantInterceptor(
                    tenantLineHandler, tenantProperties.getIgnoreSqls());
            mpInterceptor.addInnerInterceptor(tenantInterceptor);
        }
        if(dataScopeProperties.getEnabled()){
            DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor(dataScopeProperties, sqlHandler);
            mpInterceptor.addInnerInterceptor(Boolean.TRUE.equals(dataScopeProperties.getEnabledSqlDebug())
                    ? new EnableQuerySqlLogInnerInterceptor(dataScopeInnerInterceptor): dataScopeInnerInterceptor);
        }
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mpInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "hexagrams.mybatis-plus.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new DataMetaObjectHandler(autoFillProperties);
    }
}
