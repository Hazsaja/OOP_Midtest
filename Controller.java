import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.input.MouseEvent;

public class Controller {

    private String[] usernames = {"admin", "user"};
    private String[] passwords = {"123", "456"};
    
    private double x = 0;
    private double y = 0;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String usernameInput = "admin"; // nanti ambil dari TextField
        String passwordInput = "123";   // nanti ambil dari PasswordField

        if (checkLogin(usernameInput, passwordInput)) {
            // inisialisasi data dummy
            Database.initSampleData();

            // pindah ke dashboard
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            root.setOnMousePressed((MouseEvent mouse) -> {
                x = mouse.getSceneX();
                y = mouse.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent mouse) -> {
               stage.setX(mouse.getScreenX() - x);
               stage.setY(mouse.getScreenY() - y);
            });
            
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } else {
            System.out.println("Login gagal!");
        }
    }

    private boolean checkLogin(String user, String pass) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(user) && passwords[i].equals(pass)) {
                return true;
            }
        }
        return false;
    }
    
    public void close(){
        System.exit(0);
    }
}
