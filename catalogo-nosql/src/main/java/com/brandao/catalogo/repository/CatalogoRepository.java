package com.brandao.catalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brandao.catalogo.collection.Catalogo;

@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo, String> {

}
