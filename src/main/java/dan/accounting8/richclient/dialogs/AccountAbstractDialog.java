/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dan.accounting8.richclient.dialogs;

import dan.accounting8.model.AccGroup;
import dan.accounting8.util.AccException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public abstract class AccountAbstractDialog extends AbstractDialog {

    protected ComboBox<AccGroup> skupinaCB;
    protected TextField analTF;
    protected TextField nameTF;
    protected TextField agTF;

    public AccountAbstractDialog(String title) throws AccException {
        super(title);
    }

}
