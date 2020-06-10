package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movie ob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
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
