package dan.accounting8.richclient.controller.actions;

import dan.accounting8.model.Document;
import dan.accounting8.richclient.dialogs.TransactionCreateDialog;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.Messages;

public class TransactionCreateByInvoiceAction extends AbstrAction {

    public static final TransactionCreateByInvoiceAction instance = new TransactionCreateByInvoiceAction();

    private TransactionCreateByInvoiceAction() {
        super(Messages.Vytvor_transakci.cm());
    }

    @Override
    public void execute() {
        MainWindow.instance.getSelectedTab(Messages.Doklady.cm())
                .ifPresent(ap -> {
                    ap.getSelected()
                            .ifPresent(d -> new TransactionCreateDialog((Document)d).execute());
                });
    }

}
