package com.e2esp.fashioncentral.utils;

import android.content.Context;
import android.widget.Toast;

import com.afrozaar.wp_api_v2_client_android.rest.HttpServerErrorResponse;
import com.afrozaar.wp_api_v2_client_android.rest.WordPressRestResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by Zain on 2/17/2017.
 */

public class Utils {

    public static <T> void doRetrofitCall(Call<T> call, final WordPressRestResponse<T> callback) {
        Callback<T> retroCallback = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(HttpServerErrorResponse.from(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(HttpServerErrorResponse.from(t));
            }
        };
        call.enqueue(retroCallback);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
