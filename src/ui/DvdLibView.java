package ui;

import dto.Dvd;

import java.util.List;

public class DvdLibView {
    private UserIO io;

    public DvdLibView(UserIO io){
        // I want chose what implementation later on.
        // We don't hard code which implementation to use unless it's the top layer of our program (App.java).
        this.io = io;
    }

    // Same as in Controller, all the functions "Hides" io functions.
    // Consequently, controller cannot "See" io.

    public int printMenuAndGetSelection(){
        int menuNumber = 0;
        io.print("Main Menu");
        io.print(++menuNumber + ". List All Dvds");
        io.print(++menuNumber + ". Add a New Dvd");
        io.print(++menuNumber + ". Search for a Dvd");
        io.print(++menuNumber + ". Edit a Dvd");
        io.print(++menuNumber + ". Remove a Dvd");
        io.print(++menuNumber + ". Exit");

        return io.readInt("Please select from the above choices.", 1, menuNumber);
    }

    public void displayExitBanner(){
        io.print("Thank you and good bye!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown command entered. Try again.");
    }

    public static void main(String[] args){
        // testing purpose.
        UserIO io = new UserIOConsoleImpl();
        DvdLibView view = new DvdLibView(io);
        view.printMenuAndGetSelection();
    }

    // Banners
    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayAddDvdBanner() {
        io.print("=== Add a Dvd ===");
    }

    public void displayAddSuccessBanner() {
        io.readString(
                "Dvd successfully added.  Please hit enter to continue");
    }

    public void displaySearchDvdBanner () {
        io.print("=== Search for a Dvd ===");
    }
    public void displayEditDvdBanner () {
        io.print("=== Edit a Dvd ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the Dvd Title.");
    }


    public void displayRemoveDvdBanner () {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(Dvd dvd) {
        if(dvd != null){
            io.print("Dvd successfully removed.");
        }else{
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditResult(Dvd dvd) {
        if(dvd != null){
            io.print("Dvd successfully edited.");
        }else{
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    // Other inputs

    public Dvd getNewDvdInfo() {
        String dvdTitle = io.readString("Please enter Dvd title");
        String releaseDate = io.readString("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio");
        String note = io.readString("Please enter Note");

        // New object
        Dvd currentDvd = new Dvd(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setNote(note);
        return currentDvd;
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format(
                    "Title: %s " +
                    "Release Date: %s " +
                    "MPAA rating: %s " +
                    "Director: %s " +
                    "Studio: %s " +
                    "Note: %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorName(),
                    currentDvd.getStudio(),
                    currentDvd.getNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            String dvdInfo = String.format(
                    "Title: %s \n" +
                    "Release Date: %s\n" +
                    "MPAA rating: %s\n" +
                    "Director: %s\n" +
                    "Studio: %s\n" +
                    "Note: %s",
                    dvd.getTitle(),
                    dvd.getReleaseDate(),
                    dvd.getMpaaRating(),
                    dvd.getDirectorName(),
                    dvd.getStudio(),
                    dvd.getNote());
            io.print(dvdInfo);
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public int printFieldsAndGetSelection(){
            int fieldNumber = 0;
            io.print("Please select the field you want to edit.");
            io.print("Fields");
            io.print(++fieldNumber + ". Release Date");
            io.print(++fieldNumber + ". MPAA rating");
            io.print(++fieldNumber + ". Director");
            io.print(++fieldNumber + ". Studio");
            io.print(++fieldNumber + ". Note");
            io.print("===========");
            io.print(++fieldNumber + ". Cancel and Exit");
            int selection = io.readInt("Please select from the above choices.", 1, fieldNumber);
            if (selection == fieldNumber){
                return -1;
            }
            return selection;
        }

    public String getNewFieldValue(){
        return io.readString("Please enter the new value.");
    }
}
