package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Document;
import dan.accounting8.model.DocumentId;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class DocumentUpdateDialog extends DocumentAbstractDialog {

    Document d;

    public DocumentUpdateDialog(Document d) {
        super(Messages.Zmen_doklad.cm());
        this.d = d;
        registerFields();
        setFields(d);
    }

    @Override
    public void ok() throws AccException {
        Facade.instance.updateDocument(
                d.getId(),
                docTypeCB.getValue(),
                nameTF.getText(),
                date.getValue(),
                dscTF.getText());
        MainWindow.instance.refreshDocumentPanes();
    }

    @Override
    public void validate() {
    }

}
