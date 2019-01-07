package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.AccDeleteDialog;
import dan.accounting8.richclient.view.AccountsPane;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class AcountDeleteAction extends AbstrAction {

    public static final AcountDeleteAction instance = new AcountDeleteAction();

    private AcountDeleteAction() {
        super(Messages.Zrus_ucet.cm());
    }

    @Override
    public void execute() {
        MainWindow.getInstance().getAccountPane()
                .ifPresent((AccountsPane ap) -> {
                    ap.selected()
                            .ifPresent((ap1) -> {
                                try {
                                    new AccDeleteDialog(ap1).execute();
                                } catch (AccException ex) {
                                    MainWindow.getInstance().showException(ex);
                                }

                            });
                });
    }

}
