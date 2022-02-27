package com.esliceu.securityWeb.Controller;

import com.esliceu.securityWeb.Dto.Topics;
import com.esliceu.securityWeb.Dto.replyDto;
import com.esliceu.securityWeb.Dto.topicDto;
import com.esliceu.securityWeb.Model.UserMain;
import com.esliceu.securityWeb.Service.CategoryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class TopicsController {
    Gson gson = new Gson();

    CategoryService categoryService;

    @Autowired
    public TopicsController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping("/categories/{categorySlug}/topics")
    public String getCategoryTopics(@PathVariable String categorySlug ) {
       ArrayList<topicDto> getTopicsInCategory = categoryService.findTopicsCategory(categorySlug);
       return gson.toJson(getTopicsInCategory);

    }

    @PostMapping("/topics")
    public String PostNewCategory(@Valid @RequestBody Topics topics, Authentication authentication ) {
        UserMain usuario = (UserMain) authentication.getPrincipal();
        topicDto topicDto = categoryService.saveTopic(topics, usuario.getEmail());
        return gson.toJson(topicDto);

    }

    @GetMapping("/topics/{topicId}")
    public String getTopic(@PathVariable String topicId ) {
        replyDto getTopicInCategory = categoryService.getTopic(topicId);
        return gson.toJson(getTopicInCategory);
    }

    @PutMapping("/topics/{topicId}")
    public void putTopic(@Valid @RequestBody Topics topics, @PathVariable String topicId) {
        categoryService.putTopic(topicId, topics);
    }

    @DeleteMapping("/topics/{topicId}")
    public void deleteTopic(@PathVariable String topicId) {
        categoryService.deleteTopic(topicId);
    }


    @PostMapping("/topics/{topicId}/replies")
    public String createReply(@Valid @RequestBody replyDto replyDto,
                              @PathVariable String topicId,Authentication authentication ) {
        UserMain usuario = (UserMain) authentication.getPrincipal();
        categoryService.saveReply(replyDto, topicId, usuario.getEmail());
        replyDto replyPostDto = categoryService.replyPost(replyDto);
        return gson.toJson(replyPostDto);
    }


    @PutMapping("/topics/{topicId}/replies/{replyId}")
    public String putReply(@Valid @RequestBody replyDto replyDto,@PathVariable String replyId) {
        replyDto replyPut =  categoryService.putReply(replyId,replyDto);
        return gson.toJson(replyPut);
    }

    @DeleteMapping("/topics/{topicId}/replies/{replyId}")
    public void deleteReply(@PathVariable String replyId) {
       categoryService.delteRely(replyId);
    }

}
