package com.spark.bitrade.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import com.spark.bitrade.core.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Zhang Jinwei
 * @date 2018年01月10日
 */
@AllArgsConstructor
@Getter
public enum AdvertiseControlStatus implements BaseEnum {
    /**
     * 上架
     */
    PUT_ON_SHELVES("上架"),
    /**
     * 下架
     */
    PUT_OFF_SHELVES("下架"),
    /**
     * 已关闭（删除）
     */
    TURNOFF("已关闭"),
    /**
     * 已失效
     */
    FAILURE("已失效");

    @Setter
    private String cnName;

    @Override
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
}
