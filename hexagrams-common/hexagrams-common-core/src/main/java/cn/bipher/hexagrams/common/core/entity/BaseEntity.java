package cn.bipher.hexagrams.common.core.entity;

import cn.bipher.hexagrams.common.core.enums.DeleteFlagEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Bipher
 * @version 1
 * @date 2023/2/17 22:44
 */
@Data
public class BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 9020291133879879074L;

    /**
     * 删除标记
     */
    private DeleteFlagEnum isDeleted;

    /**
     * 创建人编号
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;
    /**
     * 更新人编号
     */
    private String modifiedBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime gmtModified;
}