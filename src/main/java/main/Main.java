package main;
import java.util.Scanner;
import dao.MovieDAO;
import dao.SeriesDAO;
import dao.WatchlistDAO;
import model.Movie;
import model.Series;
import service.WatchlistService;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        WatchlistService service = new WatchlistService();
        MovieDAO movieDAO = new MovieDAO();
        SeriesDAO seriesDAO = new SeriesDAO();
        WatchlistDAO watchlistDAO = new WatchlistDAO();

        int choice;

        do {
            System.out.println("\n===== THE LONG FORGOTTEN WATCHLIST =====");
            System.out.println("1. Add Movie");
            System.out.println("2. Add Series");
            System.out.println("3. View All");
            System.out.println("4. Search");
      
            System.out.println("5. add to watchlist");
            
            System.out.println("6. Mark as watched");
            System.out.println("7. view watchlist");
            System.out.println("8. update movie");
            System.out.println("9. update series");
            System.out.println("10 .delete movie");
            System.out.println("11 .delete series");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter movie ID: ");
                    int movieId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter title: ");
                    String movieTitle = scanner.nextLine();

                    System.out.print("Enter genre: ");
                    String movieGenre = scanner.nextLine();

                    System.out.print("Enter rating: ");
                    double movieRating = scanner.nextDouble();

                    System.out.print("Enter release year: ");
                    int movieYear = scanner.nextInt();

                    System.out.print("Enter duration in minutes: ");
                    int duration = scanner.nextInt();

                    Movie movie = new Movie(
                            movieId,
                            movieTitle,
                            movieGenre,
                            movieRating,
                            movieYear,
                            duration
                    );

                    service.addMedia(movie);
                    movieDAO.addMovie(movie);
                    System.out.println("Movie added successfully.");
                    break;

                case 2:
                    System.out.print("Enter series ID: ");
                    int seriesId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter title: ");
                    String seriesTitle = scanner.nextLine();

                    System.out.print("Enter genre: ");
                    String seriesGenre = scanner.nextLine();

                    System.out.print("Enter rating: ");
                    double seriesRating = scanner.nextDouble();

                    System.out.print("Enter release year: ");
                    int seriesYear = scanner.nextInt();

                    System.out.print("Enter number of seasons: ");
                    int seasons = scanner.nextInt();

                    Series series = new Series(
                            seriesId,
                            seriesTitle,
                            seriesGenre,
                            seriesRating,
                            seriesYear,
                            seasons
                    );

                    service.addMedia(series);
                    seriesDAO.addSeries(series);
                    System.out.println("Series added successfully.");
                    break;

               
                case 3:
                    ArrayList<Movie> movies = movieDAO.getAllMovies();

                    if (movies.isEmpty()) {
                        System.out.println("No movies found in database.");
                    } else {
                        for (Movie m : movies) {
                            m.displayDetails();
                            System.out.println("--------------------");
                        }
                    }
                    
                    ArrayList<Series> seriesList = seriesDAO.getAllSeries();

                    if (seriesList.isEmpty()) {
                        System.out.println("No series found in database.");
                    } else {
                        for (Series s : seriesList) {
                            s.displayDetails();
                            System.out.println("--------------------");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();

                    Movie foundMovie = movieDAO.searchMovie(searchTitle);

                    if (foundMovie != null) {
                        foundMovie.displayDetails();
                    } else {
                        Series foundSeries = seriesDAO.searchSeries(searchTitle);

                        if (foundSeries != null) {
                            foundSeries.displayDetails();
                        } else {
                            System.out.println("Media not found.");
                        }
                    }
                    break;

                
                    
                case 5:
                    System.out.print("Enter watchlist ID: ");
                    int watchlistId = scanner.nextInt();

                    System.out.print("Enter movie ID (0 if series): ");
                    int watchMovieId = scanner.nextInt();

                    System.out.print("Enter series ID (0 if movie): ");
                    int watchSeriesId = scanner.nextInt();

                    watchlistDAO.addToWatchlist(watchlistId, watchMovieId, watchSeriesId);
                    break;

                case 6:
                    System.out.print("Enter watchlist ID: ");
                    int watchedId = scanner.nextInt();

                    watchlistDAO.markAsWatched(watchedId);
                    break;
                    
                case 7:
                    watchlistDAO.displayWatchlist();
                    break; 
                    
                case 8:
                    System.out.print("Enter movie ID to update: ");
                    int updateMovieId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new title: ");
                    String updateTitle = scanner.nextLine();

                    System.out.print("Enter new genre: ");
                    String updateGenre = scanner.nextLine();

                    System.out.print("Enter new rating: ");
                    double updateRating = scanner.nextDouble();

                    System.out.print("Enter new release year: ");
                    int updateYear = scanner.nextInt();

                    System.out.print("Enter new duration: ");
                    int updateDuration = scanner.nextInt();

                    Movie updatedMovie = new Movie(
                        updateMovieId,
                        updateTitle,
                        updateGenre,
                        updateRating,
                        updateYear,
                        updateDuration
                    );

                    movieDAO.updateMovie(updatedMovie);
                    break;
                case 9:
                    System.out.print("Enter series ID to update: ");
                    int updateSeriesId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new title: ");
                    String updateSeriesTitle = scanner.nextLine();

                    System.out.print("Enter new genre: ");
                    String updateSeriesGenre = scanner.nextLine();

                    System.out.print("Enter new rating: ");
                    double updateSeriesRating = scanner.nextDouble();

                    System.out.print("Enter new release year: ");
                    int updateSeriesYear = scanner.nextInt();

                    System.out.print("Enter new number of seasons: ");
                    int updateSeasons = scanner.nextInt();

                    Series updatedSeries = new Series(
                        updateSeriesId,
                        updateSeriesTitle,
                        updateSeriesGenre,
                        updateSeriesRating,
                        updateSeriesYear,
                        updateSeasons
                    );

                    seriesDAO.updateSeries(updatedSeries);
                    break;
                    
                case 10:
                    System.out.print("Enter movie ID to delete: ");
                    int deleteMovieId = scanner.nextInt();

                    movieDAO.deleteMovie(deleteMovieId);
                    break;
                    
                case 11:
                    System.out.print("Enter series ID to delete: ");
                    int deleteSeriesId = scanner.nextInt();

                    seriesDAO.deleteSeries(deleteSeriesId);
                    break;
                    
                case 12:
                    System.out.println("Thank you for using The Long Forgotten Watchlist.");
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
                
                 
            }

        } while (choice != 12);

        scanner.close();
    }
}