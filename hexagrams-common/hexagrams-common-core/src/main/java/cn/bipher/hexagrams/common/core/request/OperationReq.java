package cn.bipher.hexagrams.common.core.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 操作请求
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/20 23:26
 */
@Data
public class OperationReq implements IBaseReq {

    private static final long serialVersionUID = -5251651270794361972L;

    /**
     * 操作数据
     */
    private Operation operation;

    @Data
    public static class Operation implements Serializable {

        private static final long serialVersionUID = 6265855334733359568L;
        /**
         * 操作人编号
         */
        private String operatorCode;
        /**
         * 操作人名称
         */
        private String operatorName;

    }
}


