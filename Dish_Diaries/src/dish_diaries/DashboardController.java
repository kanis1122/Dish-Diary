package dish_diaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DashboardController implements Initializable {

    @FXML
    private HBox hbox1;
    @FXML
    private ImageView img1;
    @FXML
    private Label title1;
    @FXML
    private ImageView img2;
    @FXML
    private Label title2;
    @FXML
    private HBox hbox2;
    @FXML
    private ImageView img3;
    @FXML
    private Label title3;
    @FXML
    private ImageView img4;
    @FXML
    private Label title4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImageAndTitle(img1, title1, "file:resources/dish1.jpg", "Dish 1: Spaghetti");
        setImageAndTitle(img2, title2, "file:resources/dish2.jpg", "Dish 2: Tacos");
        setImageAndTitle(img3, title3, "file:resources/dish3.jpg", "Dish 3: Salad");
        setImageAndTitle(img4, title4, "file:resources/dish4.jpg", "Dish 4: Burger");
    }

    private void setImageAndTitle(ImageView imageView, Label label, String imagePath, String title) {
        try {
            Image image = new Image(imagePath);
            imageView.setImage(image);
            label.setText(title);
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath + " - " + e.getMessage());
        }
    }
}
