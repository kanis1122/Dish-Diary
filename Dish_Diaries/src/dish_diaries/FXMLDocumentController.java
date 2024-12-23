/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dish_diaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        }
        else {
            System.out.println("please fill in both user ID or password fields.");
        }
    }

    @FXML
    private void link(ActionEvent event) {
    }
    
}
