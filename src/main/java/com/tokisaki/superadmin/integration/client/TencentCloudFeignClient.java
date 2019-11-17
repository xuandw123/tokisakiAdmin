package com.tokisaki.superadmin.integration.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tokisaki.superadmin.integration.config.TencentCloudFeignConfiguration;

/**
 *
 */
@FeignClient(name = "tencentCloud-service", url = "${tencentCloud-service.url}", configuration = TencentCloudFeignConfiguration.class)
public interface TencentCloudFeignClient {

    /**
     * Get property info from EC system
     * @return
     */
    @RequestMapping(path = "/cirepo/jenkins_artifacts/settings/repoLock.json", method = RequestMethod.GET, produces = "application/json")
    String fileUpload(@RequestHeader("Authorization") String authorization);

}
