package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.MovieDao;
import model.entities.Category;
import model.entities.Movie;

public class MovieDaoJDBC implements MovieDao{

	private Connection conn;
	
	public MovieDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Movie obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(  
					"INSERT INTO movie "
				+ "(Name, Description, DateOfRelease, CategoryId) "
				+ "VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
					);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());
			st.setDate(3, new java.sql.Date(obj.getDateOfRelease().getTime()));
			st.setInt(4, obj.getCategory().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Movie obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement( 
					"UPDATE movie SET Name = ?, "
					+ "Description = ?, "
					+ "DateOfRelease = ?, "
					+ "CategoryId = ? "
					+ "WHERE Id = ?"
					);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());
			st.setDate(3, new java.sql.Date(obj.getDateOfRelease().getTime()));
			st.setInt(4, obj.getCategory().getId());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(  
					"DELETE FROM movie WHERE Id = ?"
					);
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Movie selectById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT m.*, c.Name as CatName "
					+ "FROM movie m "
					+ "INNER JOIN category c "
					+ "ON m.CategoryId = c.Id "
					+ "WHERE m.Id = ?"
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Category category = instantiateCategory(rs);
				Movie movie = instantiateMovie(rs, category);
				return movie;
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
	public List<Movie> selectAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT m.*, c.Name as CatName FROM movie m " 
					+ "INNER JOIN category c "
					+ "ON m.CategoryId = c.Id " 
					+ "ORDER BY Name"
					);
			
			rs = st.executeQuery();
			List<Movie>list = new ArrayList<>();
			Map<Integer, Category> map = new HashMap<>();
			
			while(rs.next()) {
				Category category = map.get(rs.getInt("CategoryId"));
				
				if(category == null) {
					category = instantiateCategory(rs);
					map.put(rs.getInt("CategoryId"), category);
				}
				Movie movie = instantiateMovie(rs, category);
				list.add(movie);
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
		category.setId(rs.getInt("CategoryId"));
		category.setName(rs.getString("CatName"));
		
		return category;
	}
	
	public Movie instantiateMovie(ResultSet rs, Category category) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getInt("Id"));
		movie.setName(rs.getString("Name"));
		movie.setDescription(rs.getString("Description"));
		movie.setDateOfRelease(rs.getDate("DateOfRelease"));
		movie.setCategory(category);
		
		return movie;
	}

}
