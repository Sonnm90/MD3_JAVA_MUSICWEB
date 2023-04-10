package service.category;

import config.Config;
import modal.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    List<Category> categoryList = new Config<Category>().readFromFile(Config.PATH_CATEGORY);
//    public static List<Category> categoryList = new ArrayList<>();
//    static {
//        categoryList.add(new Category(0,"Nhac tre"));
//        categoryList.add(new Category(1,"Nhac vang"));
//    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        if (findById(category.getId())==null) {
            categoryList.add(category);
        } else {
            categoryList.set(categoryList.indexOf(findById(category.getId())),category);
        }
        new Config<Category>().writeToFile(Config.PATH_CATEGORY, categoryList);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId()==id){
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId()==id){
                categoryList.remove(i);
                new Config<Category>().writeToFile(Config.PATH_CATEGORY,categoryList);
            }
        }
    }
}
