package com.esliceu.securityWeb.Service;

import com.esliceu.securityWeb.Dto.*;
import com.esliceu.securityWeb.Model.*;
import com.esliceu.securityWeb.Repository.CategoryRepo;
import com.esliceu.securityWeb.Repository.ReplyRepo;
import com.esliceu.securityWeb.Repository.TopicsRepo;
import com.esliceu.securityWeb.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.Random;

@Service
public class CategoryService {

    CategoryRepo categoryRepo;
    TopicsRepo topicsRepo;
    ReplyRepo replyRepo;
    UserRepo userRepo;

    UserService userService;

    @Autowired
    public CategoryService(UserRepo userRepo,CategoryRepo categoryRepo, TopicsRepo topicsRepo, ReplyRepo replyRepo,
    UserService userService ) {
        this.categoryRepo = categoryRepo;
        this.topicsRepo = topicsRepo;
        this.replyRepo = replyRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }
    public Categories save(Categories categories, String usuario) {
        Random r = new Random();
        int num = r.nextInt(256);
        User idUsuario = userRepo.findByEmail(usuario);
        Category category = new Category();
        category.setColor("hsl(" + num + ",50%,50%)");
        category.setTitle(categories.getTitle());
        category.setDescription(categories.getDescription());
        category.setSlug(categories.getTitle().toLowerCase().replace(" ", "-"));
        category.setUser(idUsuario);
        categoryRepo.save(category);

        Category ct = categoryRepo.findByTitle(categories.getTitle());
        Categories ctDto = new Categories();
        ctDto.setId(ct.getId());
        ctDto.setSlug(ct.getSlug());
        ctDto.setTitle(ct.getTitle());
        ctDto.setDescription(ct.getDescription());
        ctDto.setColor(ct.getColor());
        return ctDto;


    }
    public ArrayList<Categories> allCategories() {
        ArrayList<Category> list =  categoryRepo.findAll();
        ArrayList<Categories> listCate = new ArrayList<>();
        for (Category c: list) {
            Categories one = new Categories(c);
            listCate.add(one);
        }
        return listCate;
    }
    public Categories findBySlug(String categorySlug) {
        Category oneCate = categoryRepo.findBySlug(categorySlug);
        return new Categories(oneCate);
    }
    public void update(Categories categories, String categorySlug) {
        Category before = categoryRepo.findBySlug(categorySlug);
        before.setTitle(categories.getTitle());
        before.setDescription(categories.getDescription());
        categoryRepo.save(before);
    }
    public void deleteBySlug(String categorySlug) {
        Integer idSlug = categoryRepo.findBySlug(categorySlug).getId();
        categoryRepo.deleteById(idSlug);
    }


