package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
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
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement( 
					"SELECT * from category WHERE Id = ?"
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Category cat = instantiateCategory(rs);
				return cat;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Category> selectAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement( 
					"SELECT * FROM category ORDER BY Name"
					);
			
			rs = st.executeQuery();
			List<Category> list = new ArrayList<>();
			while(rs.next()) {
				Category cat = instantiateCategory(rs);
				list.add(cat);
			}
			
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	public Category instantiateCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("Id"));
		category.setName(rs.getString("Name"));
		
		return category;
	}
}
