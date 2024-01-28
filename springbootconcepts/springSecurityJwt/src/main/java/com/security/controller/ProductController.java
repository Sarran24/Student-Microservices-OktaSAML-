package com.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthRequest;
import com.security.dto.Product;
import com.security.model.UserInfo;
import com.security.service.JwtService;
import com.security.service.ProductService;
import com.security.service.UserInfoService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
		Optional<Product> product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/get")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> products = productService.getAllProduct();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/saveUser")
	public ResponseEntity<UserInfo> saveUserInfo(@RequestBody UserInfo userInfo) {
		UserInfo userInfoRepond = userInfoService.saveUserInfo(userInfo);
		return new ResponseEntity<>(userInfoRepond, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());

		}else {
			throw new UsernameNotFoundException("Invalid user");
		}
	}
}
