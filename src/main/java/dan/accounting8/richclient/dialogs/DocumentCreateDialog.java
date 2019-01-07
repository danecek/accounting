package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class DocumentCreateDialog extends DocumentAbstractDialog {

    public DocumentCreateDialog() {
        super(Messages.Vytvor_doklad.cm());
        registerFields();
        init();
    }

    @Override
    public void ok() throws AccException {
        Facade.instance.createDocument(
                docTypeCB.getValue(),
                nameTF.getText(),
                companyCB.getOptCompany(),
                accountCB.getOptAccount(),
                dueDate.getOptDate(), 
                dscTF.getText());
        MainWindow.instance.refreshDocumentPanes();
    }

    @Override
    public void validate() {
       // setError(message);
    }

}
