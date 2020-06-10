package application;

import java.util.Date;

import model.entities.Category;
import model.entities.Movie;

public class Program {

	public static void main(String[] args) {
		Movie movie = new Movie(1, "Batman", "A batman's movie", new Date(), new Category(1, "action"));
		System.out.println(movie);

	}

}
