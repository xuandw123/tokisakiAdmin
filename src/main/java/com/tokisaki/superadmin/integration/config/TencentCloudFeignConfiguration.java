package com.tokisaki.superadmin.integration.config;


import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
public class TencentCloudFeignConfiguration {

    //@Value("${jenkinsfile-service.username}")
   // private String username;

   	

    // @Bean
    // public Logger.Level feignLoggerLevel() {
    // return Logger.Level.FULL;
    // }

    //@Bean
   // public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
   //     return new BasicAuthRequestInterceptor(username, password);
   // }*/

    @Bean
    public Client feignclient() {
        KeyStore trustStore;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            return new Client.Default(new IgnoreCertificateSSLSocketFactory(trustStore), new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Client.Default(null, null);
        }

    }



}
