package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.models.Product;
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
    public void deleteProductById(Long id) {

    }

    @Override
    public Product addProduct(Product product){
        return null;
    }

    @Override
    public void updateProductById() {

    }
}
