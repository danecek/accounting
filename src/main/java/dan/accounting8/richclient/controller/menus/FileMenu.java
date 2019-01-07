package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.ExitAction;
import dan.accounting8.richclient.controller.actions.ExitAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class FileMenu extends Menu {

    public FileMenu() {
        super(Messages.File.cm(), null,
             ExitAction.instance.createMenuItem()
             );
    }

}
