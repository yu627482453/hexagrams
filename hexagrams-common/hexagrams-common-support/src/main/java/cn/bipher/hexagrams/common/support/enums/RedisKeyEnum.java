package cn.bipher.hexagrams.common.support.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Bipher
 * @version 1
 * @date 2023/6/27 0:19
 */
@AllArgsConstructor
@Getter
public enum RedisKeyEnum {
    /**
     * 用户新增
     */
    LOCK_USER_CREATE_CODE(1, "lock::user::create::", 5, TimeUnit.SECONDS, "用户新增"),
    /**
     * 用户更新
     */
    LOCK_USER_UPDATE_CODE(2, "lock::user::update::", 5, TimeUnit.SECONDS, "用户更新"),
    /**
     * 角色创建
     */
    LOCK_ROLE_CREATE_CODE(3, "lock::role::create::", 5, TimeUnit.SECONDS, "角色创建"),
    /**
     * 角色更新
     */
    LOCK_ROLE_UPDATE_CODE(4, "lock::role::update::", 5, TimeUnit.SECONDS, "角色更新"),

    /**
     * 权限创建
     */
    LOCK_PERMISSION_CREATE_CODE(5, "lock::permission::create::", 5, TimeUnit.SECONDS, "权限创建"),
    /**
     * 权限更新
     */
    LOCK_PERMISSION_UPDATE_CODE(6, "lock::permission::update::", 5, TimeUnit.SECONDS, "权限更新"),

    ;
    static final Map<Integer, RedisKeyEnum> POOL = new HashMap<>();

    static {
        for (RedisKeyEnum each : RedisKeyEnum.values()) {
            POOL.put(each.getCode(), each);
        }
    }

    private Integer  code;
    private String   key;
    private Integer  time;
    private TimeUnit timeUnit;
    private String   msg;

    /**
     * 根据code获取枚举
     *
     * @param code
     *
     * @return
     */
    public static RedisKeyEnum getByCode(Integer code) {
        return POOL.get(code);
    }

}