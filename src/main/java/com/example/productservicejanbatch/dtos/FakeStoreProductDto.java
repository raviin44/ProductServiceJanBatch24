package com.example.productservicejanbatch.dtos;

import com.example.productservicejanbatch.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String description;
    private Long price;
    private String category;

}
