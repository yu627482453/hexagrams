package cn.bipher.hexagrams.common.support.entity;

import cn.bipher.hexagrams.common.support.enums.DeleteFlagEnum;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 完备的实体父类
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 18:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends Model<?>> extends SupperEntity<T> {

    private static final long serialVersionUID = 581147555675656014L;
    /**
     * 删除标记
     */
    private DeleteFlagEnum isDeleted;

    /**
     * 创建人编号
     */
    private String createdBy;

    /**
     * 更新人编号
     */
    private String modifiedBy;

}