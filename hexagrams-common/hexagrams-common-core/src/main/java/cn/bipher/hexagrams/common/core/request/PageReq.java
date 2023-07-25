package cn.bipher.hexagrams.common.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分页请求
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 11:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageReq implements IBaseReq {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}