package com.youle.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import  com.youle.constant.MessageConstant;
import com.youle.constant.RedisConstant;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.entity.Result;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.Setmeal;
import com.youle.service.CheckGroupService;
import com.youle.service.SetmealService;
import com.youle.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import  javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal") public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    //图片上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
        System.out.println(imgFile);
        String filename = imgFile.getOriginalFilename();//原始的文件名称  a.jpg
        int indexOf = filename.lastIndexOf(".");
        String name = filename.substring(indexOf); //表示获取到文件的原始后缀
        //借助UUID  或者  时间戳  来随机生成名称名称
        filename = UUID.randomUUID().toString() + name;
        try {
            //借助骑牛云工具类完成这个图片上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            //提交云存储之后在将文件名称保存到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,"http://qgmnz3862.hd-bkt.clouddn.com/"+filename);
        } catch (IOException e) {
            e.printStackTrace();
            //上传失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
    }
    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
        setmealService.add(setmeal,checkgroupIds);
    }catch (Exception e){
//新增套餐失败
        return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
    }
//新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.pageQuery(queryPageBean);
    }
}
