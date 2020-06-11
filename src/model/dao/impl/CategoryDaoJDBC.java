package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.dao.CategoryDao;
import model.entities.Category;

public class CategoryDaoJDBC implements CategoryDao {
	private Connection conn;
	
	public CategoryDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Category obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Category instantiateCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("Id"));
		category.setName(rs.getString("CatName"));
		
		return category;
	}
}
