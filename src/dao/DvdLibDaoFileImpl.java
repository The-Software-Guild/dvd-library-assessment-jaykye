package dao;

import dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibDaoFileImpl implements DvdLibDao {
    public static final String LIBRARYFILE = "dvdlib.txt";
    public static final String DELIMITER = "::";
    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibDaoException {
        loadDvdLib();
        Dvd newDvd = dvds.put(dvdTitle, dvd);
        writeDvdLib();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibDaoException {
        loadDvdLib();
        return new ArrayList(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdTitle) throws DvdLibDaoException {
        loadDvdLib();
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd editDvd(String dvdTitle, int field, String newValue) throws DvdLibDaoException {
        // Load data
        loadDvdLib();
        Dvd dvd = dvds.get(dvdTitle);  // dvd object must be obtained AFTER loading data.

        if (dvd != null) {
            switch (field) {
                case 1:
                    dvd.setReleaseDate(newValue);
                    break;
                case 2:
                    dvd.setMpaaRating(newValue);
                    break;
                case 3:
                    dvd.setDirectorName(newValue);
                    break;
                case 4:
                    dvd.setStudio(newValue);
                    break;
                case 5:
                    dvd.setNote(newValue);
                    break;
            }
        }
        writeDvdLib();
        return dvd;
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibDaoException {
        loadDvdLib();
        Dvd removedDvd = dvds.remove(dvdTitle); // returns null if does not exist
        writeDvdLib();
        return removedDvd;
    }


    // Marshalling and Unmarshalling

    private Dvd unmarshallDvd(String dvdAsText){
        // dvdAsText is expecting a line read in from our file.

        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(dvdTitle);

        // Index 1 - Release date
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        // Index 2 - MPAA rating
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        // Index 3 - Director name
        dvdFromFile.setDirectorName(dvdTokens[3]);
        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);
        // Index 5 - Note
        dvdFromFile.setNote(dvdTokens[5]);

        return dvdFromFile;
    }

    private void loadDvdLib() throws DvdLibDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARYFILE)));
        }
        catch (FileNotFoundException e) {
            // throw will return the exception to caller.
            throw new DvdLibDaoException(
                    "-_- Could not load dvd library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled.
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd){
        // Grabs a dvd obj and convert it into a line of string.
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getNote();
        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all dvds in the dvdlibrary out to a LIBRARYFILE.  See loadDvdLib
     * for file format.
     *
     * @throws DvdLibDaoException if an error occurs writing to the file
     */
    private void writeDvdLib() throws DvdLibDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARYFILE));
        } catch (IOException e) {  // RMB this is not userIO it's Java's io.
            throw new DvdLibDaoException(
                    "Could not save dvd data.", e);
        }

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
