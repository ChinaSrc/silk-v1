package com.spark.bitrade.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author rongyu
 * @description 会员等级实体
 * @date 2017/12/26 17:12
 */
@Data
@Entity
@Table(name = "member_level")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberLevel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotBlank(message = "会员等级名称不得为空")
    private String name;
    @NotNull(message = "默认不得为null")
    private Boolean isDefault;
    private String remark;

}
