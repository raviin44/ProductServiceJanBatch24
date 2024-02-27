package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.dtos.FakeStoreProductDto;
import com.example.productservicejanbatch.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
// @Qualifier("SelfProductService")
public class ProductServiceImpl implements  ProductService{

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        return null;
    }

    @Override
    public Product deleteProductById(Long id) {

        return null;
    }

    @Override
    public Product addProduct(Product product){
        return null;
    }

    @Override
    public void updateProductById() {

    }
}
