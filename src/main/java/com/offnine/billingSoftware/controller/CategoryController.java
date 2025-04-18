package com.offnine.billingSoftware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offnine.billingSoftware.io.CategoryRequest;
import com.offnine.billingSoftware.io.CategoryResponse;
import com.offnine.billingSoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestPart("category") String categoryString, @RequestPart("file") MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryRequest request = null;
        try{
            request = objectMapper.readValue(categoryString,CategoryRequest.class);
            return  categoryService.add(request,file);

        }
        catch (JsonProcessingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Exception Ocurred while parsing JSON");
        }
    }

    @GetMapping
    public List<CategoryResponse> fetchCategories (){
        return categoryService.read();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
 @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId){
        try {
            categoryService.delete(categoryId);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"+categoryId);
        }
 }

}
