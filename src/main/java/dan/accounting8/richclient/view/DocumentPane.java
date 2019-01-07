package dan.accounting8.richclient.view;

import dan.accounting8.business.Facade;
import dan.accounting8.business.Global;
import dan.accounting8.model.Document;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.controller.actions.DocumentDeleteAction;
import dan.accounting8.richclient.controller.actions.DocumentUpdateAction;
import dan.accounting8.richclient.controller.actions.TransactionCreateByInvoiceAction;
import dan.accounting8.richclient.view.DocumentPane.DocumentP;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class DocumentPane extends AbstrPane<DocumentP> {

    public static class DocumentP extends Document {

        public DocumentP(Document d) {
            super(d.getId(), d.getType(), d.getName(), d.getOptCompany(), d.getOptAccount(),
                    d.getOptDate(), d.getDescription());
        }

        public String getCompanyName() {
            return getOptCompany().isPresent() ? getOptCompany().get().getName() : "";
        }

        public String getTypeText() {
            return getType().getText();
        }

        public String getAccountNumber() {
            return getOptAccount().isPresent() ? getOptAccount().get().getNumber() : "";
        }

        public String getDateText() {
            return getOptDate().isPresent() ? getOptDate().get().format(Global.instance.df()) : "";
        }
    }

    private final ObservableList<DocumentP> documents;
    private TableView<DocumentP> tw;

    public DocumentPane() {
        super(Messages.Doklady.cm());
        this.documents = FXCollections.observableArrayList();
        setContent(createContent());
        refresh();
    }

    @Override
    protected Node createContent() {
        tw = new TableView<>();
        PaneColumn idCol = propColumn(Messages.Cislo.cm(), "id");
        PaneColumn typeCol = propColumn(Messages.Typ_dokladu.cm(), "typeText");
        PaneColumn nameCol = propColumn(Messages.Jmeno.cm(), "name");
        PaneColumn companyNameCol = propColumn(Messages.Organizace.cm(), "companyName");
        PaneColumn accountNumberCol = propColumn(Messages.Ucet.cm(), "accountNumber");
        PaneColumn dateCol = propColumn(Messages.Datum.cm(), "dateText");
        PaneColumn descriptionCol = propColumn(Messages.Popis.cm(), "description");

        tw.getColumns().addAll(idCol, typeCol, nameCol,
                companyNameCol, accountNumberCol, dateCol, descriptionCol);
        tw.setItems(documents);
        tw.setRowFactory(gencb(
                DocumentUpdateAction.instance,
                DocumentDeleteAction.instance,
                TransactionCreateByInvoiceAction.instance));
        return tw;
    }

    @Override
    public void refresh() {
        try {
            documents.setAll(
                    Facade.instance.getAllDocuments().stream()
                            .map(d -> new DocumentP(d))
                            .collect(Collectors.toList()));
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }

    }

    @Override
    public Optional<DocumentP> getSelected() {
        return Optional.ofNullable(tw.getSelectionModel().getSelectedItem());
    }

}
