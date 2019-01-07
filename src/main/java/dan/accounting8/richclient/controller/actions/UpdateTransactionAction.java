package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.TransactionUpdateDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class UpdateTransactionAction extends AbstrAction {

    public static final UpdateTransactionAction instance = new UpdateTransactionAction();

    private UpdateTransactionAction() {
        super(Messages.Zmen_transakci.cm());
    }

    @Override
    public void execute() {
        MainWindow.getInstance().getSelectedTransactionPane()
                .ifPresent((tp) -> {
                    tp.getSelected()
                            .ifPresent((t) -> {
                                try {
                                    new TransactionUpdateDialog(t).execute();
                                } catch (AccException ex) {
                                    MainWindow.getInstance().showException(ex);
                                }
                            });
                });
    }

}
