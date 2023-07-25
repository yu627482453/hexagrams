package cn.bipher.hexagrams.common.support.util;

import cn.bipher.hexagrams.common.core.request.OperationReq;
import cn.bipher.hexagrams.common.core.request.PageReq;
import cn.bipher.hexagrams.common.support.entity.BaseEntity;
import cn.bipher.hexagrams.common.support.enums.DeleteFlagEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

/**
 * datasource 元数据 填充
 *
 * @author Bipher
 * @version 1
 * @date 2023/6/27 9:42
 */
public class PoUtil {

    private PoUtil() {

    }

    /**
     * 创建时修改元数据
     *
     * @param po  实体
     * @param req 操作请求体
     * @param <T> extends {@link BaseEntity}
     * @return {@link BaseEntity}
     */
    public static <T extends BaseEntity<?>> T create(T po, OperationReq req) {
        String operatorCode = req.getOperation().getOperatorCode();
        LocalDateTime now = LocalDateTime.now();
        po.setCreatedBy(operatorCode);
        po.setGmtCreated(now);
        po.setModifiedBy(operatorCode);
        po.setGmtModified(now);
        po.setIsDeleted(DeleteFlagEnum.NOT_DELETED);
        return po;
    }

    /**
     * 修改时修改元数据
     *
     * @param po  实体
     * @param req 操作请求体
     * @param <T> extends {@link BaseEntity}
     * @return {@link BaseEntity}
     */
    public static <T extends BaseEntity<?>> T update(T po, OperationReq req) {
        String operatorCode = req.getOperation().getOperatorCode();
        LocalDateTime now = LocalDateTime.now();
        po.setModifiedBy(operatorCode);
        po.setGmtModified(now);
        return po;
    }

    /**
     * 删除时修改元数据
     *
     * @param po  实体
     * @param req 操作请求体
     * @param <T> extends {@link BaseEntity}
     * @return {@link BaseEntity}
     */
    public static <T extends BaseEntity<?>> T remove(T po, OperationReq req) {
        String operatorCode = req.getOperation().getOperatorCode();
        LocalDateTime now = LocalDateTime.now();
        po.setModifiedBy(operatorCode);
        po.setGmtModified(now);
        po.setIsDeleted(DeleteFlagEnum.DELETED);
        return po;
    }

    /**
     * @param pageReq 分页请求
     * @param <T>     clazz
     * @return Page
     */
    public static <T> IPage<T> page(PageReq pageReq) {
        IPage<T> iPage = new Page<>();
        iPage.setCurrent(pageReq.getCurrentPage());
        iPage.setSize(pageReq.getPageSize());
        return iPage;
    }

}