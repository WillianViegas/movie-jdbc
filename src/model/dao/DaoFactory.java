package model.dao;

import db.DB;
import model.dao.impl.CategoryDaoJDBC;
import model.dao.impl.MovieDaoJDBC;

public class DaoFactory {

	public static MovieDao createMovieDao() {
		return new MovieDaoJDBC(DB.getConnection());
	}
	
	public static CategoryDao createCategoryDao(){
		return new CategoryDaoJDBC(DB.getConnection());
	}
}
