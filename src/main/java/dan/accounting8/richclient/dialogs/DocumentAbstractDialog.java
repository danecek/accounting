package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.AccDatePicker;
import dan.accounting8.business.Facade;
import dan.accounting8.model.Document;
import dan.accounting8.model.DocumentId;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class DocumentAbstractDialog extends AbstractDialog {

    protected DocumentId id;
    protected DocTypeCB docTypeCB;
    protected TextField nameTF;
    protected CompanyCB companyCB;
    protected AccountCB accountCB;
    protected AccDatePicker dueDate;
    protected TextField dscTF;

    protected void registerFields() {
        nameTF.textProperty().addListener(this);
    }

    protected void setFields(Document d) {
        id = d.getId();
        docTypeCB.setValue(d.getType());
        nameTF.setText(d.getName());
        dscTF.setText(d.getDescription());
        d.getOptCompany().ifPresent(comp -> {
            companyCB.setValue(comp);
        });
        d.getOptAccount().ifPresent(acc -> accountCB.setValue(acc));
        d.getOptDate().ifPresent(date -> {
            dueDate.setValue(date);
        });
    }

    protected void init() {
        try {
            companyCB.getItems().addAll(Facade.instance.getAllCompanies());
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }
        try {
            accountCB.getItems().addAll(Facade.instance.getAllAccounts());
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }
    }

    public DocumentAbstractDialog(String title) {
        super(title);
    }

    @Override
    protected Node createContent() {
        GridPane gp = genGP();
        int row = 0;
        docTypeCB = new DocTypeCB();
        nameTF = new TextField();
        companyCB = new CompanyCB();
        accountCB = new AccountCB();
        dueDate = new AccDatePicker();
        dscTF = new TextField();
        gp.add(new Label(Messages.Typ_dokladu.cm() + DEL), 0, row);
        gp.add(docTypeCB, 1, row);
        row++;
        gp.add(new Label(Messages.Jmeno.cm() + DEL), 0, row);
        gp.add(nameTF, 1, row);
        row++;
        gp.add(new Label(Messages.Organizace.cm() + DEL), 0, row);
        gp.add(companyCB, 1, row);
        row++;
        gp.add(new Label(Messages.Ucet.cm() + DEL), 0, row);
        gp.add(accountCB, 1, row);
        row++;
        gp.add(new Label(Messages.Datum_splatnosti.cm() + DEL), 0, row);
        gp.add(dueDate, 1, row);
        row++;
        gp.add(new Label(Messages.Popis.cm() + DEL), 0, row);
        gp.add(dscTF, 1, row);
        return gp;
    }

    private Optional<String> errorMessage() {
        if (nameTF.getText().trim().isEmpty()) {
            return Optional.of(Messages.prazdne_jmeno.cm());
        }
        return Optional.empty();

    }

    @Override
    public void validate() {
        setError(errorMessage());
    }

}
