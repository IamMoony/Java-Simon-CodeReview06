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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.util.List;

public class TeacherApp extends Application {

    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;

    private ListView<Classes> listView2;
    private ObservableList<Classes> data2;

    private Text txtIdData;
    private Text txtNameData;
    private Text txtSurnameData;
    private Text txtEmailData;

    private TeacherDataAccess dbaccess;

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
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Label lblTop = new Label("School Statistics of Teachers (1)");
        Label lblId = new Label("ID");
        Label lblName = new Label("Name");
        Label lblSurname = new Label("Surname");
        Label lblEmail = new Label("Email");
        Label wichClasses = new Label("Teaches this classes");

        txtIdData = new Text();
        txtNameData = new Text();
        txtSurnameData = new Text();
        txtEmailData = new Text();

        HBox idData = new HBox(lblId, txtIdData);
        HBox nameData = new HBox(lblName, txtNameData);
        HBox surnameData = new HBox(lblSurname, txtSurnameData);
        HBox emailData = new HBox(lblEmail, txtEmailData);

        HBox hBoxLblTop = new HBox(lblTop);

        VBox vBoxDataCenter = new VBox(idData, nameData, surnameData, emailData);
        vBoxDataCenter.setSpacing(20);

        VBox vBoxListView = new VBox();

        VBox vBoxListView2 = new VBox();

        root.setCenter(vBoxDataCenter);
        // hBoxLblTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setPadding(new Insets(15, 20, 10, 10));

        // View

        listView = new ListView<>();

        listView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        data = getDbData();
        listView.setItems(data);

        listView2 = new ListView<>();
        vBoxListView2.getChildren().add(listView2);

        vBoxListView.getChildren().add(listView);

        root.setLeft(vBoxListView);
        root.setRight(listView2);


        // TOP
        String cssLayoutHboxTop = "-fx-border-color: purple;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-radius: 10 10 10 10;\n" +
                "-fx-background-radius: 10 10 0 0;" +
                "-fx-border-width: 3;\n" +
                "-fx-padding: 15;";

        hBoxLblTop.setAlignment(Pos.TOP_CENTER);
        hBoxLblTop.setStyle(cssLayoutHboxTop);
        root.setTop(hBoxLblTop);


        // Scene View

        Scene scene = new Scene(root, 800, 400);

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

            // set id, name, surname and email fields for the selected teacher
            Teacher teacher = data.get(new_val.intValue());
            txtIdData.setText(Integer.toString(teacher.getTeacherId()));
            txtNameData.setText(teacher.getTeacherName());
            txtSurnameData.setText(teacher.getTeacherSurname());
            txtEmailData.setText(teacher.getTeacherEmail());

            data2 = getDbData2(Integer.valueOf(txtIdData.getText()));

            listView2.setItems(data2);

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

    private ObservableList<Classes> getDbData2(int i) {

        List<Classes> list2 = null;

        try {
            list2 = dbaccess.getAllRows2(i);
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Classes> dbData = FXCollections.observableList(list2);
        return dbData;
    }

    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
}
