package com.spark.bitrade.job;


import com.alibaba.fastjson.JSON;
import com.spark.bitrade.entity.ExchangeCoin;
import com.spark.bitrade.entity.ExchangeOrder;
import com.spark.bitrade.service.ExchangeCoinService;
import com.spark.bitrade.service.ExchangeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//del by yangch 时间： 2018.07.26 原因：定时任务迁移到schedule-job模块中,类名为 AutoCancelExchangeOrderHandler
//@Component
//public class OrderUpdateJob {
//
//    @Autowired
//    private ExchangeOrderService orderService;
//    @Autowired
//    private ExchangeCoinService coinService;
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//    private Logger logger = LoggerFactory.getLogger(OrderUpdateJob.class);
//
//    @Scheduled(fixedRate = 60*1000)
//    public void autoCancelOrder(){
//        logger.info("start autoCancelOrder...");
//        List<ExchangeCoin> coinList = coinService.findAllEnabled();
//        coinList.forEach(coin->{
//            if(coin.getMaxTradingTime() > 0){
//                List<ExchangeOrder> orders =  orderService.findOvertimeOrder(coin.getSymbol(),coin.getMaxTradingTime());
//                orders.forEach(order -> {
//                    // 发送消息至Exchange系统
//                    kafkaTemplate.send("exchange-order-cancel",order.getSymbol(), JSON.toJSONString(order));
//                    logger.info("orderId:"+order.getOrderId()+",time:"+order.getTime());
//                });
//            }
//        });
//        logger.info("end autoCancelOrder...");
//    }
//
//
//}
