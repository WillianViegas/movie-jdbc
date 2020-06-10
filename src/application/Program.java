package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Movie;

public class Program {

	public static void main(String[] args) {
		MovieDao movieDao = DaoFactory.createMovieDao();		
		
		System.out.println("Movie test 1#: selectById");
		Movie movie = movieDao.selectById(2);
		System.out.println(movie);

		System.out.println("\n Movie test 2#: selectAll");
		List<Movie> list = movieDao.selectAll();
		list.forEach(System.out::println);
	}
}
