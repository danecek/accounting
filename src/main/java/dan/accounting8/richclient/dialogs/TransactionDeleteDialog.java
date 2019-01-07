package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Transaction;
import dan.accounting8.model.TransactionId;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;

public class TransactionDeleteDialog extends TransactionAbstractDialog {

    TransactionId id;

    public TransactionDeleteDialog(Transaction t) throws AccException {
        super(Messages.Zrus_transakci.cm());
        id = t.getId();
        set(t);
 //       datePicker.setDisable(true);
        amountTF.setDisable(true);
        madatiCB.setDisable(true);
        dalCB.setDisable(true);
        documentCB.setDisable(true);
        bindingDocumentCB.setDisable(true);
    }

    @Override
    protected Optional<String> err() {
        return Optional.empty();
    }

    @Override
    public void ok() throws AccException {
        try {
            Facade.instance.deleteTransaction(id);
            MainWindow.getInstance().refreshTransactionPanes();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
