package com.zhpan.library.net.common;


import com.zhpan.library.net.token.IGlobalManager;
import com.zhpan.library.net.token.ProxyHandler;

import java.lang.reflect.Proxy;


/**
 * Created by zhpan on 2017/4/1.
 */

public class IdeaApiProxy  {

    @SuppressWarnings("unchecked")
    public <T> T getApiService(Class<T> tClass,String baseUrl,IGlobalManager manager) {
        T t = RetrofitService.getRetrofitBuilder(baseUrl)
                .build().create(tClass);
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[] { tClass }, new ProxyHandler(t, manager));
    }
}
