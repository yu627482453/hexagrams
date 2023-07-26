package cn.bipher.hexagrams.common.utils.util;


import cn.bipher.hexagrams.common.core.code.BaseErrorCodeEnum;
import cn.bipher.hexagrams.common.core.exception.ExceptionFactory;
import cn.bipher.hexagrams.common.core.exception.ValidateException;
import cn.bipher.hexagrams.common.core.validate.ValidateGroup;
import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * bean对象属性验证
 *
 * @author Bipher
 * @version 1
 * @date 2022/12/30 13:57
 */
@UtilityClass
public class BeanValidators {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 按组进行校验
     *
     * @param object object 待校验对象
     * @param groups 分组 {@link ValidateGroup}
     */
    @SafeVarargs
    public static void validate(Object object, Class<ValidateGroup>... groups) {

        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw ExceptionFactory.exception(ValidateException.class, BaseErrorCodeEnum.PARAM_ERROR,
                    constraintViolations.toArray());
        }
    }

    /**
     * 进行校验
     *
     * @param object object 待校验对象
     */
    public static void validate(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object);
        if (!constraintViolations.isEmpty()) {
            throw ExceptionFactory.exception(ValidateException.class, BaseErrorCodeEnum.PARAM_ERROR,
                    constraintViolations.toArray());
        }
    }
}