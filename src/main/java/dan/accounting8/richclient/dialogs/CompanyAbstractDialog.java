package dan.accounting8.richclient.dialogs;

import dan.accounting8.util.Messages;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class CompanyAbstractDialog extends AbstractDialog {

    protected TextField nameTF;
    protected TextField dscTF;
    protected TextField addrTF;

    public CompanyAbstractDialog(String title) {
        super(title);
    }

    @Override
    protected Node createContent() {
        nameTF = new TextField();
        dscTF = new TextField();
        addrTF = new TextField();
        GridPane gp = genGP();
        int row = 0;
        gp.add(new Label(Messages.Jmeno.cm()), 0, row);
        gp.add(nameTF, 1, row);
        row++;
        gp.add(new Label(Messages.Popis.cm()), 0, row);
        gp.add(dscTF, 1, row);
        row++;
        gp.add(new Label(Messages.Adresa.cm()), 0, row);
        gp.add(addrTF, 1, row);
        return gp;
    }

}
