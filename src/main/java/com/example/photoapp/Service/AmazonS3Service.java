package com.example.photoapp.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.photoapp.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class AmazonS3Service {
    @Value("${s3.access-key}")
    private String accessKey;
    @Value("${s3.secret-access-key}")
    private String secretAccessKey;
    @Value("${s3.bucket}")
    private String bucketName;

    private AmazonS3 getClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_1)
                .build();
    }

    public void uploadToAmazonS3(MultipartFile multipartFile, String uniqueFileName) throws IOException {
        FileInputStream input = (FileInputStream) multipartFile.getInputStream();
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(multipartFile.getSize());
        metaData.setContentType(multipartFile.getContentType());
        PutObjectRequest request = new PutObjectRequest(bucketName, uniqueFileName, input, metaData);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        getClient().putObject(request);
    }
}
