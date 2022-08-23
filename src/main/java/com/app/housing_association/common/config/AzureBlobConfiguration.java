package com.app.housing_association.common.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfiguration {

    @Value("${azure.storage.connection.string}")
    private String azureConnectionString;

    @Value("${azure.storage.data.container}")
    private String azureStorageContainer;

    @Bean
    public BlobServiceClient clobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(azureConnectionString)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient() {
        return clobServiceClient().getBlobContainerClient(azureStorageContainer);
    }
}
