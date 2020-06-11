package application;

import java.util.List;
import java.util.Scanner;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CategoryDao categoryDao = DaoFactory.createCategoryDao();
		
		
		System.out.println("Category test 1#: selectAll");
		List<Category> list = categoryDao.selectAll();
		list.forEach(System.out::println);
		
		System.out.println("\nCategory test 2#: selectById");
		Category cat = categoryDao.selectById(1);
		System.out.println(cat);

		System.out.println("\nCategory test 3#: insert");
		Category newCat = new Category(null,"Animation");
		//categoryDao.insert(newCat);
		System.out.println("Insert completed!");
		
		System.out.println("\n Category test 4#: update");
		cat = categoryDao.selectById(1);
		cat.setName("Action");
		//categoryDao.update(cat);
		System.out.println("Update completed!");
		
		System.out.println("\n Category test 5#: delete");
		int id = sc.nextInt();
		categoryDao.deleteById(id);
		System.out.println("Delete completed!");
		sc.close();
	}

}
