package movieticketbooking;
import java.util.ArrayList;
public class Movie {
    private String movieName, director;
    private ArrayList<Theatre> theatreList = new ArrayList<Theatre>();

    public Movie(String movieName, String director) {
        this.movieName = movieName;
        this.director = director;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return String.format("Movie Name : %s\tDirector : %s",this.movieName,this.director);
    }
    
    
}
