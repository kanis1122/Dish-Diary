package dish_diaries;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.sql.DriverManager;

public class DashboardddController implements Initializable {

    @FXML
    private ImageView imgview;
    @FXML
    private Label showingdetails;
    @FXML
    private ListView<Pair<String, String>> listview;
    @FXML
    private TextField detailswrite;
    @FXML
    private Button selectfile;
    @FXML
    private Button Insert;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    private File selectedFile;
    private ObservableList<Pair<String, String>> items = FXCollections.observableArrayList();
    @FXML
    private TextField searchfield;
    @FXML
    private Button search;
    @FXML
    private Button logout;
    @FXML
    private Button refreshbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String sql = "SELECT image_url, details FROM items";
    try (Connection conn = connectDB(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            items.add(new Pair<>(rs.getString("image_url"), rs.getString("details")));
        }
        listview.setItems(items);
    } catch (Exception e) {
        e.printStackTrace();
    }

    listview.setOnMouseClicked(event -> showSelectedItem());
}

    
    private Connection connectDB() {
    try {
        String url = "jdbc:mysql://localhost:3306/dish_diaries"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = ""; // Replace with your database password
        return DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
  
    
@FXML
private void searchbtn(ActionEvent event) {
    String searchText = searchfield.getText().toLowerCase();
    System.out.println("Searching for: " + searchText);  // Debugging print statement

    if (searchText.isEmpty()) {
        System.out.println("Search text is empty.");
        return; 
    }

    String sql = "SELECT image_url, details FROM items WHERE LOWER(image_url) LIKE ?";
    try (Connection conn = connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + searchText + "%");  // Set search pattern

       
        ResultSet rs = stmt.executeQuery();

      
        ObservableList<Pair<String, String>> filteredItems = FXCollections.observableArrayList();
        
        
        while (rs.next()) {
            filteredItems.add(new Pair<>(rs.getString("image_url"), rs.getString("details")));
        }

       
        listview.setItems(filteredItems);

       
        System.out.println("Results found: " + filteredItems.size());
        
      
        if (filteredItems.isEmpty()) {
            System.out.println("No results found for the search.");
        }
    } catch (Exception e) {
        e.printStackTrace();  
    }
}
  
    @FXML
    private void uploadfile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedFile = fileChooser.showOpenDialog(null);
    }
 
    
    
    @FXML
    private void Insertbtn(ActionEvent event) {
    if (selectedFile != null && !detailswrite.getText().isEmpty()) {
        String imageUrl = selectedFile.toURI().toString();
        String details = detailswrite.getText();

        String sql = "INSERT INTO items (image_url, details) VALUES (?, ?)";
        try (Connection conn = connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, imageUrl);
            stmt.setString(2, details);
            stmt.executeUpdate();
            items.add(new Pair<>(imageUrl, details)); // Add to ListView
            detailswrite.clear();
            selectedFile = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

  
    private void showSelectedItem() {
        Pair<String, String> selectedItem = listview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            imgview.setImage(new Image(selectedItem.getKey()));
            showingdetails.setText(selectedItem.getValue());
            showingdetails.setWrapText(true);
        }
    }

   @FXML
    private void updatebtn(ActionEvent event) {
    int selectedIndex = listview.getSelectionModel().getSelectedIndex();
    if (selectedIndex != -1 && !detailswrite.getText().isEmpty()) {
        String details = detailswrite.getText();
        String imageUrl = items.get(selectedIndex).getKey();

        String sql = "UPDATE items SET details = ? WHERE image_url = ?";
        try (Connection conn = connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, details);
            stmt.setString(2, imageUrl);
            stmt.executeUpdate();
            items.set(selectedIndex, new Pair<>(imageUrl, details)); // Update ListView
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    
    
    @FXML
    private void deletebtn(ActionEvent event) {
    int selectedIndex = listview.getSelectionModel().getSelectedIndex();
    if (selectedIndex != -1) {
        String imageUrl = items.get(selectedIndex).getKey();

        String sql = "DELETE FROM items WHERE image_url = ?";
        try (Connection conn = connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, imageUrl);
            stmt.executeUpdate();
            items.remove(selectedIndex); // Remove from ListView
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 


    @FXML
    private void logoutbtn(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @FXML
private void refreshPage(ActionEvent event) {
    // Reset or reload the page without fetching data from the database again.
    listview.setItems(items);  // Reset ListView to the original list
    searchfield.clear();       // Clear any search field text
    detailswrite.clear();      // Clear any input details
    imgview.setImage(null);    // Clear the image view
    showingdetails.setText("");  // Clear the details label
}



   
}
