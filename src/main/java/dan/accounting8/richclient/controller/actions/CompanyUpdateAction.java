package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.CompanyUpdateDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class CompanyUpdateAction extends AbstrAction {

    public static final CompanyUpdateAction instance = new CompanyUpdateAction();

    private CompanyUpdateAction() {
        super(Messages.Zrus_organizaci.cm());
    }

    @Override
    public void execute() {
        MainWindow.getInstance().getCompanyPane()
                .ifPresent((cp) -> {
                    cp.getSelected().ifPresent((c) -> {
                        new CompanyUpdateDialog(c).execute();

                    });
                });
    }

}
