/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cau1;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.net.URI;
public class GuavaCacheUtil {
    private static LoadingCache<Integer, String> empCache;
    static {
	empCache = CacheBuilder.newBuilder()
	       .maximumSize(100)
	       .expireAfterWrite(10, TimeUnit.SECONDS)
	       .build(
	           new CacheLoader<Integer, String>() {
        		@Override
			public String load(Integer id) throws Exception {
				return show(id);
			}
	           }
	       );
    }
    public static LoadingCache<Integer, String> getLoadingCache() {
	return empCache;
    }
     public static boolean NTo(int k){
        for(int i=2;i<k;i++){
            if(k%i==0)return false;
        }
        return true;
    }
     public static String show(int num1) {
        String res="";
        for(int i=2;i<num1;i++){
            if(NTo(i)==true)
            res=res+i+" ";
        }
        return res;
    }
}
