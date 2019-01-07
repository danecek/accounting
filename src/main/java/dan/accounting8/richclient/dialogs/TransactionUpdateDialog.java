package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.Document;
import dan.accounting8.model.Transaction;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionUpdateDialog extends TransactionAbstractDialog {

    private Transaction t;

    public TransactionUpdateDialog(Transaction t) throws AccException {
        super(Messages.Zmen_transakci.cm());
      this.t = t;
        init();
        set(t);
//        datePicker.setValue(t.getDate().get());
//        amountTF.setText(Long.toString(t.getAmount()));
//        ObservableList<AnalAcc> accounts = 
//                FXCollections.observableArrayList(Facade.instance.getAllAccounts());
//        madatiCB.getItems().addAll(accounts);
//        madatiCB.setValue(t.getMaDati());
//        dalCB.getItems().addAll(accounts);
//        dalCB.setValue(t.getDal());
//        documentCB.setValue(t.getDocument().get());
//        documentCB.setEditable(true);
//        bindingDocumentCB.setValue(t.getBindingDocument().get());
//        bindingDocumentCB.setEditable(true);
//        List<Document> documents = Facade.instance.getAllDocuments();
//        bindingDocumentCB.setItems(FXCollections.observableArrayList(documents));
    }

    @Override
    public void ok() throws AccException {
        try {
            Facade.instance.updateTransaction(t.getId(), datePicker.getValue(),
                    Long.parseLong(amountTF.getText()),
                    madatiCB.getValue(), dalCB.getValue(),
                    Optional.ofNullable(documentCB.getValue()),
                    Optional.ofNullable(bindingDocumentCB.getValue()));
            MainWindow.getInstance().refreshTransactionPanes();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
