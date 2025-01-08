/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dish_diaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author HP
 */
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);
        System.out.println("Name: " + fullname);
        System.out.println("Selected option: " + selectoption);
        
        if(userId.isEmpty() || password.isEmpty() || fullname.isEmpty()){
            msglabel1.setText("Please Fill is All The Fields!");
            msglabel1.setStyle("-fx-text-fill: red;");
        } else{
            msglabel1.setText("Registration Success!");
            msglabel1.setStyle("-fx-text-fill: green;");
        }
    }
    
}
