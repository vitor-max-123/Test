package com.example.demo.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.VultureCheck;
import com.example.demo.exception.Result;
import com.example.demo.pojo.FaxRequestParam;
import com.example.demo.pojo.UserInfoVo;
import com.example.demo.service.AsyncService;
import com.example.demo.util.DealFileUtil;
import com.example.demo.util.ExcelUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static org.apache.poi.util.IOUtils.toByteArray;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/demo")
public class TestController {
    @PostMapping("/send")
    public Result<String> send(MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        try {
            String fileBody = DealFileUtil.streamToString(file.getInputStream());
            FaxRequestParam faxRequestParam=new FaxRequestParam();
            //faxRequestParam.setAccessId(237433);
            faxRequestParam.setAccessId(276146);
            faxRequestParam.setAction("Queue_Fax");
            faxRequestParam.setAccessPwd("Assignment1234");
            faxRequestParam.setSCallerID(9063733033L);
            //faxRequestParam.setSSenderEmail("support@westsidelend.com");
            faxRequestParam.setSSenderEmail("support@eastlinelend.com");
            faxRequestParam.setSFaxType("SINGLE");
            //faxRequestParam.setSToFaxNumber("19063733032");
            faxRequestParam.setSToFaxNumber("19063733033");



            faxRequestParam.setSFileContent(fileBody);
            faxRequestParam.setSFileName(fileName);
            String url="https://global.srfax.com/SRF_SecWebSvc.php";
            System.out.println(JSON.toJSONString(faxRequestParam));
            String post = HttpUtil.post(url, JSON.toJSONString(faxRequestParam));
            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("suc");
    }
    @GetMapping("/get-status")
    public Result<String> getStatus(Integer id){
/*            FaxRequestParam faxRequestParam=new FaxRequestParam();
            faxRequestParam.setAccessId(276146);
            faxRequestParam.setAction("Get_FaxStatus");
            faxRequestParam.setAccessPwd("Assignment1234");
            faxRequestParam.setSFaxDetailsID(id);

            String url="https://global.srfax.com/SRF_SecWebSvc.php";
            System.out.println(JSON.toJSONString(faxRequestParam));
            String post = HttpUtil.post(url, JSON.toJSONString(faxRequestParam));
            System.out.println(post);*/
        return Result.success("suc");
    }

    @PostMapping("/export")
    @VultureCheck(value = "ab")
    public void export(@RequestBody UserInfoVo userInfoVo,HttpServletResponse response, HttpServletRequest request){
        String titleStr="名字,年龄,性别";
        String keyStr="name,age,sex";
        List<JSONObject> list=new ArrayList<>();
        JSONObject p1=new JSONObject();
        p1.put("name","张三");
        p1.put("age",18);
        p1.put("sex","男");
        JSONObject p2=new JSONObject();
        p2.put("name","李四");
        p2.put("age",21);
        p2.put("sex","男");
        JSONObject p3=new JSONObject();
        p3.put("name","王五");
        p3.put("age",24);
        p3.put("sex","男");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        try(SXSSFWorkbook wb = ExcelUtil.createExcel(titleStr.split(","),keyStr.split(","),JSONArray.parseArray(JSON.toJSONString(list)))) {
        String fileName = "test.xlsx";
        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private AsyncService asyncService;
    @GetMapping("async")
    public Result<String> asyncDemo(Integer num){
        asyncService.asyncDemo(num);
        return Result.success("suc");
    }
}
