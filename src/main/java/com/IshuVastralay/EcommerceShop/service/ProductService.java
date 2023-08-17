package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest createProductRequest);
    public String deleteProduct(Long productId)throws ProductException;
    public Product updateProduct(Long productId, Product product)throws ProductException;
    public Product findProductById(Long id)throws ProductException;
    public List<Product> findProductCategory(String category);
    public Page<Product> getAllProduct(String category,List<String>colors,List<String>sizes,Integer minPrice,Integer maxPrice
    ,Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);
}
