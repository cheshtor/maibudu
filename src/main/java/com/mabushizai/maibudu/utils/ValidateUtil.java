package com.mabushizai.maibudu.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 对象属性校验工具
 *
 * @author Pengyu Gan
 * CreateDate 2022/4/12
 */
@Slf4j
public class ValidateUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验对象属性是否符合条件
     * @param obj 被校验对象
     * @param <T> 被校验对象泛型
     * @return true 符合条件 false 条件不符
     */
    public static <T> boolean isValid(T obj) {
        AssertUtil.notNull(obj, "被校验对象不能为空");
        return validator.validate(obj).isEmpty();
    }



}
