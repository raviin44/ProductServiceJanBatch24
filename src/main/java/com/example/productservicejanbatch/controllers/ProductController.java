package com.example.productservicejanbatch.controllers;

import com.example.productservicejanbatch.exceptions.ProductNotFoundException;
import com.example.productservicejanbatch.models.Product;
import com.example.productservicejanbatch.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

   /* @Autowired
    public setProductService(ProductService productService){
        this.productService = productService;
    }*/

    @GetMapping("/{id}")
    public Product getProductId(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

   /* @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
         productService.deleteProductById(id);
    }*/

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    // 1st way - Best way
   /* @ExceptionHandler(ProductNotFoundException.class)   // handles on Comtroller level
    @ResponseStatus(HttpStatus.NOT_FOUND) // Right Status code
    @ResponseBody  // Good Practice to return body
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        return exceptionDto;
    } */

    // 2nd way
  /*   @ExceptionHandler(ProductNotFoundException.class)   // handles on Comtroller level
   // @ResponseStatus(HttpStatus.NOT_FOUND) // Right Status code
    // @ResponseBody  // Good Practice to return body
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
     }  */

}

// end point is s combination of Domain name + Path of Entity API

/**
 * www.xyz.com/api/......
 * Endpoint is nothing but a combination of Domain Name + Path of Entity API
 */

/**
 * 1. GetProductById(Id)
 * 2. GetAllProducts
 * 3. UpdateProductById()
 * 4. DeleteProduct(id)
 * 5. AddProduct()
 */

/**
 - Construtor Injection - Best way to use
 - Filed Injection      - not recommended, readability issue in case of huge service list
 - Setter Injection     - not recommended , readability AND while writing Unit Test cases can see NPE.
 */