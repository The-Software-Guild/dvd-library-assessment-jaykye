package dao;

import dto.Dvd;

import java.util.List;

public interface DvdLibDao {
    /**
     * Adds the given Dvd to the Dvd library and associates it with the given
     * dvd title. If there is already a dvd associated with the given
     * dvd title it will return that student object, otherwise it will
     * return null.
     *
     * @param dvdTitle id with which student is to be associated
     * @param dvd student to be added to the roster
     * @return the Dvd object previously associated with the given
     * dvd title if it exists, null otherwise
     */
    Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibDaoException;

    /**
     * Returns a List of all dvds in the dvd library.
     *
     * @return List containing all dvds in the dvd library.
     */
    List<Dvd> getAllDvds() throws DvdLibDaoException;

    /**
     * Returns the dvd object associated with the given dvd title.
     * Returns null if no such dvd exists
     *
     * @param dvdTitle Title of the dvd to retrieve
     * @return the Dvd object associated with the given dvd title,
     * null if no such dvd exists
     */
    Dvd getDvd(String dvdTitle) throws DvdLibDaoException;


    /**
     * Returns the dvd object after user edit.
     * Returns null if no such dvd exists
     *
     * @param dvdTitle title of the dvd to retrieve
     * @return the Dvd object associated after user edit,
     * null if no such dvd exists
     */
    Dvd editDvd(String dvdTitle, int field, String newValue) throws DvdLibDaoException;

    /**
     * Removes from the dvd library the dvd associated with the given title.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given title
     *
     * @param dvdTitle tile of the dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the give dvd title
     */
    Dvd removeDvd(String dvdTitle) throws DvdLibDaoException;
}
