package com.example.productservicejanbatch.services;

import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Product;

import java.util.List;


public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    void deleteProductById(Long id);

    Product addProduct(Product product);

    void updateProductById();

}
