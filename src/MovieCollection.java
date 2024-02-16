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
        System.out.println("The following movies match your search:");
        for (int i = 0; i < searched.size(); i++) {
            System.out.println((i+1) + " " + searched.get(i).getMovieName());
        }
        System.out.println("Enter the number corresponding to the movie you're looking for if it is on this list.");
        int number = scan.nextInt() - 1;
        scan.nextLine();
        Movie movie = searched.get(number);
        System.out.println("Title: " + movie.getMovieName());
        System.out.println("Cast: " + Arrays.toString(movie.getMovieCast()));
    }

    public void searchCast() {}
}
