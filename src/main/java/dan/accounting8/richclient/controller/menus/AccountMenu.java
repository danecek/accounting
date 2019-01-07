package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.AcountDeleteAction;
import dan.accounting8.richclient.controller.actions.AcountCreateAction;
import dan.accounting8.richclient.controller.actions.AccountsShowAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class AccountMenu extends Menu {

    public AccountMenu() {
        super(Messages.Ucty.cm(), null,
                AccountsShowAction.instance.createMenuItem(),
                AcountCreateAction.instance.createMenuItem(),
                AcountDeleteAction.instance.createMenuItem()
        );
    }

}
