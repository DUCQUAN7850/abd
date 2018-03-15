/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau3;

//import com.sun.jersey.api.client.Client;
import com.google.common.cache.LoadingCache;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;

/**
 *
 * @author DUC QUAN
 */
public class Main {
    public static String NT(int k){
        String s="";
        for(int i=1;i<k;i++){
            s=s+i+" ";
        }
        return s;
    }
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<Integer, String> c = GuavaCacheUtil.getLoadingCache();
        Scanner in=new Scanner(System.in);
            long lancuoi=System.currentTimeMillis();
        while(true){
            System.out.println("Nhap so: ");
            int k=in.nextInt();
            if(k==-1)break;
            long lantiep=System.currentTimeMillis();
            if(c.get(k).equals(NT(k))==true){
                System.out.println(c.get(k));
                System.out.println("Size= "+c.size());
                lancuoi=System.currentTimeMillis();
            }
            else{
                c.put(k, getSNT(k));
                if(lantiep-lancuoi>=10000){
                    System.out.println("Da qua 10s, cache bi xoa!");
                    c.invalidateAll();
                }
                System.out.println(c.get(k));
                System.out.println("Size= "+c.size());
                lancuoi=System.currentTimeMillis();
            }
            
        }
    }
    public static String getSNT(int number) throws ExecutionException{
        LoadingCache<Integer, String> empCache = GuavaCacheUtil.getLoadingCache();
	return empCache.get(number);
    }
}
