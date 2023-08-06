package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.UserException;
import com.IshuVastralay.EcommerceShop.model.User;

public interface UserService {

    public User findUserById(Long userId)throws UserException;
    public User findUserProfileByJwt(String jwt)throws UserException;

}
