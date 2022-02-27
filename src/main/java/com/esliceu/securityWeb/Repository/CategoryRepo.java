package com.esliceu.securityWeb.Repository;


import com.esliceu.securityWeb.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findByTitle(String prueba);
    Category findBySlug(String editText);
    ArrayList<Category> findAll();

 }
