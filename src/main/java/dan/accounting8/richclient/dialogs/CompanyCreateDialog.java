package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Address;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;

public class CompanyCreateDialog extends CompanyAbstractDialog {

    public CompanyCreateDialog() {
        super(Messages.Vytvor_organizaci.cm());
        nameTF.textProperty().addListener(this);
    }

    @Override
    public void ok() throws AccException {
        String a = addrTF.getText().trim();
        Facade.instance.createCompany(nameTF.getText(), dscTF.getText(),
                a.isEmpty() ? Optional.empty() : Optional.of(new Address(a)));
        MainWindow.instance.refreshCompanyPane();
    }

    @Override
    public void validate() {
        if (nameTF.getText().trim().isEmpty()) {
            errorPane.setError(Optional.of(Messages.prazdne_jmeno.cm()));
        } else {
            errorPane.setError(Optional.empty());
        }

    }

}
