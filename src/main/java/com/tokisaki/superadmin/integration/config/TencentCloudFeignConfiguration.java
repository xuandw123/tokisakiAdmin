package com.tokisaki.superadmin.integration.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class TencentCloudFeignConfiguration {

    @Value("${tencentCloud-service.secretId}")
   private String secretId;
    @Value("${tencentCloud-service.secretKey}")
    private String secretKey;
    @Value("${tencentCloud-service.region}")
    private String region;
    @Value("${tencentCloud-service.bucketName}")
    private String bucketName;

    


}
