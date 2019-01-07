/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dan.accounting8.richclient.dialogs;

import dan.accounting8.model.Company;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class CompanyCB extends ComboBox<Company> {

    public CompanyCB() {
        setConverter(new StringConverter<Company>() {
            @Override
            public String toString(Company company) {
                return company.getName();
            }

            @Override
            public Company fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public Optional<Company> getOptCompany() {
        return Optional.ofNullable(getValue());
    }

}
