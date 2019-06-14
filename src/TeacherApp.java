import javafx.application.Application;
import javafx.beans.Observable;
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

public class Main extends Application {

    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;
    private TeacherDataAccess dbaccess;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Label lblTop = new Label("School Statistics of Teachers (1)");
        HBox hBoxLblTop = new HBox(lblTop);
        // hBoxLblTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        HBox hBoxCenter = new HBox();

        root.setPadding(new Insets(15, 20, 10, 10));

        // View

        listView = new ListView<>();

        listView.getSelectionModel().selectedIndexProperty().addListener();

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
}
