package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.CreateInitDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class InitCreateAction extends AbstrAction {

    public static final InitCreateAction instance = new InitCreateAction();

    private InitCreateAction() {
        super(Messages.Nastav_pocatecni_stav.cm());
    }

    @Override
    public void execute() {
        try {
            new CreateInitDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