    public topicDto saveTopic(Topics topics, String usuario) {
        Category category = categoryRepo.findBySlug(topics.getCategory());
        User user = userRepo.findByEmail(String.valueOf(usuario));
        LocalDateTime dateTime = LocalDateTime.now();
        Topic t = new Topic();
        t.setTitle(topics.getTitle());
        t.setContent(topics.getContent());
        t.setViews(0);
        t.setCreatedAt(dateTime);
        t.setUpdateAt(dateTime);
        t.setCategory(category);
        t.setUser(user);
        topicsRepo.save(t);


        Topic topicM = topicsRepo.findByTitle(topics.getTitle());
        Long numberOfReplies = replyRepo.countAllByCategory(topicM.getCategory());
        getprofile getprofile = new getprofile();
        getprofile.setId(String.valueOf(topicM.getUser().getId()));

        topicDto tDto = new topicDto();
        tDto.setCategory(topicM.getCategory().getId());
        tDto.setContent(topics.getContent());
        tDto.setCreatedAT(String.valueOf(topicM.getCreatedAt()));
        tDto.setId(topicM.get_id());
        tDto.setNumberOfReplies(numberOfReplies);
        tDto.setUser(getprofile);
        tDto.setTitle(t.getTitle());
        tDto.setUpdatedAt(String.valueOf(t.getUpdateAt()));
        return  tDto;
    }
    public ArrayList<topicDto> findTopicsCategory(String categorySlug) {
        Category category = categoryRepo.findBySlug(categorySlug);
        ArrayList<Topic> listTopics = topicsRepo.findAllByCategory(category);
        Long numberOfReplies = replyRepo.countAllByCategory(category);
        ArrayList<topicDto> listTopicDto = new ArrayList<>();
        for (Topic t: listTopics) {
            getprofile getprofile = new getprofile(t.getUser());

            topicDto topicDto = new topicDto();
            topicDto.setCategory(t.getCategory().getId());
            topicDto.setContent(t.getContent());
            topicDto.setCreatedAT(String.valueOf(t.getCreatedAt()));
            topicDto.setId(t.get_id());
            topicDto.setNumberOfReplies(numberOfReplies);
            topicDto.setUser(getprofile);
            topicDto.setTitle(t.getTitle());
            topicDto.setUpdatedAt(String.valueOf(t.getUpdateAt()));
            listTopicDto.add(topicDto);
        }
        return listTopicDto;
    }
    public replyDto getTopic(String topicId) {

        Topic before = topicsRepo.findBy_id(Integer.valueOf(topicId));
        ArrayList<Reply> listReplies =  replyRepo.findAllByTopic(before);
        getprofile getprofile = new getprofile(before.getUser());
        Categories categories = new Categories(before.getCategory());
        return new replyDto(categories,before,getprofile,listaReply(listReplies));
    }
    public ArrayList<replyDto>  listaReply(ArrayList<Reply> list) {
        ArrayList<replyDto> newList = new ArrayList<>();
        if (!list.isEmpty()){
            for (Reply r: list) {
                replyDto replyDto = new replyDto();
                replyDto.setId(r.get_id());
                replyDto.setContent(r.getContent());
                replyDto.setCreatedAt(String.valueOf(r.getCreateAt()));
                replyDto.setTopic(String.valueOf(r.getTopic().get_id()));
                replyDto.setUpdatedAt(String.valueOf(r.getUpdateAt()));

                getprofile getprofile = new getprofile(r.getUser());
                replyDto.setUser(getprofile);

                newList.add(replyDto);
            }
        }
        return newList;
    }
    public void putTopic(String topicId, Topics topics) {
        LocalDateTime dateTime = LocalDateTime.now();
        Category category = categoryRepo.findByTitle(topics.getCategory());
        Topic before = topicsRepo.findBy_id(Integer.valueOf(topicId));
        before.setTitle(topics.getTitle());
        before.setCategory(category);
        before.setContent(topics.getContent());
        before.setUpdateAt(dateTime);
        topicsRepo.save(before);
    }
    public void deleteTopic(String topicId) {
        Topic before = topicsRepo.findBy_id(Integer.valueOf(topicId));
        ArrayList<Reply> listReplies =  replyRepo.findAllByTopic(before);
        if(!listReplies.isEmpty()){
            for (Reply r: listReplies) {
                    replyRepo.deleteById(r.get_id());
            }
        }
       topicsRepo.deleteById(Integer.valueOf(topicId));
    }


    public void saveReply(replyDto replyDto, String topicId, String email) {
        User user = userRepo.findByEmail(email);
        Topic topic = topicsRepo.findBy_id(Integer.valueOf(topicId));
        Category category = categoryRepo.findByTitle(topic.getCategory().getTitle());
        LocalDateTime dateTime = LocalDateTime.now();
        Reply reply = new Reply();
        reply.setContent(replyDto.getContent());
        reply.setCreateAt(dateTime);
        reply.setUpdateAt(dateTime);
        reply.setUser(user);
        reply.setCategory(category);
        reply.setTopic(topic);
        replyRepo.save(reply);
    }
    public replyDto replyPost(replyDto replyDto) {
        Reply reply = replyRepo.findByContent(replyDto.getContent());
        replyDto.setContent(reply.getContent());
        replyDto.setId(reply.get_id());
        replyDto.setCreatedAt(String.valueOf(reply.getCreateAt()));
        replyDto.setTopic(String.valueOf(reply.getTopic().get_id()));
        replyDto.setUpdatedAt(String.valueOf(reply.getUpdateAt()));
        getprofile getprofile = new getprofile(reply.getUser());
        replyDto.setUser(getprofile);
        return  replyDto;
    }
    public replyDto putReply(String replyId, replyDto replyDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        Reply reply = replyRepo.findBy_id(Integer.valueOf(replyId));
        reply.setContent(replyDto.getContent());
        reply.setUpdateAt(dateTime);
        replyRepo.save(reply);
        replyDto rDto = new replyDto();
        rDto.setContent(replyDto.getContent());
        rDto.setCreatedAt(String.valueOf(reply.getCreateAt()));
        rDto.setTopic(String.valueOf(reply.getTopic().get_id()));
        rDto.setUpdatedAt(String.valueOf(reply.getUpdateAt()));
        getprofile getprofile = new getprofile(reply.getUser());
        rDto.setUser(getprofile);
        return rDto;
    }


    public void delteRely(String replyId) {
        replyRepo.deleteById(Integer.valueOf(replyId));
    }
}
