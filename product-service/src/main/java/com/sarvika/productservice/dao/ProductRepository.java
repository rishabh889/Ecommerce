package com.sarvika.productservice.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.sarvika.productservice.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
