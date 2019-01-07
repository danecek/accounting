package dan.accounting8.richclient.dialogs;

import dan.accounting8.richclient.view.BalancePane;
import dan.accounting8.richclient.MainWindow;
import dan.accounting8.util.AccException;
import dan.accounting8.util.Messages;
import java.time.Month;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BalanceCreateDialog extends AbstractDialog {

    private ComboBox<Month> monthCB;

    public BalanceCreateDialog() throws AccException {
        super(Messages.Vytvor_rozvahu.cm());
    }

    @Override
    protected Node createContent() {
        GridPane gp = genGP();
        gp.add(new Label(Messages.Mesic.cm() + DEL), 0, 0);
        gp.add(monthCB = monthCB(), 1, 0);
        return gp;
    }

    @Override
    public void ok() {
        BalancePane table = new BalancePane(monthCB.getValue());
        Tab balsheet = new Tab(Messages.Rozvaha.cm() + DEL
                + BalanceCreateDialog.monthFormater.format(monthCB.getValue()), table);
        TabPane tp = MainWindow.getInstance().getTabPane();
        tp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

                Stage st = new Stage(StageStyle.DECORATED);
                BalancePane bp = new BalancePane(monthCB.getValue());
                st.setScene(new Scene(bp));//, pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight()));
                PrinterJob job = PrinterJob.createPrinterJob();
                st.show();
                if (job != null) {
                    boolean successPrintDialog = job.showPrintDialog(MainWindow.getInstance().getPrimaryStage());
                    if (successPrintDialog) {
                        double scaleX = pageLayout.getPrintableWidth() / bp.getWidth();
                        double scaleY = pageLayout.getPrintableHeight() / bp.getHeight();
                        bp.getTransforms().add(new Scale(scaleX, scaleY));
                        boolean success = job.printPage(pageLayout, bp);
                        if (success) {
                            st.hide();
                            job.endJob();
                        }
                    }
                }
            }

//                Printer printer = Printer.getDefaultPrinter();
//                Stage dialogStage = new Stage(StageStyle.DECORATED);
//                PrinterJob job = PrinterJob.createPrinterJob(printer);
//                if (job != null) {
//                    boolean showDialog = job.showPageSetupDialog(null);//dialogStage);
//                    if (showDialog) {
//                        table.setScaleX(0.3);
//                        table.setScaleY(0.3);
//                        table.setTranslateX(-700);
//                        table.setTranslateY(-500);
//                        boolean success = job.printPage(table);
//                        if (success) {
//                            job.endJob();
//                        }
//                        table.setTranslateX(0);
//                        table.setTranslateY(0);
//                        table.setScaleX(1.0);
//                        table.setScaleY(1.0);
//                    }
//                }
//            }
        });
        tp.getTabs().add(balsheet);
        tp.getSelectionModel().select(balsheet);
    }

    @Override
    public void validate() {
        errorPane.setError(monthCB.getValue() == null
                ? Optional.of(Messages.neplatny_mesic.cm()) : Optional.empty());
    }

}
