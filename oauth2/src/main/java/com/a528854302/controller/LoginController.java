package com.a528854302.controller;

import com.a528854302.dto.LoginInfo;
import com.a528854302.dto.ResponseResult;
import com.a528854302.utils.MapperUtils;
import com.a528854302.utils.OkHttpClientUtil;
import com.sun.org.apache.regexp.internal.RE;
import lombok.val;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class LoginController {

    private final static String URL="http://localhost:8888/oauth/token";

    @Resource(name = "userDetailsServiceBean")
    UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    TokenStore tokenStore;

    @PostMapping("/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails==null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"username or password is wrong",null);
        }

        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("grant_type","password");
        params.put("client_id","client");
        params.put("client_secret","secret");
        Response response = OkHttpClientUtil.getInstance().postData(URL, params);
        Map<String,Object> data=new HashMap<>();
        String json = null;
        try {
            json = response.body().string();
            data.put("token",MapperUtils.json2map(json).get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(ResponseResult.CodeStatus.OK,"login success",data);
    }
    @GetMapping("/user/info")
    public ResponseResult<LoginInfo> loginInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName("james");
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"get userInfo",loginInfo);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"logut success",null);
    }
}
