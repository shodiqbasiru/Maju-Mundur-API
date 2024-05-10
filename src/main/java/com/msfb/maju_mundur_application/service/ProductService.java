package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.ProductRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateProductRequest;
import com.msfb.maju_mundur_application.dto.response.ProductResponse;
import com.msfb.maju_mundur_application.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest request);
    Product getById(String id);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAllProduct();
    ProductResponse updateProduct(UpdateProductRequest request);
    void deleteProduct(String id);
}
