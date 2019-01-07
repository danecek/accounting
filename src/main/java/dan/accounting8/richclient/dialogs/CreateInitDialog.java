package dan.accounting8.richclient.dialogs;

import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;

public class CreateInitDialog extends InitAbstrDialog {

    public CreateInitDialog() throws AccException {
        super(Messages.Nastav_pocatecni_stav.cm());
    }

}
