public class Movie {
    private String movieName;
    private String[] movieCast;
    private String movieDirector;
    private String movieOverview;
    private int runtime;
    private double userRating;

    public Movie(String name, String[] cast, String director, String overview, int runtime, double rating) {
        movieName = name;
        movieCast = cast;
        movieDirector = director;
        movieOverview = overview;
        this.runtime = runtime;
        this.userRating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public String[] getMovieCast() {
        return movieCast;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public int getRuntime() {
        return runtime;
    }

    public double getUserRating() {
        return userRating;
    }

    public String toString() {
        return "";
    }
}
