package com.spark.bitrade.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spark.bitrade.constant.AdminModule;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户访问操作日志
 *
 * @author Zhang Jinwei
 * @date 2017年12月19日
 */
@Entity
@Data
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminAccessLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long adminId;

    //add by yangch 时间： 2018.04.29 原因：合并
    @Transient
    private String adminName ;

    private String uri;

    @Enumerated(EnumType.ORDINAL)
    private AdminModule module;

    private String operation;

    private String accessIp;

    private String accessMethod;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date accessTime;

    //add by zyj :2018-11-08 增加备注（对管理员操作的具体描述信息）
    @Column(columnDefinition = "varchar(255) comment '备注'")
    private String remark;

}
