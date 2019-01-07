package dan.accounting8.richclient.view;

import dan.accounting8.business.Facade;
import dan.accounting8.business.balance.BalanceItem;
import dan.accounting8.model.AccGroup;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.dialogs.AbstractDialog;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.time.Month;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public final class BalancePane extends AbstrPane {

    private final ObservableList<BalanceItem> balanceItems = FXCollections.observableArrayList();
    TableView<BalanceItem> tableView;
    Month month;

    static String title(Month month) {
        return Messages.Rozvaha_pro_mesic.cm() + " : "
                + AbstractDialog.monthFormater.format(month);
    }

    public BalancePane(Month month) {
        super(Messages.Rozvaha.cm());
        setText(title(month));
        setContent(tableView = createContent());
        refresh();
    }

    @Override
    public void refresh() {
        try {
            balanceItems.setAll(Facade.instance.getAllBalanceItems(month));
        } catch (AccException ex) {
            MainWindow.getInstance().showException(ex);
        }
    }

    @Override
    public Optional getSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class BalanceColumn extends PaneColumn {

        public BalanceColumn(String text, int prefWidth) {
            this(text);
            setPrefWidth(prefWidth);
        }

        public BalanceColumn(String text) {
            super(text);
        }

    }

    @Override
    protected TableView<BalanceItem> createContent() {
        TableView<BalanceItem> tw = new TableView<>();
        tw.setPrefHeight(2000);
        tw.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        BalanceColumn groupCol = new BalanceColumn(Messages.Skupina.cm(), 200);
        BalanceColumn analCol = new BalanceColumn(Messages.Analytika.cm(), 200);
        BalanceColumn nameCol = new BalanceColumn(Messages.Jmeno.cm(), 400);

        BalanceColumn initAssetsCol = new BalanceColumn(Messages.Aktiva.cm(), 200);
        BalanceColumn initLiabilitiesCol = new BalanceColumn(Messages.Pasiva.cm(), 200);
        BalanceColumn assetsCol = new BalanceColumn(Messages.Ma_dati.cm(), 200);
        BalanceColumn liabilitiesCol = new BalanceColumn(Messages.Dal.cm(), 200);
        BalanceColumn assetsSumCol = new BalanceColumn(Messages.Ma_dati.cm(), 200);
        BalanceColumn liabilitiesSumCol = new BalanceColumn(Messages.Dal.cm(), 200);
        BalanceColumn finalAssetsCol = new BalanceColumn(Messages.Aktiva.cm(), 200);
        BalanceColumn finalLiabilitiesCol = new BalanceColumn(Messages.Pasiva.cm(), 200);

        groupCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(param.getValue().getNumber());
            }
        });
        groupCol.setCellFactory(new Callback<TableColumn<BalanceItem, String>, TableCell<BalanceItem, String>>() {
            @Override
            public TableCell<BalanceItem, String> call(TableColumn<BalanceItem, String> param) {
                return new TableCell<BalanceItem, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            return;
                        }
                        setText(item);
                        BalanceItem value = getTableView().getItems().get(getIndex());
                        if (value.getGroup().isPresent()) {
                            AccGroup g = value.getGroup().get();
                            TableRow tr = getTableRow();
                            // tr.setStyle("-fx-background-color:ligthgrey")
                            switch (g.getGroupType()) {
                                case SYNT_ACCOUNT:
                                    tr.setStyle("-fx-background-color:lightgray");
                                    break;
                            }
                        }
                    }
                };
            }
        });

        analCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                String a = "";
                Optional<AccGroup> g = param.getValue().getGroup();
                if (g.isPresent()) {
                    AccGroup group = g.get();
                    if (group instanceof AnalAcc) {
                        a = ((AnalAcc) group).getAnal();
                    }
                }
                return new SimpleStringProperty(a);
            }
        });

        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        initAssetsCol.setCellValueFactory(new PropertyValueFactory<>("initAssets"));
        assetsCol.setCellValueFactory(new PropertyValueFactory<>("assets"));
        assetsSumCol.setCellValueFactory(new PropertyValueFactory<>("assetsSum"));
        finalAssetsCol.setCellValueFactory(new PropertyValueFactory<>("finalAssets"));
        //************************************************************************
        //liabilitiesCol.setCellValueFactory(new PropertyValueFactory<>("liabilities"));
        initLiabilitiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(Long.toString(-param.getValue().getInitLiabilities()));
            }
        });
        liabilitiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(Long.toString(-param.getValue().getLiabilities()));
            }
        });
        liabilitiesSumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(Long.toString(-param.getValue().getLiabilitiesSum()));
            }
        });
        finalLiabilitiesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BalanceItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BalanceItem, String> param) {
                return new SimpleStringProperty(Long.toString(-param.getValue().getFinalLiabilities()));
            }
        });
        // nested columns: *****************************************************************
        BalanceColumn accCol = new BalanceColumn(Messages.Ucet.cm());
        accCol.getColumns().addAll(groupCol, analCol, nameCol);
        BalanceColumn initCol = new BalanceColumn(Messages.Pocatecni.cm());
        initCol.getColumns().addAll(initAssetsCol, initLiabilitiesCol);
        BalanceColumn obratCol = new BalanceColumn(Messages.Obrat.cm());
        obratCol.getColumns().addAll(assetsCol, liabilitiesCol);
        BalanceColumn obratNacitanyCol = new BalanceColumn(Messages.Obrat_nacitany.cm());
        obratNacitanyCol.getColumns().addAll(assetsSumCol, liabilitiesSumCol);
        BalanceColumn finalCol = new BalanceColumn(Messages.Konecna.cm());
        finalCol.getColumns().addAll(finalAssetsCol, finalLiabilitiesCol);
        // ************************************************************************************
        Stream.of(accCol, initCol, obratCol, obratNacitanyCol, finalCol)
                .forEach((c) -> tw.getColumns().add(c));
        tw.setItems(balanceItems);
        return tw;
    }

}
