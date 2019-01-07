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
    protected AccDatePicker date;
    protected TextField dscTF;

    protected void registerFields() {
        nameTF.textProperty().addListener(this);
    }

    protected void setFields(Document d) {
        id = d.getId();
        docTypeCB.setValue(d.getType());
        nameTF.setText(d.getName());
        dscTF.setText(d.getDescription());
        date.setValue(d.getDate());
    }

    public DocumentAbstractDialog(String title) {
        super(title);
    }

    @Override
    protected Node createContent() {
        GridPane gp = genGP();
        docTypeCB = new DocTypeCB();
        nameTF = new TextField();
        date = new AccDatePicker();
        dscTF = new TextField();
        int row = 0;
        gp.add(new Label(Messages.Datum.cm() + DEL), 0, row);
        gp.add(date, 1, row);
        row++;
        gp.add(new Label(Messages.Typ_dokladu.cm() + DEL), 0, row);
        gp.add(docTypeCB, 1, row);
        row++;
        gp.add(new Label(Messages.Jmeno.cm() + DEL), 0, row);
        gp.add(nameTF, 1, row);
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
