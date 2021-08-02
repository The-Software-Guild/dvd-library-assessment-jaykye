import controller.DvdLibController;
import dao.DvdLibDao;
import dao.DvdLibDaoFileImpl;
import ui.DvdLibView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        // Select implementation of io, dao.
        // Feed view and dao to controller. -- dependency injection
        UserIO io = new UserIOConsoleImpl();
        DvdLibView view = new DvdLibView(io);
        DvdLibDao dao = new DvdLibDaoFileImpl();
        DvdLibController controller = new DvdLibController(view, dao);
        controller.run();
    }
}
