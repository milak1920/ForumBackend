package com.esliceu.securityWeb.Repository;


import com.esliceu.securityWeb.Model.Category;
import com.esliceu.securityWeb.Model.Reply;
import com.esliceu.securityWeb.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Integer> {
    Long countAllByCategory(Category category);
    ArrayList<Reply> findAllByTopic(Topic topic);
    Reply findByContent(String content);
    Reply findBy_id(Integer id);
}
