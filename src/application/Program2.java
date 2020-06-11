package application;

import java.util.List;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;
import model.entities.Movie;

public class Program2 {

	public static void main(String[] args) {
		CategoryDao categoryDao = DaoFactory.createCategoryDao();
		
		
		System.out.println("Category test 1#: selectAll");
		List<Category> list = categoryDao.selectAll();
		list.forEach(System.out::println);
		
		System.out.println("\nCategory test 2#: selectById");
		Category cat = categoryDao.selectById(1);
		System.out.println(cat);
		

	}

}
