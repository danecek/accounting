package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Address;
import dan.accounting8.model.Company;
import dan.accounting8.model.CompanyId;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;

public class CompanyUpdateDialog extends CompanyAbstractDialog {

    private CompanyId id;

    public CompanyUpdateDialog(Company comp) {
        super(Messages.Zmen_organizaci.cm());
        comp.getName();
        nameTF.textProperty().addListener(this);
    }

    @Override
    public void ok() throws AccException {
        String a = addrTF.getText().trim();
        Facade.instance.updateCompany(id, nameTF.getText(), dscTF.getText(),
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
