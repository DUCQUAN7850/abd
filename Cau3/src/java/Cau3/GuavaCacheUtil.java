/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau3;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
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
				return getSNT(id);
			}
	           }
	       );
    }
    public static LoadingCache<Integer, String> getLoadingCache() {
	return empCache;
    }
    public static String getSNT(int id) {
	System.out.println("--Đã gửi request--");
	ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getBaseURI());
        String response = target.path("generic/show").queryParam("num1", id).
                            request().
                            accept(MediaType.TEXT_PLAIN).
                            get(String.class)
                            .toString();
        return response;
   }
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/Cau3/webresources").build();
    }
}
