package com.esliceu.securityWeb.Controller;

import com.esliceu.securityWeb.Dto.Categories;
import com.esliceu.securityWeb.Model.UserMain;
import com.esliceu.securityWeb.Service.CategoryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@ResponseBody
public class CategoryController {
    Gson gson = new Gson();

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategory() {
        ArrayList<Categories> getcategories = categoryService.allCategories();
        return gson.toJson(getcategories);
    }

    @GetMapping("/categories/{categorySlug}")
    public String getCategorySlug(@PathVariable String categorySlug) {
        Categories oneCategory = categoryService.findBySlug(categorySlug);
        return gson.toJson(oneCategory);
    }

    @PostMapping("/categories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String  PostNewCategory(@Valid @RequestBody Categories categories, Authentication authentication) {
        UserMain usuario = (UserMain) authentication.getPrincipal();
        Categories oneCategory = categoryService.save(categories,usuario.getEmail());
        return gson.toJson(oneCategory);
    }

    @PutMapping("/categories/{categorySlug}")
    @ResponseStatus(value = HttpStatus.OK)
    public void PutCategorySlug(@Valid @RequestBody Categories categories,@PathVariable String categorySlug) {
        categoryService.update(categories, categorySlug);
    }

    @DeleteMapping("/categories/{categorySlug}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategorySlug(@PathVariable String categorySlug) {
        categoryService.deleteBySlug(categorySlug);
    }


}
