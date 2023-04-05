package controller;

import modal.Category;
import modal.Song;
import service.category.CategoryServiceIMPL;
import service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    private ICategoryService categoryService =new CategoryServiceIMPL();
    public List<Category> getListCategory() {
        return categoryService.findAll();
    }

    public void createCategory(Category category) {
        categoryService.save(category);
    }
    public void updateCategory(Category category){
        categoryService.save(category);
    }
    public Category detailCategory(int id){
        return categoryService.findById(id);
    }
    public void  deleteCategory(int id){
        categoryService.deleteById(id);
    }
}
