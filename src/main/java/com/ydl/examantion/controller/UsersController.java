package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ydl.examantion.model.Users;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.UsersService;
import com.ydl.examantion.util.AesUtils;
import com.ydl.examantion.vo.UserRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
 * <p>
 *  接口
 * </p>
 *
 * @author YDL
 * @since 2021-01-09
 */
@Api(tags = {"用户接口"})
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    UsersService usersService;

    @PostMapping(value = "/userRegister")
    public ResponseResult addUser(@RequestBody UserRegisterVo userRegisterVo,HttpServletRequest request){
        ResponseResult responseResult = validateData(userRegisterVo);
        if(!responseResult.isSuccess()){
            return responseResult;
        }
        HttpSession session = request.getSession(true);
        Object key = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("key"+key);
        String code = Objects.toString(key);
        String verificationCode = userRegisterVo.getVerificationCode();
        System.out.println("code："+code+"vervode:"+verificationCode+","+verificationCode.equalsIgnoreCase(code));
//        if(!verificationCode.equalsIgnoreCase(code)){
//            return ResponseResult.fail("验证码输入不正确");
//        }

        Users user = new Users();
        BeanUtils.copyProperties(userRegisterVo,user);
        user.setPassWord(AesUtils.aesCbcNoPaddingEncrypt(userRegisterVo.getPassWord().getBytes(), AesUtils.sKey));
        return  usersService.registerUser(user);
    }

    @PostMapping(value = "/userLogin")
    public ResponseResult userLogin(@RequestBody UserRegisterVo userRegisterVo){
        ResponseResult responseResult = validateData(userRegisterVo);
        if(!responseResult.isSuccess()){
            return responseResult;
        }
        //TODO 校验验证码
        userRegisterVo.setPassWord(AesUtils.aesCbcNoPaddingEncrypt(userRegisterVo.getPassWord().getBytes(), AesUtils.sKey));
        return usersService.selectUserLogin(userRegisterVo);
    }


    @GetMapping(value="/createCode")
    public void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中去
            String createText = defaultKaptcha.createText();
            System.out.print(createText);
            //给定一个唯一标识，防止验证码重复使用
            HttpSession session = httpServletRequest.getSession(true);
//            Cookie cookie = new Cookie("JESSIONID",session.getId());
//            cookie.setMaxAge(60*60*24);
//            httpServletResponse.addCookie(cookie);
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            //redisCache.set(RedisCache.prefix, coldFlag, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");

        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");//若要返回cookie、携带seesion等信息则将此项设置我true
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    //更新信息
    @PostMapping(value = "/updateUser")
    public ResponseResult updateUser(@RequestBody Users users){
        if(!StringUtils.isEmpty(users.getPassWord())){
            users.setPassWord(AesUtils.aesCbcNoPaddingEncrypt(users.getPassWord().getBytes(), AesUtils.sKey));
        }
        boolean isStatus = usersService.saveOrUpdate(users);
        if(isStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("更新失败");
    }



    public ResponseResult validateData(UserRegisterVo userRegisterVo){
        String userName = userRegisterVo.getUserName();
        if(StringUtils.isEmpty(userName)){
            return ResponseResult.fail("用户名不能为空");
        }
        String passWord = userRegisterVo.getPassWord();
        if(StringUtils.isEmpty(passWord)){
            return ResponseResult.fail("密码不能为空");
        }

        Integer userType = userRegisterVo.getType();
        if(null == userType ){
            return ResponseResult.fail("用户类型不能为空");
        }

        String verificationCode = userRegisterVo.getVerificationCode();
        if(StringUtils.isEmpty(verificationCode)){
            return ResponseResult.fail("验证码不能为空!");
        }
        return ResponseResult.success();
    }


}
