package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Category;
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
		
		System.out.println("\n Movie test 3#: insert");
		Category cat = new Category(1, null);
		Movie movie2 = new Movie(null, "Rambo: Last Blood", 
		"Rambo must confront his past and unearth his ruthless combat skills to exact revenge in a final mission.",
		new Date(), cat);
		movieDao.insert(movie2);
		
	}
}
