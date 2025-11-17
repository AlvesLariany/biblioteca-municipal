package com.cadastralivro.cadastralivro.infraestructure.repository;

import com.cadastralivro.cadastralivro.infraestructure.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LivroJpa extends JpaRepository<LivroEntity, Integer> {

    Optional<LivroEntity> findById(Integer id);
    Optional<LivroEntity> findByIsbn(String isbn);
    List<LivroEntity> findAll();
    void deleteById(Integer id);


}