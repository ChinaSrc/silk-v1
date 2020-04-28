package com.spark.bitrade.dao;

import com.spark.bitrade.dao.base.BaseDao;
import com.spark.bitrade.entity.Admin;
import com.spark.bitrade.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author Zhang Jinwei
 * @date 2017年12月18日
 */
public interface AdminDao extends BaseDao<Admin> {

    Admin findAdminByUsernameAndPassword(String username, String password);

    @Modifying
    @Query("update Admin a set a.lastLoginTime=?1,a.lastLoginIp=?2 where a.id=?3")
    int updateAdminLastTimeAndIp(Date date, String ip, Long memberId);

    @Modifying
    @Query("delete from Admin a where a.roleId = ?1")
    int deleteBatch(Long roleId);

    List<Admin> findAllByDepartment(Department department);

    List<Admin> findAllByRoleId(long id);

    Admin findByUsername(String username);
}
