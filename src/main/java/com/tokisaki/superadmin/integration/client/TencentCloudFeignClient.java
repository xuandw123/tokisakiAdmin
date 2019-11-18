package com.tokisaki.superadmin.integration.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tokisaki.superadmin.integration.config.TencentCloudFeignConfiguration;

/**
 *
 */
@FeignClient(name = "tencentCloud-service", url = "", configuration = TencentCloudFeignConfiguration.class)
public interface TencentCloudFeignClient {

  

}
