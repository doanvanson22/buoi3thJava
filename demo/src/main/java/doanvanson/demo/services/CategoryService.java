package doanvanson.demo.services;

import doanvanson.demo.entity.Category;
import doanvanson.demo.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories(){return categoryRepository.findAll();}

    public Category getCategoryById(Long id){return categoryRepository.findById(id).orElse(null);}

    public Category saveCategory(Category category){ return categoryRepository.save(category);}

    public void deleteCategory(Long id){categoryRepository.deleteById(id);}

    public void addCategory(Category category) {categoryRepository.save(category);
    }
}
