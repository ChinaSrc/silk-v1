package com.spark.bitrade.dao;

import com.spark.bitrade.constant.AdvertiseControlStatus;
import com.spark.bitrade.constant.AdvertiseType;
import com.spark.bitrade.dao.base.BaseDao;
import com.spark.bitrade.entity.Advertise;
import com.spark.bitrade.entity.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Zhang Jinwei
 * @date 2017年12月07日
 */
public interface AdvertiseDao extends BaseDao<Advertise> {

    List<Advertise> findAllByMemberIdAndStatusNot(Long var1, AdvertiseControlStatus status, Sort var2);

    Advertise findAdvertiseByIdAndMemberIdAndStatusNot(Long var1, Long var2, AdvertiseControlStatus status);

    Advertise findByIdAndMemberId(long v1, long v2);

    @Modifying
    @Query("update Advertise a set a.status=?1 where a.id=?2 and a.member.id=?3 and a.status<>?4")
    int updateAdvertiseStatus(AdvertiseControlStatus status, Long id, Long mid, AdvertiseControlStatus advertiseControlStatus);

    List<Advertise> findAllByMemberIdAndStatusAndAdvertiseType(Long var1, AdvertiseControlStatus status, AdvertiseType type);

    @Modifying
    @Query("update Advertise a set a.remainAmount=a.remainAmount-:amount,a.dealAmount=a.dealAmount+:amount where a.remainAmount>=:amount and a.status=:sta and a.id=:id")
    int updateAdvertiseAmount(@Param("sta") AdvertiseControlStatus status, @Param("id") Long id, @Param("amount") BigDecimal amount);


    @Modifying
    @Query("update Advertise a set a.dealAmount=a.dealAmount-:amount where a.dealAmount>=:amount  and a.id=:id")
    int updateAdvertiseDealAmount( @Param("id") Long id, @Param("amount") BigDecimal amount);

    @Modifying
    @Query("update Advertise a set a.status=1,a.remainAmount=0,a.number=0 where a.remainAmount=:amount  and a.id=:id and a.status=0")
    int putOffAdvertise( @Param("id") Long id, @Param("amount") BigDecimal amount);

    List<Advertise> findAllByMemberIdAndStatus(Long var, AdvertiseControlStatus status);

    @Modifying
    @Query("update Advertise a set a.status = :status,a.updateTime=:updateTime where a.id in :ids")
    int alterStatusBatch(@Param("status")AdvertiseControlStatus status,@Param("updateTime")Date updateTime,@Param("ids")Long[] ids) ;

    //add by tansitao 时间： 2018/6/12 原因：通过用户查找广告数
    @Query("select count(a.id) from Advertise a where a.member = :member")
    Long getAdvertiseNum(@Param("member")Member member);

    @Modifying
    @Query("update Advertise a set a.status = :status, a.updateTime = :updateTime where a.id = :id")
    int updateStatus(@Param("status") AdvertiseControlStatus status, @Param("updateTime")Date updateTime, @Param("id")long id);

}
