package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Category;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.CategoryRepository;
import com.IshuVastralay.EcommerceShop.repository.ProductRepository;
import com.IshuVastralay.EcommerceShop.request.CreateProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{
    public ProductRepository productRepository;
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

        if(topLevel==null) {

            Category topLevelCategory=new Category();
            topLevelCategory.setName(createProductRequest.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel= categoryRepository.save(topLevelCategory);
        }

        Category secondLevel=categoryRepository.
                findByNameAndParent(createProductRequest.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel==null) {

            Category secondLevelCategory=new Category();
            secondLevelCategory.setName(createProductRequest.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel= categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParent(createProductRequest.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel==null) {

            Category thirdLevelCategory=new Category();
            thirdLevelCategory.setName(createProductRequest.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

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
        product.setSizes(createProductRequest.getSizeSet());
        product.setQuantity(createProductRequest.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct= productRepository.save(product);

        System.out.println("products - "+product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product=findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product Deleted SuccessFully";
    }

    @Override
    public Product updateProduct(Long productId,Product product) throws ProductException {
        Product tempProduct=findProductById(productId);
        if(product.getQuantity()!=0){
            tempProduct.setQuantity(product.getQuantity());
        }
        return productRepository.save(tempProduct);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product>opt=productRepository.findById(id);
        if(opt.isPresent())return opt.get();
        throw new ProductException("Product Is not found-"+id);
    }

    @Override
    public List<Product> findProductCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer miniDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        List<Product>productList=productRepository.filterProducts(category,minPrice,maxPrice,miniDiscount,sort);
        if(!colors.isEmpty()){
            productList=productList.stream()
                    .filter(p->colors.stream().anyMatch(c-> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }
        if(stock!=null){
            if (stock.equals("in_stock")){
                productList=productList.stream().filter(p-> p.getQuantity()>0).collect(Collectors.toList());
            }
            else if(stock.equals("out_of_stock")){
                productList=productList.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex=(int)pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),productList.size());
        List<Product>pageContent=productList.subList(startIndex,endIndex);
        Page<Product>filterProduct=new PageImpl<>(pageContent,pageable, productList.size());
        return filterProduct;

    }
}
