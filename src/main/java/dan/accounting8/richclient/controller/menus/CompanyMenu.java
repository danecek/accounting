package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.CompaniesShowAction;
import dan.accounting8.richclient.controller.actions.CompanyCreateAction;
import dan.accounting8.richclient.controller.actions.CompanyDeleteAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;

public class CompanyMenu extends Menu {

    public CompanyMenu() {
        super(Messages.Doklady.cm(), null,
                CompaniesShowAction.instance.createMenuItem(),
                CompanyCreateAction.instance.createMenuItem(),
                CompanyDeleteAction.instance.createMenuItem()
        );
    }

}
