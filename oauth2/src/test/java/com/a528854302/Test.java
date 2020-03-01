package com.a528854302;

import com.a528854302.utils.MapperUtils;
import com.a528854302.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test() throws Exception {
        String url="http://localhost:8888/oauth/token";
        Map<String,String> params = new HashMap<>();
        params.put("username","root");
        params.put("password","123456");
        params.put("grant_type","password");
        params.put("client_id","client");
        params.put("client_secret","secret");
        Response response = OkHttpClientUtil.getInstance().postData(url, params);
        String json = response.body().string();
        MapperUtils.json2map(json);

    }
}
