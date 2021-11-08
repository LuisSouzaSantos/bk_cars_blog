package br.com.supersim.blog.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class Properties {
	
	private String bucketS3Name;
	private String awsKeyId;
	private String awsSecretKey;
	private String jwtSecret;
	private String jwtExpiration;
	
	public Properties(
			@Value("${br.com.supersim.bucket-s3-name}") String bucketS3Name, 
			@Value("${br.com.supersim.aws-key-id}") String awsKeyId, 
			@Value("${br.com.supersim.aws-secret-key}") String awsSecretKey,
			@Value("${br.com.supersim.jwt.secret}") String jwtSecret,
			@Value("${br.com.supersim.jwt.expiration}") String jwtExpiration) {
		this.bucketS3Name = bucketS3Name;
		this.awsKeyId = awsKeyId;
		this.awsSecretKey = awsSecretKey;
		this.jwtSecret = jwtSecret;
		this.jwtExpiration = jwtExpiration;
	}

	public String getBucketS3Name() {
		return bucketS3Name;
	}

	public void setBucketS3Name(String bucketS3Name) {
		this.bucketS3Name = bucketS3Name;
	}

	public String getAwsKeyId() {
		return awsKeyId;
	}

	public void setAwsKeyId(String awsKeyId) {
		this.awsKeyId = awsKeyId;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public String getJwtExpiration() {
		return jwtExpiration;
	}

	public void setJwtExpiration(String jwtExpiration) {
		this.jwtExpiration = jwtExpiration;
	}

}
