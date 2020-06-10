package model.dao;

import java.util.List;

import model.entities.Category;

public interface CategoryDao {
	
	void insert(Category obj);
	void update(Category obj);
	void deleteById(Integer id);
	Category selectById(Integer id);
	List<Category> selectAll();
}
