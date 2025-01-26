package dish_diaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.Image;

public class DashboardController implements Initializable {

    @FXML
    private Button insertbutton;
    @FXML
    private Button updatebutton;
    @FXML
    private Button deletebutton;
    @FXML
    private Button addimagebutton;
    @FXML
    private Label alart;
    @FXML
    private TextField textfield2;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView img1;
    @FXML
    private Label l1;
    @FXML
    private ImageView img2;
    @FXML
    private Label l2;
    @FXML
    private ImageView img3;
    @FXML
    private Label l3;
    @FXML
    private ImageView img4;
    @FXML
    private Label l4;
    @FXML
    private ImageView img5;
    @FXML
    private Label l5;
    @FXML
    private ImageView img6;
    @FXML
    private Label l6;
    private int currentImageIndex = 1;
    @FXML
    private Label labaaal1;

    public void setUserId(String userId) {
        labaaal1.setText("Welcome, " + userId);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handle(ActionEvent event) {
        if (event.getSource() == insertbutton) {
            String inputText = textfield2.getText();
            if (inputText != null && !inputText.trim().isEmpty()) {
                l1.setText(inputText);
                alart.setText("Inserted: " + inputText);
                textfield2.clear();
            } else {
                alart.setText("Please enter some text to insert.");
            }
        } else if (event.getSource() == updatebutton) {
            String inputText = textfield2.getText();
            if (inputText != null && !inputText.trim().isEmpty()) {
                switch (currentImageIndex) {
                    case 1: l1.setText(inputText); break;
                    case 2: l2.setText(inputText); break;
                    case 3: l3.setText(inputText); break;
                    case 4: l4.setText(inputText); break;
                    case 5: l5.setText(inputText); break;
                    case 6: l6.setText(inputText); break;
                }
                alart.setText("Updated: " + inputText);
                textfield2.clear();
            } else {
                alart.setText("Please enter some text to update.");
            }
        } else if (event.getSource() == deletebutton) {
            switch (currentImageIndex) {
                case 1: img1.setImage(null); l1.setText(""); break;
                case 2: img2.setImage(null); l2.setText(""); break;
                case 3: img3.setImage(null); l3.setText(""); break;
                case 4: img4.setImage(null); l4.setText(""); break;
                case 5: img5.setImage(null); l5.setText(""); break;
                case 6: img6.setImage(null); l6.setText(""); break;
            }
            alart.setText("Deleted the image and title.");
        }
    }

    @FXML
    private void addimagebutton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = (Stage) addimagebutton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            String title = textfield2.getText();

            switch (currentImageIndex) {
                case 1:
                    img1.setImage(image);
                    l1.setText(title.isEmpty() ? "Burger" : title);
                    break;
                case 2:
                    img2.setImage(image);
                    l2.setText(title.isEmpty() ? "Pizza" : title);
                    break;
                case 3:
                    img3.setImage(image);
                    l3.setText(title.isEmpty() ? "Pasta" : title);
                    break;
                case 4:
                    img4.setImage(image);
                    l4.setText(title.isEmpty() ? "Soup" : title);
                    break;
                case 5:
                    img5.setImage(image);
                    l5.setText(title.isEmpty() ? "BBQ" : title);
                    break;
                case 6:
                    img6.setImage(image);
                    l6.setText(title.isEmpty() ? "Fish_Fry" : title);
                    break;
            }

            alart.setText("Image " + currentImageIndex + " added with title: " + (title.isEmpty() ? "Image " + currentImageIndex : title));

            if (currentImageIndex < 6) {
                currentImageIndex++;
            } else {
                currentImageIndex = 1;
            }

            textfield2.clear();
        } else {
            alart.setText("No image selected.");
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        alart.setText("Mouse clicked on an image!");
    }
}
