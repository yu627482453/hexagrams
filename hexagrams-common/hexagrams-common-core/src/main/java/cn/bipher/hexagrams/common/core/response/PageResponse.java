package cn.bipher.hexagrams.common.core.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回对象类，PageEsResponse为ES分页返回对象类
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/6 15:13
 */
@Data
@Accessors
public class PageResponse<T extends Serializable> implements IBaseRes {


    private static final long serialVersionUID = -415258852153096684L;

    private int currentPage = 1;
    private int pageSize = 10;
    private int total = 0;
    private int totalPage = 0;
    private List<T> records;
}