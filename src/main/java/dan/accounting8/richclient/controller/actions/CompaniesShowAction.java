package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.view.CompanyPane;
import dan.accounting8.util.Messages;

public class CompaniesShowAction extends AbstrAction {

    public static final CompaniesShowAction instance = new CompaniesShowAction();

    private CompaniesShowAction() {
        super(Messages.Zobraz_organizace.cm());
    }

    @Override
    public void execute() {
        new CompanyPane().addToMain();
    }

}
