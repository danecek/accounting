package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.AccGroup;
import dan.accounting8.model.Osnova;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AccDeleteDialog extends AccountAbstractDialog {

    private AnalAcc acc;

    public AccDeleteDialog(AnalAcc acc) throws AccException {
        super(Messages.Zrus_ucet.cm());
        this.acc = acc;
        agTF.setText(acc.getNumber());
        analTF.setText(acc.getAnal());
        nameTF.setText(acc.getName());
        validate();
    }

    @Override
    protected Node createContent() {
        GridPane gp = genGP();
        int row = 0;
        gp.add(new Label(Messages.Cislo.cm() + DEL), 0, row);
        gp.add(agTF = new TextField(), 1, row);
        row++;
        gp.add(new Label(Messages.Analytika.cm() + DEL), 0, row);
        gp.add(analTF = new TextField(), 1, row);
        row++;
        gp.add(new Label(Messages.Nazev.cm() + DEL), 0, row);
        gp.add(nameTF = new TextField(), 1, row);
        return gp;
    }

    @Override
    public void ok() throws AccException {
        Optional<AccGroup> ag = Osnova.instance.getGroup(agTF.getText());
        Facade.instance.deleteAccount(acc.getId());
        MainWindow.getInstance().getAccountPane().ifPresent((ap) -> ap.refresh());
    }

    @Override
    public void validate() {
        try {
            boolean accUsed = Facade.instance.getAllTransactions().stream()
                    .anyMatch((t) -> t.getMaDati().equals(acc) || t.getDal().equals(acc));
            errorPane.setError(Optional.ofNullable(accUsed ? Messages.Ucet_je_pouzit.cm() : null));
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }

    }

}
