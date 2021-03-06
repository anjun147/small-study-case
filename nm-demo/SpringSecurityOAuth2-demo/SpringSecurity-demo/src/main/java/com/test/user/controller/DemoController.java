package com.test.user.controller;

import com.centre.common.model.Result;
import com.test.security.config.SpringSecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: gl_stars
 * @data: 2020年 09月 29日 14:00
 **/
@RestController
public class DemoController {

    @RequestMapping("/")
    public Result find(){
        return Result.succeed("没有问题");
    }

    /**
     * 这个地址不用登陆就可以访问了。
     * {@link SpringSecurityConfig#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)}
     * @return
     */
    @PostMapping("/find/abc")
    public Result findAbc(){
        return Result.succeed("这个地址不用授权访问");
    }

    /**
     * 获取验证码
     * <p>本来正规流程是，通过验证码生成器生成验证码，这个验证码是一张照片，但是生成器会给我们照片上的验证码。
     * 我们需要将验证码存到Redis中，然后将这个图片通过流的方式写到前端。前端将验证码输入回来时，我们需要去Redis里面查询是否存在。
     * 这里为了简便，我就只做一个过程就行了，图形验证码生成就在当前项目中有现成的，为了过多的引用其他模块，就免了。</p>
     * @return
     */
    @RequestMapping("/code/image")
    public Object imageCode(){
        return 889900;
    }

    /***
     * 短信发送
     */
    @GetMapping("/mobile/page")
    public void toMobilePage() {
        // 这里调用发送短信的接口，当前项目暂时还没有介入短信发送的功能，这里做一个演戏的过程，代表这样就已经发送短信了。
        return;
    }

    /***
     * 获取当前用户信息方式一（推荐）
     * @param map
     */
    @GetMapping("/index")
    public Object index(Map<String, Object> map) {
        // 第1方式：
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof UserDetails) {
            // 这里就已经获取到UserDetails对象了，所以里面的所有数据都有了
            UserDetails userDetails = (UserDetails)principal;
            String username = userDetails.getUsername();
            map.put("username", username);
        }
        return principal ;
    }

    /**
     * 获取当前用户 方式二
     * @param authentication
     * @return
     */
    @GetMapping("/user/info")
    public Object userInfo(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 获取当前用户方式三
     * @param userDetails
     * @return
     */
    @GetMapping("/user/info2")
    public Object userInfo2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
