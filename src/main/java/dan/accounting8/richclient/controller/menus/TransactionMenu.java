package dan.accounting8.richclient.controller.menus;

import dan.accounting8.richclient.controller.actions.InitCreateAction;
import dan.accounting8.richclient.controller.actions.TransactionCreateAction;
import dan.accounting8.richclient.controller.actions.TransactionDeleteAction;
import dan.accounting8.richclient.controller.actions.InitsShowAction;
import dan.accounting8.richclient.controller.actions.TransactionsShowAction;
import dan.accounting8.richclient.controller.actions.UpdateTransactionAction;
import dan.accounting8.util.Messages;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

public class TransactionMenu extends Menu {

    public TransactionMenu() {
        super(Messages.Transakce.cm(), null,
                TransactionsShowAction.instance.createMenuItem(),
                TransactionCreateAction.instance.createMenuItem(),
                UpdateTransactionAction.instance.createMenuItem(),
                TransactionDeleteAction.instance.createMenuItem(),
                new SeparatorMenuItem(),
                InitsShowAction.instance.createMenuItem(),
                InitCreateAction.instance.createMenuItem()
        );
    }

}
