package com.esliceu.securityWeb.Repository;


import com.esliceu.securityWeb.Model.Category;
import com.esliceu.securityWeb.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface TopicsRepo extends JpaRepository<Topic, Integer> {
    ArrayList<Topic> findAllByCategory(Category category);

    Topic findBy_id(Integer valueOf);

    Topic findByTitle(String title);
}
