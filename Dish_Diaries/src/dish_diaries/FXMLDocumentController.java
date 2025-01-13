package dish_diaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField userid1;
    @FXML
    private TextField password1;
    @FXML
    private Button btn1;
    @FXML
    private Hyperlink link1;
    @FXML
    private Label msglabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void submit1(ActionEvent event) {
        String userId = userid1.getText();
        String password = password1.getText();

        if (!userId.isEmpty() && !password.isEmpty()) {
            if (validateLogin(userId, password)) {
                msglabel.setText("Login Successful!");
                loadDashboard();
            } else {
                msglabel.setText("Invalid credentials, try again.");
            }
        } else {
            msglabel.setText("Please fill in both User ID and Password fields.");
        }
    }

    private boolean validateLogin(String userId, String password) {
        return userId.equals("admin") && password.equals("1234");
    }

    private void loadDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage stage = (Stage) btn1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            msglabel.setText("Failed to load Dashboard.");
        }
    }

    @FXML
    private void link(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Regform.fxml"));
        Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Registration Form");
        stage.show();
    }
}
