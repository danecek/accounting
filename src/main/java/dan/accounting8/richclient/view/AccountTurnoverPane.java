package dan.accounting8.richclient.view;

import dan.accounting8.business.Facade;
import dan.accounting8.business.Global;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.Transaction;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.controller.actions.TransactionDeleteAction;
import dan.accounting8.richclient.controller.actions.UpdateTransactionAction;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public final class AccountTurnoverPane extends TransactionsAbstractPane {

    static class Sum {

        long maDatiSum;
        long dalSum;
        long totalSum;
    }

    Sum sum = new Sum();
    ObservableList<Sum> sums = FXCollections.singletonObservableList(sum);
    Optional<AnalAcc> optAcc;

    TableView<Sum> sumaView() {
        TableView<Sum> sv = new TableView<Sum>();

        TableColumn<Sum, String> maDatiSumCol = new TableColumn<Sum, String>(Messages.Ma_dati_celkem.cm());
        maDatiSumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sum, String> param) {
                return new SimpleStringProperty(Long.toString(sum.maDatiSum));
            }
        });
        TableColumn<Sum, String> dalSumCol = new TableColumn<Sum, String>(Messages.Dal_celkem.cm());
        dalSumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sum, String> param) {
                return new SimpleStringProperty(Long.toString(sum.dalSum));
            }
        });
        TableColumn<Sum, String> totalSumCol = new TableColumn<Sum, String>(Messages.Konecny_stav.cm());
        totalSumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sum, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sum, String> param) {
                return new SimpleStringProperty(Long.toString(sum.totalSum));
            }
        });
        sv.getColumns().addAll(maDatiSumCol, dalSumCol, totalSumCol);
        sv.setItems(sums);
        return sv;
    }

    public AccountTurnoverPane(Optional<LocalDate> dO, AnalAcc acc) throws AccException {
        //   super(acc);
        this.optAcc = Optional.of(acc);
        VBox vb = new VBox(tableView = createContent(), sumaView());
        vb.setSpacing(20);
        setContent(vb);
        this.optDo = dO;
        setText(title(Optional.empty(), dO, Optional.empty(), Optional.empty()));
        refresh();
    }

    @Override
    protected TableView<Transaction> createContent() {
        TableView<Transaction> tw = new TableView<>();
        tw.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        TransactionColumn idCol = new TransactionColumn(Messages.Id.cm());
        TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>(Messages.Datum.cm());
        TransactionColumn madatiCol = new TransactionColumn(Messages.Ma_dati.cm());
        TransactionColumn dalCol = new TransactionColumn(Messages.Dal.cm());
        TransactionColumn crossCol = new TransactionColumn(Messages.Souvztaznost.cm());
        TransactionColumn dokladNameCol = new TransactionColumn(Messages.Doklad.cm());
        TransactionColumn dokladCommentCol = new TransactionColumn(Messages.Popis_dokladu.cm());
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
        madatiCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                if (optAcc.get().equals(param.getValue().getMaDati())) {
                    return new SimpleObjectProperty(param.getValue().getAmount());
                }
                return new SimpleObjectProperty<>("");

            }
        });
        dalCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                if (optAcc.get().equals(param.getValue().getDal())) {
                    return new SimpleObjectProperty(param.getValue().getAmount());
                }
                return new SimpleObjectProperty<>("");
            }
        });
        crossCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                if (optAcc.get().equals(param.getValue().getDal())) {
                    return new SimpleObjectProperty(param.getValue().getMaDati());
                } else if (optAcc.get().equals(param.getValue().getMaDati())) {
                    return new SimpleObjectProperty(param.getValue().getDal());
                } else {
                    throw new RuntimeException();
                }

            }
        });
        dokladNameCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        TransactionColumn dokladCol = new TransactionColumn(Messages.Doklad.cm(), dokladNameCol, dokladCommentCol);
        tw.getColumns().addAll(idCol, dateCol, dokladCol, madatiCol, dalCol, crossCol);

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
                            if (!getSelected().get().isInit()) {
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
            sum = new Sum();
            transactions.setAll(Facade.instance
                    .getTransactions(Optional.empty(), optDo, optAcc, Optional.empty(), Optional.empty()));
            System.out.println(optDo);
            transactions.forEach((t) -> {
                //    System.out.println(t);
                if (t.getMaDati().equals(optAcc.get())) {
                    sum.maDatiSum += t.getAmount();
                }
                if (t.getDal().equals(optAcc.get())) {
                    sum.dalSum += t.getAmount();
                }
            });
            sum.totalSum = sum.maDatiSum - sum.dalSum;
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

}
