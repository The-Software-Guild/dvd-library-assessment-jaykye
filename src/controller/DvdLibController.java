package controller;

import dao.DvdLibDao;
import dao.DvdLibDaoException;
import dao.DvdLibDaoFileImpl;
import dto.Dvd;
import ui.DvdLibView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.List;

public class DvdLibController {
    // Controller has access to view, but does not directly use view methods!!!
    // Make its own method using view!

    private DvdLibView view;
    private DvdLibDao dao;

    public DvdLibController(DvdLibView view, DvdLibDao dao){
        this.view = view;
        this.dao = dao;
    }

    public void run(){
        boolean imDone = false;
        int menuSelection;

        try {
            while (!imDone) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listAllDvd();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        searchDvd();
                        break;
                    case 4:
                        editDvd();
                        break;
                    case 5:
                        removeDvd();
                        break;
                    case 6:
                        imDone = true;  // Loop breaks
                        break;
                    default:
                        unknownCommand();
                }
            }
        }
        catch (DvdLibDaoException e){
            view.displayErrorMessage(e.getMessage());  // This can also be in a controller function.
        }
        exitMessage();
    }

    // Methods are not going to be used outside of controller. --> private
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void addDvd() throws DvdLibDaoException {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayAddSuccessBanner();
    }

    private void listAllDvd() throws DvdLibDaoException {
        view.displayDisplayAllBanner();
        List allDvdList = dao.getAllDvds();
        view.displayDvdList(allDvdList);
    }

    private void searchDvd() throws DvdLibDaoException {
        view.displaySearchDvdBanner();
        String dvdTitleInput = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitleInput);
        view.displayDvd(dvd);
    }

    private void editDvd() throws DvdLibDaoException {
        view.displayEditDvdBanner();
        String dvdTitleInput = view.getDvdTitleChoice();
        int dvdFieldInput = view.printFieldsAndGetSelection();
        if (dvdFieldInput>0) {
            String dvdFieldValueInput = view.getNewFieldValue();
            Dvd editedDvd = dao.editDvd(dvdTitleInput, dvdFieldInput,
                    dvdFieldValueInput);
            view.displayEditResult(editedDvd);
        }
    }

    private void removeDvd() throws DvdLibDaoException {
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd removedDvd = dao.removeDvd(dvdTitle);
        view.displayRemoveResult(removedDvd);
    }
}
