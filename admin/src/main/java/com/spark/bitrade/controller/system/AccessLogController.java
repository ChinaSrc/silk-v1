package com.spark.bitrade.controller.system;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spark.bitrade.annotation.AccessLog;
import com.spark.bitrade.constant.AdminModule;
import com.spark.bitrade.constant.PageModel;
import com.spark.bitrade.controller.common.BaseAdminController;
import com.spark.bitrade.entity.Admin;
import com.spark.bitrade.entity.AdminAccessLog;
import com.spark.bitrade.entity.QAdmin;
import com.spark.bitrade.entity.QAdminAccessLog;
import com.spark.bitrade.service.AdminAccessLogService;
import com.spark.bitrade.service.AdminService;
import com.spark.bitrade.util.MessageResult;
import com.spark.bitrade.util.PredicateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author rongyu
 * @description 日志管理
 * @date 2017/12/22 17:27
 */
@Slf4j
@RestController
@RequestMapping("/system/access-log")
@Transactional(readOnly = true)
public class AccessLogController extends BaseAdminController {

    @Autowired
    private AdminAccessLogService adminAccessLogService;

    @Autowired
    private AdminService adminService ;

    @RequiresPermissions("system:access-log:all")
    @GetMapping("/all")
    @AccessLog(module = AdminModule.SYSTEM, operation = "所有操作/访问日志AdminAccessLog")
    public MessageResult all() {
        List<AdminAccessLog> adminAccessLogList = adminAccessLogService.queryAll();
        return success(adminAccessLogList);
    }

    @RequiresPermissions("system:access-log:detail")
    @GetMapping("/{id}")
    @AccessLog(module = AdminModule.SYSTEM, operation = "操作/访问日志AdminAccessLog 详情")
    public MessageResult detail(@PathVariable("id") Long id) {
        AdminAccessLog adminAccessLog = adminAccessLogService.queryById(id);
        notNull(adminAccessLog, "validate id!");
        return success(adminAccessLog);
    }

    @RequiresPermissions("system:access-log:page-query")
    @GetMapping("/page-query")
    @AccessLog(module = AdminModule.SYSTEM, operation = "分页查找操作/访问日志AdminAccessLog")
    //edit by yangch 时间： 2018.04.29 原因：合并
    public MessageResult pageQuery(
            PageModel pageModel,
            @RequestParam(value = "adminName", required = false) String adminName,
            @RequestParam(value = "module", required = false) AdminModule module) {

        List<BooleanExpression> list = new ArrayList<>();
        list.add(QAdmin.admin.id.eq(QAdminAccessLog.adminAccessLog.adminId));
        if (!StringUtils.isEmpty(adminName))
            list.add(QAdmin.admin.username.like("%"+adminName+"%"));
        if(module!=null)
            list.add(QAdminAccessLog.adminAccessLog.module.eq(module));
        Page<AdminAccessLog> all = adminAccessLogService.page(list, pageModel);
        return success(all);
    }
    /*public MessageResult pageQuery(
            PageModel pageModel,
            @RequestParam(value = "adminId", required = false) Long adminId) {
        Predicate predicate = getPredicateList(adminId);
        Page<AdminAccessLog> all = adminAccessLogService.findAll(predicate, pageModel.getPageable());
        return success(all);
    }*/

    //获得条件
    private Predicate getPredicateList(Long adminId) {
        List<BooleanExpression> list = new ArrayList<>();
        QAdminAccessLog qAdminAccessLog = QAdminAccessLog.adminAccessLog;
        if (adminId != null) {
            list.add(qAdminAccessLog.adminId.eq(adminId));
        }
        return PredicateUtils.getPredicate(list);
    }

}
