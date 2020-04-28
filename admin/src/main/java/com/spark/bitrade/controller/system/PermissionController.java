package com.spark.bitrade.controller.system;

import com.mysema.commons.lang.Assert;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spark.bitrade.annotation.AccessLog;
import com.spark.bitrade.constant.AdminModule;
import com.spark.bitrade.constant.PageModel;
import com.spark.bitrade.controller.BaseController;
import com.spark.bitrade.entity.QSysPermission;
import com.spark.bitrade.entity.SysPermission;
import com.spark.bitrade.service.SysPermissionService;
import com.spark.bitrade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@ApiIgnore
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequiresPermissions("system:permission:merge")
    @PostMapping("/merge")
    @AccessLog(module = AdminModule.SYSTEM, operation = "创建/修改权限")
    public MessageResult merge(@Valid SysPermission permission) {
        permission = sysPermissionService.save(permission);
        MessageResult result = success("保存权限成功");
        result.setData(permission);
        return result;
    }


    @RequiresPermissions("system:permission:page-query")
    @PostMapping("/page-query")
    @AccessLog(module = AdminModule.SYSTEM, operation = "分页查询权限")
    public MessageResult pageQuery(PageModel pageModel,
                                   @RequestParam(value = "parentId", required = false) Long parentId) {
        BooleanExpression predicate = null;
        if (parentId != null) {
            predicate = QSysPermission.sysPermission.parentId.eq(parentId);
        }
        Page<SysPermission> all = sysPermissionService.findAll(predicate, pageModel.getPageable());
        return success(all);
    }

    @RequiresPermissions("system:permission:detail")
    @PostMapping("/detail")
    @AccessLog(module = AdminModule.SYSTEM, operation = "权限详情")
    public MessageResult detail(@RequestParam(value = "id") Long id) {
        SysPermission sysPermission = sysPermissionService.findOne(id);
        Assert.notNull(sysPermission, "该权限不存在");
        return MessageResult.getSuccessInstance("查询权限成功", sysPermission);
    }

    @RequiresPermissions("system:permission:deletes")
    @PostMapping("/deletes")
    @AccessLog(module = AdminModule.SYSTEM, operation = "批量删除权限")
    public MessageResult deletes(@RequestParam(value = "ids") Long[] ids) {
        sysPermissionService.deletes(ids);
        return MessageResult.success("批量删除权限成功");
    }

}
