package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.dialogs.TransactionCreateDialog;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class TransactionCreateAction extends AbstrAction {

    public static final TransactionCreateAction instance = new TransactionCreateAction();

    private TransactionCreateAction() {
        super(Messages.Vytvor_transakci.cm());
    }

    @Override
    public void execute() {
        new TransactionCreateDialog().execute();
    }

}
