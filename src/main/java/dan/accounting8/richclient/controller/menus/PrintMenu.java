package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.PrintBalanceAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class PrintMenu extends Menu {

    public PrintMenu() {
        super(Messages.Tisk.cm(), null,
                PrintBalanceAction.instance.createMenuItem()
        );
    }

}
