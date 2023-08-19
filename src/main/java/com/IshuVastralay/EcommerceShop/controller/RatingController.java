package com.IshuVastralay.EcommerceShop.controller;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.exception.UserException;
import com.IshuVastralay.EcommerceShop.model.Rating;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.request.RatingRequest;
import com.IshuVastralay.EcommerceShop.service.RatingService;
import com.IshuVastralay.EcommerceShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ranges.RangeException;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating>createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {
        User user=userService.findUserProfileByJwt(jwt);
        Rating rating=ratingService.createRating(request,user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>>getProductsRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt)throws UserException,ProductException{
        User user=userService.findUserProfileByJwt(jwt);
        List<Rating> ratings=ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.CREATED);
    }
}
