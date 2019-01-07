package dan.accounting8.richclient.dialogs;

import dan.accounting8.business.Facade;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.richclient.MainWindow;
import static dan.accounting8.richclient.dialogs.AbstractDialog.DEL;
import dan.accounting8.richclient.view.InitsPane;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class InitShowDialog extends AbstractDialog {

    private AccountCB accCB;

    public InitShowDialog() throws AccException {
        super(Messages.Zobraz_pocatecni_stavy.cm());
    }

    @Override
    protected Node createContent() {
        GridPane gp = genGP();
        int row = 0;
        gp.add(new Label(Messages.pro_ucet.cm() + DEL), 0, row);
        gp.add(accCB = new AccountCB(), 1, row);
        try {
            accCB.getItems().addAll(Facade.instance.getBalanceAccounts());
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }
        return gp;
    }

    @Override
    public void ok() throws AccException {        
        Tab t = new Tab(Messages.Pocatecni_stavy.cm(),
                new InitsPane(accCB.getOptAccount()));
        MainWindow.getInstance().addTab(t);
    }

    @Override
    public void validate() {
    }
}
