package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.view.DocumentPane;
import dan.accounting8.util.Messages;

public class DocumentsShowAction extends AbstrAction {

    public static final DocumentsShowAction instance = new DocumentsShowAction();

    private DocumentsShowAction() {
        super(Messages.Zobraz_doklady.cm());
    }

    @Override
    public void execute() {
        new DocumentPane().addToMain();
    }

}
