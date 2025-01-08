/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
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

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField userid1;
    @FXML
    private TextField password1;
    @FXML
    private Button btn1;
    @FXML
    private Hyperlink link1;
    private String usrId;
    @FXML
    private Label msglabel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit1(ActionEvent event) {
        String userId = userid1.getText();
        String password = password1.getText();
        
        System.out.println("user Id: " + userId);
        System.out.println("password " + password);
        
        if (!userId.isEmpty() && !password.isEmpty()) {
            System.out.println("Submitted successfully!");
            msglabel.setText("Submitted Success");
        }
        else {
            System.out.println("please fill in both user ID or password fields.");
            msglabel.setText("Not Success");
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
