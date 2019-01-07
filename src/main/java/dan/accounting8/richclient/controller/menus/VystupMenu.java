package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.BalanceCreateAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class VystupMenu extends Menu {

    public VystupMenu() {
        super(Messages.Vystupy.cm(), null,
                BalanceCreateAction.instance.createMenuItem()
        );
    }

}
