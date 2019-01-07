package dan.accounting8.richclient.controller.actions;

import dan.accounting8.model.Document;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.DocumentUpdateDialog;
import dan.accounting8.richclient.view.AbstrPane;
import dan.accounting8.richclient.view.DocumentPane;
import dan.accounting8.richclient.view.DocumentPane.DocumentP;
import dan.accounting8.util.Messages;
import java.util.Optional;

public class DocumentUpdateAction extends AbstrAction {

    public static final DocumentUpdateAction instance = new DocumentUpdateAction();

    private DocumentUpdateAction() {
        super(Messages.Zmen_doklad.cm());
    }

    @Override
    public void execute() {
        Optional<AbstrPane> oap = MainWindow.instance.getSelectedTab(Messages.Doklady.cm());
        oap.ifPresent(ap -> {
            Optional<DocumentP> dp = ((DocumentPane) ap).getSelected();
            dp.ifPresent(d -> new DocumentUpdateDialog(d).execute());
        });
    }

}
