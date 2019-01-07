package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.view.AccountsPane;
import dan.accounting8.util.Messages;
import javafx.scene.control.Tab;

public class AccountsShowAction extends AbstrAction {

    public static final AccountsShowAction instance = new AccountsShowAction();

    private AccountsShowAction() {
        super(Messages.Zobraz_ucty.cm());
    }

    @Override
    public void execute() {
        Tab t = new Tab(Messages.Ucty.cm(),
                new AccountsPane());
        MainWindow.getInstance().addTab(t);
    }

}
