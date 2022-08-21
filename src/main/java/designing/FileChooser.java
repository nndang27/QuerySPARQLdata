package designing;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import java.io.File;

public class FileChooser extends Application
{
    public FileChooser(File file)
    {
        HostServices hostServices = getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

    @Override
    public void start(Stage stage) throws Exception
    {
    }
}
