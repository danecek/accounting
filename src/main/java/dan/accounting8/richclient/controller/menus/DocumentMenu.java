package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.DocumentCreateAction;
import dan.accounting8.richclient.controller.actions.DocumentDeleteAction;
import dan.accounting8.richclient.controller.actions.DocumentUpdateAction;
import dan.accounting8.richclient.controller.actions.DocumentsShowAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class DocumentMenu extends Menu {

    public DocumentMenu() {
        super(Messages.Doklady.cm(), null,
                DocumentsShowAction.instance.createMenuItem(),
                DocumentCreateAction.instance.createMenuItem(),
                DocumentUpdateAction.instance.createMenuItem(),
                DocumentDeleteAction.instance.createMenuItem()
        );
    }

}
