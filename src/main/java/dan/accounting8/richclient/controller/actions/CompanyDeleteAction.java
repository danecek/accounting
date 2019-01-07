package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.CompanyDeleteDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class CompanyDeleteAction extends AbstrAction {

    public static final CompanyDeleteAction instance = new CompanyDeleteAction();

    private CompanyDeleteAction() {
        super(Messages.Zrus_organizaci.cm());
    }

    @Override
    public void execute() {
        MainWindow.getInstance().getCompanyPane()
                .ifPresent((cp) -> {                    
                    cp.getSelected().ifPresent((c) -> {
                        new CompanyDeleteDialog(c).execute();

                    });
                });
    }

}
