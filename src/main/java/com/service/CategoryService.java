package com.service;

import com.domain.Category;
import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SpaceService spaceService;

    //get all categories
    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categoryList.add(category));
        return categoryList;
    }

    //update category
    public boolean updateCategory(Category category){
        boolean successful = false;
        try{
            category = categoryRepository.save(category);
            successful = true;
        }finally{

        }

        return successful;
    }

    //delete category
    @Transactional
    public boolean deleteCategory(Integer id){
        boolean successful = false;

        //delete space with this category
        if(!spaceService.deleteSpaceByCategory(id)){
            return false;
        }

        try{
            Category category = categoryRepository.findById(id).get();
            categoryRepository.delete(category);
            successful = true;
        }finally{

        }

        return successful;
    }

    //create new
    public boolean saveNewCategory(Category category){
        boolean successful = false;
        try{
            category = categoryRepository.save(category);
            successful = true;
        }finally{

        }

        return successful;
    }
}
