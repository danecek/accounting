package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.controller.actions.AbstrAction;
import dan.accounting8.richclient.dialogs.BalanceCreateDialog;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class BalanceCreateAction extends AbstrAction {

    public static final BalanceCreateAction instance = new BalanceCreateAction();

    private BalanceCreateAction() {
        super(Messages.Vytvor_rozvahu.cm());
    }

    @Override
    public void execute() {
        try {
            new BalanceCreateDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
