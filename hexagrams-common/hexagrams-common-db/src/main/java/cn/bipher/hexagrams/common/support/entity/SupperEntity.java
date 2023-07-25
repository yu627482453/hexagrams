package cn.bipher.hexagrams.common.support.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 实体父类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/17 22:44
 */
@Setter
@Getter
public class SupperEntity<T extends Model<?>> extends Model<T> {

    private static final long serialVersionUID = -1473605901375444446L;
    @TableId
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime gmtModified;
}