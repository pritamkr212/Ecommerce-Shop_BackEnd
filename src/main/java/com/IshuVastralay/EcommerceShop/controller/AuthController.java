package com.IshuVastralay.EcommerceShop.controller;

import com.IshuVastralay.EcommerceShop.authResponse.AuthResponse;
import com.IshuVastralay.EcommerceShop.config.JwtProvider;
import com.IshuVastralay.EcommerceShop.exception.UserException;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.UserRepository;
import com.IshuVastralay.EcommerceShop.request.LoginRequest;
import com.IshuVastralay.EcommerceShop.service.CustomUserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private CustomUserServiceImplementation customUserServiceImplementation;
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    public AuthController(UserRepository userRepository,CustomUserServiceImplementation customUserServiceImplementation,PasswordEncoder passwordEncoder,JwtProvider jwtProvider){
        this.customUserServiceImplementation=customUserServiceImplementation;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtProvider=jwtProvider;
    }
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{
        String email=user.getEmail();
        String password=user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        User isEmailExist=userRepository.findByEmail(email);
        if(isEmailExist!=null){
            throw new UserException("Email is Already Exist With Another Account");
        }
        User newUser=new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        User savedUser=userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Success");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);


    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
        String userName=loginRequest.getEmail();
        String userPassword=loginRequest.getPassword();
        Authentication authentication=Authenticate(userName,userPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signin Success");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

    }

    private Authentication Authenticate(String userName, String userPassword) {
        UserDetails userDetails= customUserServiceImplementation.loadUserByUsername(userName);
        if(userDetails==null){
            throw new BadCredentialsException("Username Doesn't Exist");
        }
        if(!passwordEncoder.matches(userPassword,userDetails.getPassword())){
            throw new BadCredentialsException("Invaild Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
