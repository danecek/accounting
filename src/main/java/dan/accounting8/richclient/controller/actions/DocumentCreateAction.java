package dan.accounting8.richclient.controller.actions;

import dan.accounting8.richclient.dialogs.DocumentCreateDialog;
import dan.accounting8.util.Messages;

public class DocumentCreateAction extends AbstrAction {

    public static final DocumentCreateAction instance = new DocumentCreateAction();

    private DocumentCreateAction() {
        super(Messages.Vytvor_doklad.cm());
    }

    @Override
    public void execute() {
        new DocumentCreateDialog().execute();
    }

}
