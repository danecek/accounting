package dan.accounting8.richclient.view;

import dan.accounting8.business.Facade;
import dan.accounting8.business.Global;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.Document;
import dan.accounting8.model.Transaction;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.controller.actions.TransactionDeleteAction;
import dan.accounting8.richclient.controller.actions.UpdateTransactionAction;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public final class TransactionsPane extends TransactionsAbstractPane {

    public TransactionsPane(Optional<LocalDate> optOd, Optional<LocalDate> optDo,
            Optional<Document> optDocument, Optional<Document> optRelatedDocument) throws AccException {
        setContent((tableView = createContent()));
        this.optOd = optOd;
        this.optDo = optDo;
        this.optDocument = optDocument;
        this.optRelatedDocument = optRelatedDocument;
        setText(title(optOd, optDo, optDocument, optRelatedDocument));
        refresh();
    }

    @Override
    protected TableView<Transaction> createContent() {
        TableView<Transaction> tw = new TableView<>();
        tw.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        TransactionColumn idCol = new TransactionColumn(Messages.Id.cm(), "id");
        TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>(Messages.Datum.cm());
        TransactionColumn amountCol = new TransactionColumn(Messages.Castka.cm(), "amount");
        TransactionColumn madatiNumberCol = new TransactionColumn(Messages.Cislo.cm(), "maDatiNumber");
        TransactionColumn madatiNameCol = new TransactionColumn(Messages.Jmeno.cm(), "maDatiName");
        TransactionColumn dalNumberCol = new TransactionColumn(Messages.Cislo.cm(), "dalNumber");
        TransactionColumn dalNameCol = new TransactionColumn(Messages.Jmeno.cm(), "dalName");
        TransactionColumn documentCol = new TransactionColumn(Messages.Doklad.cm(), "documentName");
        TransactionColumn relatedDocumentCol = new TransactionColumn(Messages.Parovy_doklad.cm(), "relatedDocumentName");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Transaction, LocalDate>, ObservableValue<LocalDate>>() {

            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<Transaction, LocalDate> param) {
                Optional<LocalDate> date = param.getValue().getDate();
                return new SimpleObjectProperty(date.isPresent() ? date.get()
                        .format(Global.instance.df()) : "");
            }
        });
        TransactionColumn madati = new TransactionColumn(Messages.Ma_dati.cm(), madatiNumberCol, madatiNameCol);
        TransactionColumn dal = new TransactionColumn(Messages.Dal.cm(), dalNumberCol, dalNameCol);
        tw.getColumns().addAll(idCol, dateCol, amountCol, madati, dal, documentCol, relatedDocumentCol);
        tw.setRowFactory(new Callback<TableView<Transaction>, TableRow<Transaction>>() {
            @Override
            public TableRow<Transaction> call(TableView<Transaction> param) {
                TableRow<Transaction> tr = new TableRow<>();
                tr.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            ContextMenu cm = new ContextMenu(
                                    TransactionDeleteAction.instance.createMenuItem());
                            if (getSelected().get().isInit()) {
                            } else {
                                cm.getItems().add(UpdateTransactionAction.instance.createMenuItem());
                            }
                            cm.show(MainWindow.getInstance().getPrimaryStage());
                        }
                    }
                });
                return tr;
            }
        });
        tw.setItems(transactions);
        return tw;
    }

    @Override
    public void refresh() {
        try {
            Stream<TransactionP> tp = Facade.instance.getTransactions(optOd,
                    optDo, Optional.empty(), optDocument, optRelatedDocument).stream()
                    .map(t -> new TransactionP(t));
            transactions.setAll(tp.collect(Collectors.toList()));
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }
    }

}
