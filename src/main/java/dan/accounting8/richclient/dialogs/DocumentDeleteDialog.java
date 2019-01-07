package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Document;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class DocumentDeleteDialog extends DocumentAbstractDialog {

    private Document d;

    private void disableFields() {
        docTypeCB.setDisable(true);
        nameTF.setDisable(true);
        date.setDisable(true);
        dscTF.setDisable(true);
    }

    public DocumentDeleteDialog(Document d) {
        super(Messages.Zrus_doklad.cm());
        this.d = d;
        disableFields();
        setFields(d);

    }

    @Override
    public void ok() throws AccException {
        Facade.instance.deleteDocument(d.getId());
        MainWindow.instance.refreshDocumentPanes();
    }

    @Override
    public void validate() {

    }

}
