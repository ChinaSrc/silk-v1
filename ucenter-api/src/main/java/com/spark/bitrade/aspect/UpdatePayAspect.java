package com.spark.bitrade.aspect;

import com.spark.bitrade.entity.Advertise;
import com.spark.bitrade.entity.Order;
import com.spark.bitrade.entity.transform.AuthMember;
import com.spark.bitrade.service.AdvertiseService;
import com.spark.bitrade.service.LocaleMessageSourceService;
import com.spark.bitrade.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.spark.bitrade.constant.SysConstant.SESSION_MEMBER;

/**
 * 修改支付信息前必须下架所有的广告，并且没有正在进行中的订单
 *
 * @author Zhang Jinwei
 * @date 2018年03月28日
 */
@Aspect
@Component
@Slf4j
public class UpdatePayAspect {
    @Autowired
    private LocaleMessageSourceService msService;
    @Autowired
    private AdvertiseService advertiseService;
    @Autowired
    private OrderService orderService;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.spark.bitrade.controller.ApproveController.updateAli(..))"+
            "||execution(public * com.spark.bitrade.controller.ApproveController.updateBank(..))"+
            "||execution(public * com.spark.bitrade.controller.ApproveController.updateWechat(..))"+
            "||execution(public * com.spark.bitrade.controller.ApproveController.binEpay(..))" +
            "||execution(public * com.spark.bitrade.controller.ApproveController.unBind(..))")
    public void updatePay() {
    }

    @Before("updatePay()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");
        check(joinPoint);
    }

    public void check(JoinPoint joinPoint) throws Exception {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        AuthMember authMember = (AuthMember) request.getSession().getAttribute(SESSION_MEMBER);
        List<Order> list1 = orderService.getAllOrdering(authMember.getId());
        if (list1.size()>0){
            throw new IllegalArgumentException(msService.getMessage("HAVE_ORDER_ING"));
        }
        List<Advertise> list = advertiseService.getAllPutOnAdvertis(authMember.getId());
        if (list.size()>0){
            throw new IllegalArgumentException(msService.getMessage("MUST_PUT_OFF_ALL_ADVERTISE"));
        }
    }

    @AfterReturning(pointcut = "updatePay()")
    public void doAfterReturning() throws Throwable {
        log.info("处理耗时：" + (System.currentTimeMillis() - startTime.get()) + "ms");
        log.info("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
        //add by tansitao 时间： 2018/11/22 原因：调用方法介绍remove掉线程
        startTime.remove();
    }

    /**
      * 接口异常的处理
      * @author tansitao
      * @time 2018/11/22 16:03 
      */
    @AfterThrowing(pointcut = "updatePay()")
    public void afterThrowing() {
        log.info("=====================================处理耗时："  + (System.currentTimeMillis() - startTime.get()) + "ms");
        startTime.remove();
    }
}
