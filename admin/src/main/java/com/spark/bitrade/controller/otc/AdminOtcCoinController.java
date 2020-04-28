package com.spark.bitrade.controller.otc;

import com.spark.bitrade.annotation.AccessLog;
import com.spark.bitrade.constant.AdminModule;
import com.spark.bitrade.constant.PageModel;
import com.spark.bitrade.controller.common.BaseAdminController;
import com.spark.bitrade.entity.Coin;
import com.spark.bitrade.entity.OtcCoin;
import com.spark.bitrade.service.CoinService;
import com.spark.bitrade.service.OtcCoinService;
import com.spark.bitrade.util.BindingResultUtil;
import com.spark.bitrade.util.FileUtil;
import com.spark.bitrade.util.MessageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.notNull;

/**
 * @author rongyu
 * @description otc币种
 * @date 2018/1/11 13:35
 */
@RestController
@RequestMapping("/otc/otc-coin")
public class AdminOtcCoinController extends BaseAdminController {

    @Autowired
    private OtcCoinService otcCoinService;

    @Autowired
    private CoinService coinService ;

    @RequiresPermissions("otc:otc-coin-create")
    @PostMapping("create")
    @AccessLog(module = AdminModule.OTC, operation = "创建otc币种otcCoin")
    public MessageResult create(@Valid OtcCoin otcCoin, BindingResult bindingResult) {
        isNull(otcCoin.getId(), "validate otcCoin.id!");
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null)
            return result;
        Coin coin = coinService.findByUnit(otcCoin.getUnit());
        if(coin==null)
            return error("暂不支持此币种添加");
        if (otcCoin.getGeneralFeeCoinUnit()==null||"".equals(otcCoin.getGeneralFeeCoinUnit()))
            otcCoin.setGeneralFeeCoinUnit("USDT");
        if(otcCoin.getGeneralDiscountCoinUnit()==null||"".equals(otcCoin.getGeneralDiscountCoinUnit()))
            otcCoin.setGeneralDiscountCoinUnit("SLU");
        otcCoinService.save(otcCoin);
        return success();
    }

    @RequiresPermissions("otc:otc-coin:all")
    @PostMapping("all")
    @AccessLog(module = AdminModule.OTC, operation = "所有otc币种otcCoin")
    public MessageResult all() {
        List<OtcCoin> all = otcCoinService.findAll();
        if (all != null && all.size() > 0)
            return success(all);
        return error("没有数据");
    }

//    @RequiresPermissions("otc:otc-coin:detail")
    @PostMapping("detail")
    @AccessLog(module = AdminModule.OTC, operation = "otc币种otcCoin详情")
    public MessageResult detail(@RequestParam("id") Long id) {
        OtcCoin one = otcCoinService.findOne(id);
        notNull(one, "validate otcCoin.id!");
        return success(one);
    }

    @RequiresPermissions("otc:otc-coin-edit")
    @PostMapping("update")
    @AccessLog(module = AdminModule.OTC, operation = "更新otc币种otcCoin")
    public MessageResult update(@Valid OtcCoin otcCoin, BindingResult bindingResult) {
        notNull(otcCoin.getId(), "validate otcCoin.id!");
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null)
            return result;
        OtcCoin one = otcCoinService.findOne(otcCoin.getId());
        notNull(one, "validate otcCoin.id!");
        if (otcCoin.getGeneralFeeCoinUnit()==null||"".equals(otcCoin.getGeneralFeeCoinUnit()))
            otcCoin.setGeneralFeeCoinUnit("USDT");
        if(otcCoin.getGeneralDiscountCoinUnit()==null||"".equals(otcCoin.getGeneralDiscountCoinUnit()))
            otcCoin.setGeneralDiscountCoinUnit("SLU");
        otcCoinService.save(otcCoin);
        return success();
    }

    @RequiresPermissions("otc:otc-coin-deletes")
    @PostMapping("deletes")
    @AccessLog(module = AdminModule.OTC, operation = "otc币种otcCoin 删除")
    public MessageResult deletes(
            @RequestParam(value = "ids") Long[] ids) {
        otcCoinService.deletes(ids);
        return success("批量删除成功");
    }

    @RequiresPermissions("otc:otc-coin:alter-jy-rate")
    @PostMapping("alter-jy-rate")
    @AccessLog(module = AdminModule.OTC, operation = "修改otc币种otcCoin交易率")
    public MessageResult memberStatistics(
            @RequestParam("id") Long id,
            @RequestParam("jyRate") BigDecimal jyRate) {
        OtcCoin one = otcCoinService.findOne(id);
        notNull(one, "validate otcCoin.id");
        one.setJyRate(jyRate);
        otcCoinService.save(one);
        return success();
    }

    @RequiresPermissions("otc:otc-coin-page-query")
    @PostMapping("page-query")
    @AccessLog(module = AdminModule.OTC, operation = "分页查找otc币种otcCoin")
    public MessageResult pageQuery(PageModel pageModel) {
        Page<OtcCoin> pageResult = otcCoinService.findAll(null, pageModel.getPageable());
        return success(pageResult);
    }

    @RequiresPermissions("otc:otc-coin-out-excel")
    @GetMapping("out-excel")
    @AccessLog(module = AdminModule.OTC, operation = "导出otc币种otcCoin Excel")
    public MessageResult outExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List all = otcCoinService.findAll();
        return new FileUtil().exportExcel(request, response, all, "otcCoin");
    }
}
