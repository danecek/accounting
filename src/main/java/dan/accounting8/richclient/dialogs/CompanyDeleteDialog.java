/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Company;
import dan.accounting8.model.CompanyId;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class CompanyDeleteDialog extends CompanyAbstractDialog {

    private CompanyId id;

    public CompanyDeleteDialog(Company company) {
        super(Messages.Zrus_organizaci.cm());
        nameTF.setText(company.getName());
        nameTF.setDisable(true);
        dscTF.setText(company.getDescription());
        dscTF.setDisable(true);
        addrTF.setText(company.getAddressText());
        addrTF.setDisable(true);
        id = company.getId();
    }

    @Override
    public void ok() throws AccException {
        Facade.instance.deleteCompany(id);
        MainWindow.instance.refreshCompanyPane();
    }

    @Override
    public void validate() {
    }

}
