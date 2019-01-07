/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dan.accounting8.richclient.view;

import dan.accounting8.business.Facade;
import dan.accounting8.model.Company;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.richclient.controller.actions.CompanyDeleteAction;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class CompanyPane extends AbstrPane {

    private final ObservableList<Company> companies;
    TableView<Company> tw;

    public CompanyPane() {
        super(Messages.Spolecnosti.cm());
        this.companies = FXCollections.observableArrayList();
        setContent(createContent());
        refresh();
    }

    @Override
    protected Node createContent() {
        tw = new TableView<>();
        PaneColumn idCol = propColumn(Messages.Cislo.cm(), "id");
        PaneColumn nameCol = propColumn(Messages.Jmeno.cm(), "name");
        PaneColumn descriptionCol = propColumn(Messages.Popis.cm(), "description");
        PaneColumn addressCol = propColumn(Messages.Adresa.cm(), "addressText");
        tw.getColumns().addAll(idCol, nameCol, descriptionCol, addressCol);
        tw.setItems(companies);
        tw.setRowFactory(gencb(CompanyDeleteAction.instance));
        return tw;
    }

    @Override
    public void refresh() {
        try {
            companies.setAll(Facade.instance.getAllCompanies());
        } catch (AccException ex) {
            MainWindow.showException(ex);
        }

    }

    public Optional<Company> getSelected() {
        return Optional.ofNullable(tw.getSelectionModel().getSelectedItem());
    }

}
