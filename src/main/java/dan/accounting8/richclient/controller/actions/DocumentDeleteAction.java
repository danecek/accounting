package dan.accounting8.richclient.controller.actions;

import dan.accounting8.model.Document;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.DocumentDeleteDialog;
import dan.accounting8.richclient.view.AbstrPane;
import dan.accounting8.richclient.view.DocumentPane;
import dan.accounting8.richclient.view.DocumentPane.DocumentP;
import dan.accounting8.util.Messages;
import java.util.Optional;

public class DocumentDeleteAction extends AbstrAction {

    public static final DocumentDeleteAction instance = new DocumentDeleteAction();

    private DocumentDeleteAction() {
        super(Messages.Zrus_doklad.cm());
    }

    @Override
    public void execute() {
        MainWindow.instance.getSelectedTab(Messages.Doklady.cm())
                .ifPresent(ap -> {
                    ((DocumentPane) ap).getSelected()
                            .ifPresent(d -> new DocumentDeleteDialog(d).execute());
                });

    }

}
