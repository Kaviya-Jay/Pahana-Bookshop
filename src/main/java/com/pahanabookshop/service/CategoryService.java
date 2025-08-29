package com.pahanabookshop.service;

import java.util.List;

import com.pahanabookshop.model.Category;

public interface CategoryService {

    public Category saveCategory(Category category);

    public Boolean existCategory(String name);

    public List<Category> getAllCategory();

    public Boolean deleteCategory(int id);

    public Category getCategoryById(int id);

}
