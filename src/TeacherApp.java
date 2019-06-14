import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class TeacherApp extends Application {

    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;
    private TeacherDataAccess dbaccess;
    private Label lblIdData;
    private Label lblNameData;
    private Label lblSurnameData;
    private Label lblEmailData;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {

        try {
            dbaccess = new TeacherDataAccess();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
            dbaccess.closeDb();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Label lblTop = new Label("School Statistics of Teachers (1)");
        Label lblId = new Label("ID");
        Label lblName = new Label("Name");
        Label lblSurname = new Label("Surname");
        Label lblEmail = new Label("Email");
        HBox idData = new HBox(lblId, lblIdData);
        HBox nameData = new HBox(lblName, lblNameData);
        HBox surnameData = new HBox(lblSurname, lblSurnameData);
        HBox emailData = new HBox(lblEmail, lblEmailData);
        HBox hBoxLblTop = new HBox(lblTop);
        VBox vBoxDataCenter = new VBox(idData, nameData, surnameData, emailData);
        // hBoxLblTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setPadding(new Insets(15, 20, 10, 10));

        // View

        listView = new ListView<>();

        listView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        data = getDbData();
        listView.setItems(data);
        root.setLeft(listView);
        root.setCenter(vBoxDataCenter);

        // TOP
        String cssLayoutHboxTop = "-fx-border-color: purple;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-radius: 10 10 10 10;\n" +
                "-fx-background-radius: 10 10 0 0;" +
                "-fx-border-width: 3;\n" +
                "-fx-padding: 15;";

        // CENTER





        hBoxLblTop.setAlignment(Pos.TOP_CENTER);
        hBoxLblTop.setStyle(cssLayoutHboxTop);
        root.setTop(hBoxLblTop);


        // CENTER


        // Scene View

        Scene scene = new Scene(root, 550, 250);

        primaryStage.setTitle("School Statistics of Teachers (1)");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private class ListSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {

                return; // invalid data
            }

            // set name and desc fields for the selected teacher
            Teacher teacher = data.get(new_val.intValue());
            //lblid.setText(teacher.getTeacherId());
            lblNameData.setText(teacher.getTeacherName());
            lblSurnameData.setText(teacher.getTeacherSurname());
            lblEmailData.setText(teacher.getTeacherEmail());
            // actionstatus.setText(todo.getName() + " - selected");
        }
    }

    private ObservableList<Teacher> getDbData() {

        List<Teacher> list = null;

        try {
            list = dbaccess.getAllRows();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Teacher> dbData = FXCollections.observableList(list);
        return dbData;
    }

    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
}
