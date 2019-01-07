package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.InitShowDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class InitsShowAction extends AbstrAction {

    public static final InitsShowAction instance = new InitsShowAction();

    private InitsShowAction() {
        super(Messages.Zobraz_pocatecni_stavy.cm());
    }

    @Override
    public void execute() {
        try {
            new InitShowDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
