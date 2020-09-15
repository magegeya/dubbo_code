package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youle.constant.MessageConstant;
import com.youle.entity.Result;
import com.youle.pojo.OrderSetting;
import com.youle.service.OrderSettingService;
import com.youle.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
 @RequestMapping("/ordersetting")
public class OrderSettingController {
   @Reference
    private OrderSettingService orderSettingService;


    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
     try{
       List<String[]> list = POIUtils.readExcel(excelFile);
     if(list != null && list.size() > 0){
      List<OrderSetting> orderSettingList = new ArrayList<>();
      for (String[] strings : list) {
       OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
       orderSettingList.add(orderSetting);
      }
      orderSettingService.add(orderSettingList);
     }
    } catch (IOException e) {
      e.printStackTrace();
  return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
 }
return  new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);


   }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){//参数格式为：2019-03
        try{
        List<Map> list = orderSettingService.getOrderSettingByMonth(date);
//获取预约设置数据成功
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }catch (Exception e){ e.printStackTrace();
//获取预约设置数据失败
        return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
    }
}
}
