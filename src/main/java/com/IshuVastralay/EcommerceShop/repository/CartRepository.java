package com.IshuVastralay.EcommerceShop.repository;

import com.IshuVastralay.EcommerceShop.model.Cart;
import com.IshuVastralay.EcommerceShop.model.CartItem;
import com.IshuVastralay.EcommerceShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c From Cart c where c.user.id=:userId")
    public Cart findByUserId(@Param("userId")Long userId);

}
