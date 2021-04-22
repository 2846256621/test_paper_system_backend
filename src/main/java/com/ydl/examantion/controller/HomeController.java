package com.ydl.examantion.controller;

import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.service.HomeService;
import com.ydl.examantion.service.SubjectService;
import com.ydl.examantion.vo.StatisticRes;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"首页接口"})
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    /**
     * 首页统计
     * @return
     */
    @GetMapping("/statistic")
    public ResponseResult statistic(){
        StatisticRes data = homeService.getStatistic();
        return ResponseResult.data(data);
    }

}
