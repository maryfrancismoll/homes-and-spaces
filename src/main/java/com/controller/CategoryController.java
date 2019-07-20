package com.controller;

import com.domain.Category;
import com.model.Message;
import com.service.CategoryService;
import com.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //get all categories
    @GetMapping
    @ResponseBody
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //add new category
    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        boolean isSuccessful = categoryService.saveNewCategory(category);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully created new category."), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new Message("Failed to create new category."), HttpStatus.BAD_REQUEST);
    }

    //update category
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        boolean isSuccessful = categoryService.updateCategory(category);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully updated category."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Message("Failed to update category."), HttpStatus.BAD_REQUEST);
    }

    //delete category
    @DeleteMapping (value = "{id}")
    @ResponseBody
    @PreAuthorize(("hasAuthority('ADMIN')"))
    public ResponseEntity<?> deleteDategory(@PathVariable Integer id){
        boolean isSuccessful = categoryService.deleteCategory(id);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully deleted category."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Message("Failed to delete category."), HttpStatus.BAD_REQUEST);
    }
}
