package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.controller.actions.AbstrAction;
import dan.accounting8.richclient.dialogs.TransactionDeleteDialog;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.TransactionUpdateDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class TransactionDeleteAction extends AbstrAction {

    public static final TransactionDeleteAction instance = new TransactionDeleteAction();

    private TransactionDeleteAction() {
        super(Messages.Zrus_transakci.cm());
    }

    @Override
    public void execute() {
        MainWindow.getInstance().getSelectedTransactionPane()
                .ifPresent((tp) -> {
                    tp.getSelected()
                            .ifPresent((t) -> {
                                try {
                                    new TransactionDeleteDialog(t).execute();
                                } catch (AccException ex) {
                                    MainWindow.getInstance().showException(ex);
                                }
                            });
                });
    }

}
