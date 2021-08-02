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
    // 여기서도 view의 method를 바로 사용하지 않는다.
    // 자신의 method로 감싸서 만든다.

    UserIO io = new UserIOConsoleImpl();  // ********* Hard Coded. **************
    DvdLibView view = new DvdLibView(io); // ********* Hard Coded. **************
    DvdLibDao dao = new DvdLibDaoFileImpl();

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
                        io.print("Exiting");
                        imDone = true;
                        break;
                    default:
                        unknownCommand();
                }
            }
        }
        catch (DvdLibDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    // methods are not gonna be used outside of controller. --> private
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
