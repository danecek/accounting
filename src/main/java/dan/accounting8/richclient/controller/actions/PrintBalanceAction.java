package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.controller.actions.AbstrAction;
import dan.accounting8.richclient.view.BalancePane;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.controller.Printing;
import dan.accounting8.richclient.dialogs.PrinterDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrinterJob;

public class PrintBalanceAction extends AbstrAction {
    
    public static final PrintBalanceAction instance = new PrintBalanceAction();
    
    private PrintBalanceAction() {
        super(Messages.Tisk_rozvahy.cm());
    }
    
    @Override
    public void execute() {
        try {
            new PrinterDialog().execute();
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }
    
}
