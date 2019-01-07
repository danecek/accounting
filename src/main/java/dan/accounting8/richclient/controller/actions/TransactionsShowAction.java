package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.TransactionsShowDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class TransactionsShowAction extends AbstrAction {

    public static final TransactionsShowAction instance = new TransactionsShowAction();

    private TransactionsShowAction() {
        super(Messages.Zobraz_transakce.cm());
    }

    @Override
    public void execute() {
        try {
            new TransactionsShowDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
