package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.dialogs.CompanyCreateDialog;
import dan.accounting8.util.Messages;

public class CompanyCreateAction extends AbstrAction {

    public static final CompanyCreateAction instance = new CompanyCreateAction();

    private CompanyCreateAction() {
        super(Messages.Vytvor_organizaci.cm());
    }

    @Override
    public void execute() {
        new CompanyCreateDialog().execute();
    }

}
