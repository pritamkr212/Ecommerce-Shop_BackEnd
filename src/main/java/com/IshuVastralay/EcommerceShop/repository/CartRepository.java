package com.IshuVastralay.EcommerceShop.repository;

import com.IshuVastralay.EcommerceShop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
