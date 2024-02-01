package com.ecust.jwq.tails.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="aliyun.oss")
public class AliOSSproperties {
    private String  endpoint;
    private String  accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
