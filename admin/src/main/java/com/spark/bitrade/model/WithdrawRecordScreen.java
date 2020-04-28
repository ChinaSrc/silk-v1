package com.spark.bitrade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spark.bitrade.constant.BooleanEnum;
import com.spark.bitrade.constant.WithdrawStatus;
import lombok.Data;

import java.util.Date;

@Data
public class WithdrawRecordScreen extends AccountScreen{

    private String unit ;

    /**
     * 提现地址
     */
    private String address ;

    private WithdrawStatus status ;

    /**
     * 是否自动提现
     */
    private BooleanEnum isAuto;

    private Long memberId ;
}
