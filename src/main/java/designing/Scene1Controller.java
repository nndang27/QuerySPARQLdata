package designing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scene1Controller implements Initializable {
    @FXML
    TextField typeUserName;
    @FXML
    Text helloText;
    @FXML
    Pane Pane1,Pane2,Pane3,Pane4,Pane5;
    private Scene scene;
    private Parent root;
    private Stage stage;
    public static String username;

    public void initialize(URL location, ResourceBundle resources){ //method for show the scene in initial.
        FadeAnimation<Pane> paneScene1 = new FadeAnimation<Pane>(Pane1,Pane2,Pane3,Pane4,Pane5);//fade animation for background.
        paneScene1.sliderAnimation();
    }
    public void getUserName(ActionEvent event) throws IOException{
        username = typeUserName.getText();//get the username from input.
        System.out.println(username);

        URL url = new File("E:\\ProjectOOP\\OOP1\\src\\main\\java\\designing\\Scene2.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);  //press the button will open scene2
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
