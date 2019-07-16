package com.annoroad.demo;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DemoMain {
    public static void main(String[] args) {
        Client client = Client.create();

        // TODO 设置安诺给分配的真实clientId、clientSecret
        String clientId = "";
        String clientSecret = "";

        // 1、获取access_token
        WebResource webResource1 = client.resource("https://oauth.solargenomics.com/oauth/token?grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret);
        ClientResponse response1 = webResource1.accept("application/json").get(ClientResponse.class);
        String output1 = response1.getEntity(String.class);
        System.out.println("output1:" + output1);
        JSONObject jsonObject1 = JSONObject.parseObject(output1);
        String accessToken = jsonObject1.getString("access_token");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 请求"https://api.solargenomics.com/user/detail"接口
        String input = "{\"id\":1}";
        WebResource.Builder webResource2 = client.resource("https://api.solargenomics.com/user/detail").header("Authorization", "Bearer " + accessToken);
        ClientResponse response2 = webResource2.type("application/json").post(ClientResponse.class, input);
        String output2 = response2.getEntity(String.class);
        System.out.println("output2:" + output2);

    }
}
