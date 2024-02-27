package com.example.productservicejanbatch.thirdpartyclients;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductUrl     = "https://fakestoreapi.com/products/{id}";
    private String genericProductUrl = "https://fakestoreapi.com/products";

    @Autowired
    public FakeStoreClient( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);
        if(responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product not Found for id: " +id);
        }
        return  responseEntity.getBody() ;
    }
    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]>  responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

        return  responseEntity.getBody();
    }
    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto>  responseEntity = restTemplate.postForEntity(
                genericProductUrl,fakeStoreProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }
     public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }

    public void updateProductById() {

    }

}
