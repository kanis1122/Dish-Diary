package dish_diaries;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable {

    @FXML
    private VBox vbox1, vbox2, vbox3, vbox4;

    @FXML
    private ImageView img1, img2, img3, img4;

    @FXML
    private Label title1, title2, title3, title4;

    @FXML
    private Label details1, details2, details3, details4;

    @FXML
    private TextField textfield, textfield2;

    @FXML
    private Button insertbutton, updatebutton, deletebutton, addimagebutton;

    @FXML
    private Label alart;

    private ImageView selectedImageView;
    private Label selectedTitleLabel;
    private Label selectedDetailsLabel;

    private File selectedFile; // Store the selected file for insertion

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Map image views to their title and details labels
        setImageClickListener(img1, title1, details1);
        setImageClickListener(img2, title2, details2);
        setImageClickListener(img3, title3, details3);
        setImageClickListener(img4, title4, details4);
    }

    private void setImageClickListener(ImageView imageView, Label titleLabel, Label detailsLabel) {
        imageView.setOnMouseClicked(event -> {
            selectedImageView = imageView;
            selectedTitleLabel = titleLabel;
            selectedDetailsLabel = detailsLabel;

            // Pre-fill text fields for editing
            textfield.setText(titleLabel.getText());
            textfield2.setText(detailsLabel.getText());
        });
    }

    private File chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));
        Stage stage = (Stage) addimagebutton.getScene().getWindow();
        return fileChooser.showOpenDialog(stage);
    }

    private void setAlert(String message, String color) {
        alart.setText(message);
        alart.setStyle("-fx-text-fill: white; -fx-background-color: " + color + ";");
    }

    @FXML
    private void insertbutton(ActionEvent event) {
        if (selectedFile == null) {
            setAlert("No image selected. Please use 'Add Image' to select a photo first.", "red");
            return;
        }

        String title = textfield.getText();
        String details = textfield2.getText();

        if (title.isEmpty() || details.isEmpty()) {
            setAlert("Please enter both title and details.", "red");
            return;
        }

        Image newImage = new Image(selectedFile.toURI().toString());

        if (selectedImageView != null) {
            selectedImageView.setImage(newImage);
            selectedTitleLabel.setText(title);
            selectedDetailsLabel.setText(details);

            setAlert("Image inserted successfully!", "green");
            selectedFile = null; // Reset the selected file after insertion
        } else {
            setAlert("Please select an image slot to insert the image.", "red");
        }
    }

    @FXML
    private void updatebutton(ActionEvent event) {
        File fileToUpdate = chooseImage();
        if (fileToUpdate != null && selectedImageView != null) {
            Image updatedImage = new Image(fileToUpdate.toURI().toString());
            selectedImageView.setImage(updatedImage);

            String updatedTitle = textfield.getText();
            String updatedDetails = textfield2.getText();

            if (!updatedTitle.isEmpty()) {
                selectedTitleLabel.setText(updatedTitle);
            }
            if (!updatedDetails.isEmpty()) {
                selectedDetailsLabel.setText(updatedDetails);
            }

            setAlert("Image updated successfully!", "green");
        } else {
            setAlert("Please select an image slot to update.", "red");
        }
    }

    @FXML
    private void deletebutton(ActionEvent event) {
        if (selectedImageView != null) {
            selectedImageView.setImage(null);
            selectedTitleLabel.setText("");
            selectedDetailsLabel.setText("");
            setAlert("Image deleted successfully!", "red");
        } else {
            setAlert("Please select an image slot to delete.", "red");
        }
    }

    @FXML
    private void addimagebutton(ActionEvent event) {
        selectedFile = chooseImage();
        if (selectedFile != null) {
            setAlert("Image selected. Now enter title and details, then click 'Insert'.", "green");
        } else {
            setAlert("No image selected.", "red");
        }
    }
}
