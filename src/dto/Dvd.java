package dto;

public class Dvd {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;

    // Constructor
    public Dvd(String title){
        this.title = title;
    }

    // Getter and setter methods.
    public String getTitle(){
        return title;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public String getMpaaRating(){
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating){
        this.mpaaRating = mpaaRating;
    }
    public String getDirectorName(){
        return directorName;
    }

    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }
    public String getStudio(){
        return studio;
    }

    public void setStudio(String studio){
        this.studio = studio;
    }
    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

}
