package com.esliceu.securityWeb.Dto;


import java.util.HashMap;
 public class Permissions {

     private String root [];
     HashMap<String, String[]> categories = new HashMap<String, String[]>();

     public String[] getRoot() {
         return root;
     }

     public void setRoot(String[] root) {
         this.root = root;
     }

     public HashMap<String, String[]> getCategories() {
         return categories;
     }

     public void setCategories(HashMap<String, String[]> categories) {
         this.categories = categories;
     }
 }
