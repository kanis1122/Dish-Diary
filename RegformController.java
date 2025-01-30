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

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dish_diaries"; // Replace with your DB URL
    private static final String DB_USERNAME = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = ""; // Replace with your DB password

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Any initialization can be added here
    }

    @FXML
    private void radiobutton(ActionEvent event) {
        // Handle radio button if necessary
    }

    @FXML
    private void submit2(ActionEvent event) {
        String userId = userid.getText();
        String password = passid.getText();
        String fullname = name.getText();
        String selectoption = op1.isSelected() ? op1.getText() : op2.isSelected() ? op2.getText() : "None";

        // Print values for debugging
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);
        System.out.println("Name: " + fullname);
        System.out.println("Selected option: " + selectoption);

        // Check if any fields are empty
        if (userId.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            msglabel1.setText("Please Fill in All The Fields!");
            msglabel1.setStyle("-fx-text-fill: red;");
        } else {
            // Insert data into database
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

                    // Proceed to the Login Page
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent loginPage = loader.load();

                        // Get the current stage and set the new scene
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
}
