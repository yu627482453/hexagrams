package cn.bipher.hexagrams.db.handler;

import cn.bipher.hexagrams.db.properties.DataScopeProperties;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * 个人权限的处理器
 *
 * @author jarvis create by 2023/1/10
 */
public class CreatorDataScopeSqlHandler implements SqlHandler{

    /**
     * 返回需要增加的where条件，返回空字符的话则代表不需要权限控制
     *
     * @return where条件
     */
    @Override
    public String handleScopeSql() {
        // fixme
        return null;
    }
}
