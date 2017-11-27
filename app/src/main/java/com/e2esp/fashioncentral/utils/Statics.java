package com.e2esp.fashioncentral.utils;

import com.afrozaar.wp_api_v2_client_android.rest.WpClientRetrofit;
import com.e2esp.fashioncentral.BuildConfig;

/**
 *
 * Created by Zain on 2/16/2017.
 */

public class Statics {

    private static WpClientRetrofit wpClientRetrofit;
    public static WpClientRetrofit getWpClient() {
        if (wpClientRetrofit == null) {
            wpClientRetrofit = new WpClientRetrofit(Consts.WP_BASE_URL, Consts.WP_USERNAME, Consts.WP_PASSWORD, BuildConfig.DEBUG);
        }
        return wpClientRetrofit;
    }

}
