package model.dao;

import db.DB;
import model.dao.impl.MovieDaoJDBC;

public class DaoFactory {

	public static MovieDao createMovieDao() {
		return new MovieDaoJDBC(DB.getConnection());
	}
}
