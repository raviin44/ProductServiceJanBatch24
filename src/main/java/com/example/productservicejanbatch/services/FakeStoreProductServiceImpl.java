package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Category;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements  ProductService{

    private FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductServiceImpl( FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        return  getProductFromFakeStoreproductDto( fakeStoreClient.getProductById(id)) ;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new LinkedList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreClient.getAllProducts()) {
            productList.add(getProductFromFakeStoreproductDto(fakeStoreProductDto));
        }
        return  productList;
    }
    public Product addProduct(Product product) {
        return getProductFromFakeStoreproductDto(fakeStoreClient.addProduct(getFakeStoreproductDtoFromProduct(product)));
    }
   /* @Override
    public void deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(specificProductUrl, id);
    }*/

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeStoreproductDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public void updateProductById() {

    }

    private Product getProductFromFakeStoreproductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDesc(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());

        return  product;
    }

    // Reverse Mapper as DTO from Product
    private FakeStoreProductDto getFakeStoreproductDtoFromProduct(Product product){
       FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDesc());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());

        return  fakeStoreProductDto;
    }

}

/*
1. Handling 3rd Party call - separate thing
2. Mapping - Actual Business logic

 */