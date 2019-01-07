package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.controller.actions.AbstrAction;
import dan.accounting8.richclient.dialogs.AccountCreateDialog;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class AcountCreateAction extends AbstrAction {

    public static final AcountCreateAction instance = new AcountCreateAction();

    private AcountCreateAction() {
        super(Messages.Vytvor_ucet.cm());
    }

    @Override
    public void execute() {
        try {
            new AccountCreateDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
