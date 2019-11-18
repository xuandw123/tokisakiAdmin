package com.tokisaki.superadmin.common;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.tokisaki.superadmin.integration.config.TencentCloudFeignConfiguration;


@Component
public class CosFileUtil {
	@Autowired
	TencentCloudFeignConfiguration tencentCloudFeignConfiguration;
	

	 // 从输入流进行读取并上传到COS
    public  String SimpleUploadFileFromStream(String key,File localFile,String extName) {
    	String etag="";
    	String secretId=tencentCloudFeignConfiguration.getSecretId();
    	String secretKey=tencentCloudFeignConfiguration.getSecretKey();
    	String region=tencentCloudFeignConfiguration.getRegion();
    	String bucketName = tencentCloudFeignConfiguration.getBucketName();
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        
        
        //String key = "test/test3.jpg";
        //File localFile = new File("C://2/1.jpg");
        String keyName ="task/"+key+"."+extName;
        System.out.println("keyName:"+keyName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard_IA);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
             etag = putObjectResult.getETag();
            System.out.println(etag);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        
        // 关闭客户端        
        cosclient.shutdown();
        return keyName;
    }
    public static void main(String[] args) {
    	//SimpleUploadFileFromStream();
    }

}
