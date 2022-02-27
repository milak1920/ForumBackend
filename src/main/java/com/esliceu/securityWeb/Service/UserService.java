package com.esliceu.securityWeb.Service;

import com.esliceu.securityWeb.Dto.Permissions;
import com.esliceu.securityWeb.Dto.getprofile;
import com.esliceu.securityWeb.Dto.password;
import com.esliceu.securityWeb.Dto.register;
import com.esliceu.securityWeb.Model.Category;
import com.esliceu.securityWeb.Model.Role;
import com.esliceu.securityWeb.Model.User;
import com.esliceu.securityWeb.Repository.CategoryRepo;
import com.esliceu.securityWeb.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

/**
 * getprofileUser ERROR AL LOGGEAR
 */
@Service
@Transactional
public class UserService {
    UserRepo userRepo;
    CategoryRepo categoryRepo;
    @Autowired
    public UserService(UserRepo userRepo, CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public void save(register register, String encode) {
        User newUser = new User();
        newUser.setName(register.getName());
        newUser.setEmail(register.getEmail());
        newUser.setPassword(encode);
        newUser.setRole(Role.admin);
        userRepo.save(newUser);
    }
    public User getByEmail(String userName) {
        return userRepo.findByEmail(userName);
     }
     //OK
    public getprofile getprofileUser(String usuario) {
        User user = userRepo.findByEmail(usuario);


        getprofile profile = new getprofile();

        if(user.getAvatar() == null ){
            profile.setAvartUrl(null);
        }else{
            profile.setAvartUrl(nameUrlAvatar());
        }
        profile.setId(String.valueOf(user.getId()));
        profile.setEmail(user.getEmail());
        profile.setName(user.getName());
        profile.setRole(String.valueOf(user.getRole()));
        profile.setPermissions(permissionPerfi());
        return profile;
    }
    //OK
    public HashMap<String, String[]> listaCategori(ArrayList<Category> list) {
        String[] categories = {
                "categories_topics:write",
                "categories_topics:delete",
                "categories_replies:write ",
                "categories_replies:delete "};
        HashMap<String, String[]> mapCate = new HashMap<>();
        for (Category c: list) {
            mapCate.put(c.getTitle(), categories);
        }
        return mapCate;
    }
    public getprofile upProfile(getprofile getprofile, String email) {

        User user = userRepo.findByEmail(email);
        if(!getprofile.getEmail().isEmpty()) user.setEmail(getprofile.getEmail());
        if(!getprofile.getName().isEmpty()) user.setName(getprofile.getName());
        if(!getprofile.getAvatar().isEmpty()){
            user.setAvatar(imageBlob(getprofile.getAvatar()));
        }
        userRepo.save(user);


        getprofile profile = new getprofile();
        profile.setAvartUrl(nameUrlAvatar());
        profile.setId(String.valueOf(user.getId()));
        profile.setEmail(user.getEmail());
        profile.setName(user.getName());
        profile.setRole(String.valueOf(user.getRole()));
        profile.setPermissions(permissionPerfi());
        return profile;

    }




    //OK
    private Permissions permissionPerfi() {
        ArrayList<Category> allCategories =  categoryRepo.findAll();

        String[] root =  {
                "own_topics:write",
                "own_topics:delete",
                "own_replies:write",
                "own_replies:delete",
                "categories:write",
                "categories:delete"};

        Permissions p = new Permissions();
        p.setRoot(root);
        p.setCategories(listaCategori(allCategories));
        return p;
    }
    //OK
    public String nameUrlAvatar() {
        LocalDateTime dateTime = LocalDateTime.now();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 3; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return "http://localhost:8080//images/users/" + sb.toString() + dateTime  + ".png";

    }

    private byte[] imageBlob(String avartUrl) {
        String base64Image = avartUrl.split(",")[1];
        return Base64.getDecoder().decode(base64Image);
    }

    private String imagenEncode(byte[] avatar) {

       String foto = Base64.getEncoder().encodeToString(avatar);
       return  "data:image/jpg;base64," + foto;
    }


    public void newPassword(String encode, User user) {
        user.setPassword(encode);
        userRepo.save(user);
    }
}