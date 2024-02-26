import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movieList;

    public MovieCollection() {
        importData();
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    public void importData() {
        movieList = new ArrayList<>();
        try {
            File file = new File("src\\movies_data.csv");
            Scanner fileScan = new Scanner(file);
            fileScan.nextLine();
            while (fileScan.hasNext()) {
                String[] movieInfo = fileScan.nextLine().split(",");
                movieList.add(new Movie(movieInfo[0], movieInfo[1].split("\\|"),movieInfo[2],movieInfo[3],Integer.parseInt(movieInfo[4]),Double.parseDouble(movieInfo[5])));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void searchTitles() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a title to search for: ");
        String title = scan.nextLine();
        ArrayList<Movie> searched = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getMovieName().toLowerCase().contains(title.toLowerCase())) {
                searched.add(movie);
            }
        }
        if (searched.isEmpty()) {
            System.out.println("No movies matched your search");
        } else {
            System.out.println("The following movies match your search:");
            searched = sortAlphabetically(searched);
            for (int i = 0; i < searched.size(); i++) {
                System.out.println((i + 1) + " " + searched.get(i).getMovieName());
            }
            System.out.println("Enter the number corresponding to the movie you're looking for if it is on this list.");
            int number = scan.nextInt() - 1;
            scan.nextLine();
            Movie movie = searched.get(number);
            System.out.println("Title: " + movie.getMovieName());
            System.out.println("Cast: " + Arrays.toString(movie.getMovieCast()));
            System.out.println("Director: " + movie.getMovieDirector());
            System.out.println("Overview: " + movie.getMovieOverview());
            System.out.println("Runtime: " + movie.getRuntime());
            System.out.println("User Rating: " + movie.getUserRating());
        }
    }

    public void searchCast() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a cast member to search for: ");
        String name = scan.nextLine();
        ArrayList<String> searched = new ArrayList<>();
        for (Movie movie : movieList) {
            String[] cast = movie.getMovieCast();
            for (int i = 0; i < cast.length; i++) {
                if (cast[i].toLowerCase().contains(name.toLowerCase()) && !(searched.contains(cast[i]))) {
                    searched.add(cast[i]);
                }
            }
        }
        if (searched.isEmpty()) {
            System.out.println("No actors/actresses matched your search");
        } else {
            System.out.println("The following actors/actresses match your search:");
            searched = castAlphabetically(searched);
            for (int i = 0; i < searched.size(); i++) {
                System.out.println((i + 1) + " " + searched.get(i));
            }
            System.out.println("Enter a number corresponding to the actor/actress you were looking for");
            int num = scan.nextInt() - 1;
            scan.nextLine();
            String actor = searched.get(num);
            ArrayList<Movie> actorMovies = new ArrayList<>();
            for (Movie m : movieList) {
                for (String castMember : m.getMovieCast()) {
                    if (actor.equals(castMember)) {
                        actorMovies.add(m);
                    }
                }
            }
            actorMovies = sortAlphabetically(actorMovies);
            System.out.println("Your actor/actress played a role in the following movies: ");
            for (int i = 0; i < actorMovies.size(); i++) {
                System.out.println((i + 1) + " " + actorMovies.get(i).getMovieName());
            }
            System.out.println("Enter the number corresponding to the movie you're looking for if it is on this list.");
            int number = scan.nextInt() - 1;
            scan.nextLine();
            Movie movie = actorMovies.get(number);
            System.out.println("Title: " + movie.getMovieName());
            System.out.println("Cast: " + Arrays.toString(movie.getMovieCast()));
            System.out.println("Director: " + movie.getMovieDirector());
            System.out.println("Overview: " + movie.getMovieOverview());
            System.out.println("Runtime: " + movie.getRuntime());
            System.out.println("User Rating: " + movie.getUserRating());
        }
    }

    private ArrayList<Movie> sortAlphabetically(ArrayList<Movie> movies) {
        for (int i = 0; i < movies.size() - 1; i++) {
            Movie m = movies.get(i);
            for (int n = i + 1; n < movies.size(); n++) {
                if (m.getMovieName().compareTo(movies.get(n).getMovieName()) > 0) {
                    movies.set(i,movies.get(n));
                    movies.set(n,m);
                    m = movies.get(i);
                }
            }
        }
        return movies;
    }

    private ArrayList<String> castAlphabetically(ArrayList<String> cast) {
        for (int i = 0; i < cast.size() - 1; i++) {
            String name = cast.get(i).split(" ")[0];
            for (int n = i + 1; n < cast.size(); n++) {
                String name2 = cast.get(n).split(" ")[0];
                if (name.compareTo(name2) > 0) {
                    String temp = cast.get(i);
                    cast.set(i,cast.get(n));
                    cast.set(n,temp);
                    name = cast.get(i).split(" ")[0];
                }
            }
        }
        return cast;
    }
}
