package com.stuhawe.geigertim.login.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by geiger.tim on 25.01.2018.
 */

public interface IGoogleApi {
    @GET
    Call<String> getDataFromGoogleApi(@Url String url);
}
