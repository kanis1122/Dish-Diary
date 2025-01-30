package dish_diaries;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegformController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField userid;
    @FXML
    private TextField passid;
    @FXML
    private RadioButton op1;
    @FXML
    private ToggleGroup radiobutton;
    @FXML
    private RadioButton op2;
    @FXML
    private Button btn2;
    @FXML
    private Label msglabel1;
    @FXML
    private Button backbtn1;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dish_diaries";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void radiobutton(ActionEvent event) {
    }

    @FXML
    private void submit2(ActionEvent event) {
        String userId = userid.getText();
        String password = passid.getText();
        String fullname = name.getText();
        String selectoption = op1.isSelected() ? op1.getText() : op2.isSelected() ? op2.getText() : "None";

        if (userId.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            msglabel1.setText("Please Fill in All The Fields!");
            msglabel1.setStyle("-fx-text-fill: red;");
        } else {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                String query = "INSERT INTO reg (name, userid, pass, gender) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, fullname);
                statement.setString(2, userId);
                statement.setString(3, password);
                statement.setString(4, selectoption);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    msglabel1.setText("Registration Success!");
                    msglabel1.setStyle("-fx-text-fill: green;");

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent loginPage = loader.load();
                        Stage stage = (Stage) btn2.getScene().getWindow();
                        stage.setScene(new Scene(loginPage));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    msglabel1.setText("Error in Registration. Try Again.");
                    msglabel1.setStyle("-fx-text-fill: red;");
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                msglabel1.setText("Database Error. Try Again.");
                msglabel1.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    private void backbtn(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
