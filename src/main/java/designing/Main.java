package designing;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application
{
    double x,y=0;
    @Override
    public void start(Stage primaryStage) throws IOException
    {

        try
        {   //get the url
            URL url = new File("E:\\ProjectOOP\\OOP1\\src\\main\\java\\designing\\Scene1.fxml").toURI().toURL();
            URL url2 = new File("E:\\ProjectOOP\\OOP1\\src\\main\\java\\designing\\application.css").toURI().toURL();
            Parent root = FXMLLoader.load(url);//create parent object for scene1.
            Scene scene = new Scene(root);

            String css = url2.toString();   //apply css file.
            scene.getStylesheets().add(css);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);                   //show the scene
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}