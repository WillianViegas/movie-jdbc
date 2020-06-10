package application;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Movie;

public class Program {

	public static void main(String[] args) {
		MovieDao movieDao = DaoFactory.createMovieDao();		
		
		System.out.println("Movie test 1#: selectById");
		Movie movie = movieDao.selectById(2);
		System.out.println(movie);

	}
}
