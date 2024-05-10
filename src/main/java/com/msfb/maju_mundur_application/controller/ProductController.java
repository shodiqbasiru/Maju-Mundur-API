package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.ProductRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateProductRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.ProductResponse;
import com.msfb.maju_mundur_application.entity.Product;
import com.msfb.maju_mundur_application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_PRODUCT)
@Tag(name = "Product", description = "API for product")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Create",
            description = "Create new product"
    )
    @SecurityRequirement(name = "Authorization")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<Product>> create(@RequestBody ProductRequest request) {
        Product product = productService.createProduct(request);
        CustomResponse<Product> response = CustomResponse.<Product>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Created product successfully")
                .data(product)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get All",
            description = "Get all product"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<List<ProductResponse>>> getAll() {
        List<ProductResponse> products = productService.getAllProduct();
        CustomResponse<List<ProductResponse>> responses = CustomResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all product successfully")
                .data(products)
                .build();
        return ResponseEntity.ok(responses);
    }

    @Operation(
            summary = "Get By Id",
            description = "Get product by id"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<ProductResponse>> getProductById(@PathVariable String id) {
        ProductResponse product = productService.getProductById(id);
        CustomResponse<ProductResponse> response = CustomResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get product successfully")
                .data(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update",
            description = "Update product"
    )
    @SecurityRequirement(name = "Authorization")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<ProductResponse>> update(@RequestBody UpdateProductRequest request) {
        ProductResponse product = productService.updateProduct(request);
        CustomResponse<ProductResponse> response = CustomResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Updated product successfully")
                .data(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete",
            description = "Delete product by id"
    )
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse<Product>> delete(@PathVariable String id) {
        productService.deleteProduct(id);
        CustomResponse<Product> response = CustomResponse.<Product>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted product successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
