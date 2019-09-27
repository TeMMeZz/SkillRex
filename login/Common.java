package com.stuhawe.geigertim.login;

import com.stuhawe.geigertim.login.Remote.IGoogleApi;
import com.stuhawe.geigertim.login.Remote.RetrofitClient;

/**
 * Created by geiger.tim on 25.01.2018.
 */

public class Common {
    public static final String baseURL ="https://googleapis.com";
    public static IGoogleApi getGoogleApi(){
        return RetrofitClient.getClient(baseURL).create(IGoogleApi.class);
    }
}
