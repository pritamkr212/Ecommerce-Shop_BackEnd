package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Category;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.CategoryRepository;
import com.IshuVastralay.EcommerceShop.repository.ProductRepository;
import com.IshuVastralay.EcommerceShop.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ProductServiceImplementation implements ProductService{

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;
    public ProductServiceImplementation(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {

        Category topLevel=categoryRepository.findByName(createProductRequest.getTopLevelCategory());
        if(topLevel!=null){
            Category topLevelCategory=new Category();
            topLevelCategory.setName(createProductRequest.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel=categoryRepository.save(topLevelCategory);
        }

        Category secondLevel=categoryRepository.findByNameAndParent(createProductRequest.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel!=null){
            Category secondLevelCategory=new Category();
            secondLevelCategory.setName(createProductRequest.getTopLevelCategory());
            secondLevelCategory.setLevel(2);
            secondLevel=categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParent(createProductRequest.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel!=null){
            Category thirdLevelCategory=new Category();
            thirdLevelCategory.setName(createProductRequest.getTopLevelCategory());
            thirdLevelCategory.setLevel(2);
            thirdLevel=categoryRepository.save(thirdLevelCategory);
        }
        Product product=new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setColor(createProductRequest.getColor());
        product.setDescription(createProductRequest.getDescription());
        product.setDiscountedPrice(createProductRequest.getDiscountedPrice());
        product.setDiscountPercent(createProductRequest.getDiscountPercentage());
        product.setImageUrl(createProductRequest.getImageUrl());
        product.setBrand(createProductRequest.getBrand());
        product.setPrice(createProductRequest.getPrice());
        product.setQuantity(createProductRequest.getQuantity());
        product.setCateogory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct=productRepository.save(product);
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        return null;
    }

    @Override
    public Product updateProduct(Long productId) throws ProductException {
        return null;
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findProductCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, String miniDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
