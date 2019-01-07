package dan.accounting8.richclient.controller;

import dan.accounting8.richclient.controller.menus.VystupMenu;
import dan.accounting8.richclient.controller.menus.TransactionMenu;
import dan.accounting8.richclient.controller.menus.PrintMenu;
import dan.accounting8.richclient.controller.menus.FileMenu;
import dan.accounting8.richclient.controller.menus.AccountMenu;
import dan.accounting8.richclient.controller.menus.DocumentMenu;
import javafx.scene.control.MenuBar;

public class AccMenuBar extends MenuBar {

    public AccMenuBar() {
        super(new FileMenu(),
                new AccountMenu(),
                new DocumentMenu(),
                new TransactionMenu(),
                new PrintMenu(),
                new VystupMenu());
    }

}
