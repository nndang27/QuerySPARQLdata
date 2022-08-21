package designing;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import outputting.Variables;

public class Scene2Controller extends InformationBox implements Initializable {
    @FXML
    ImageView trueTick1,wrongTick1,trueTick2,wrongTick2,trueTick3,wrongTick3;
    @FXML
    TableView myTable;
    @FXML
    Text countResults,helloText;;
    @FXML
    Label warningFilename,warningSPARQL,warningFormat,correctFilenameLabel,correctFilenameLabel2;;
    Variables var = new Variables();
    private Scene scene;
    private Parent root;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        trueTick1.setVisible(false);
        wrongTick1.setVisible(false);
        trueTick2.setVisible(false);
        wrongTick2.setVisible(false);
        trueTick3.setVisible(false);
        wrongTick3.setVisible(false);
        countResults.setText("0");
        warningFilename.setVisible(false);              //Set symbols and labels invisible.
        warningFormat.setVisible(false);
        warningSPARQL.setVisible(false);
        correctFilenameLabel.setVisible(false);
        correctFilenameLabel2.setVisible(false);

        //intro text
        TextOutput textOutput = new TextOutput() {
            @Override
            public void writeText(String textOut) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        helloText.setText(textOut);
                    }
                });
            }
        };
        String introText = "Hello "+Scene1Controller.username ;
        TextAnimator textAnimator = new TextAnimator(introText, 100, textOutput);//create run animation for text
        Thread thread = new Thread(textAnimator);
        thread.start();
    }
    public void runSearch(ActionEvent event) throws IOException{
        var = new Variables();
        super.handlingInput(event);//check conditions of filename, SPARQL, format file.

        myTable.getColumns().clear(); //clear all table after each press search button.
        myTable.getItems().clear();

        if(isCreated){
            trueTick1.setVisible(true);
            wrongTick1.setVisible(false);
            warningFilename.setVisible(false);                  //check filename and comment.
            correctFilenameLabel.setVisible(true);
            correctFilenameLabel2.setVisible(true);
            correctFilenameLabel.setText("File created successfully in path:");
            correctFilenameLabel2.setText(file.getAbsolutePath());
        }
        else{
            trueTick1.setVisible(false);
            wrongTick1.setVisible(true);
            correctFilenameLabel.setVisible(false);
            correctFilenameLabel2.setVisible(false);
            warningFilename.setVisible(true);
            warningFilename.setText("File is created, choose another name!");
        }

        if (isSPARQL){
            trueTick2.setVisible(true);                       //check SPARQL text and comment.
            wrongTick2.setVisible(false);
            warningSPARQL.setVisible(false);
        }
        else{
            trueTick2.setVisible(false);
            wrongTick2.setVisible(true);
            warningSPARQL.setVisible(true);
            warningSPARQL.setText("SPARQL text is invalid, try again!");
        }

        if (isTicked){
            trueTick3.setVisible(true);
            wrongTick3.setVisible(false);
            warningFormat.setVisible(false);                //check format and comment.
        }
        else{
            trueTick3.setVisible(false);
            wrongTick3.setVisible(true);
            warningFormat.setVisible(true);
            warningFormat.setText("Please choose one format!");
        }

        InsertTable();               //put data is queried into table.
        countResults.setText(String.valueOf(var.index-1)); //set label to count total results.
    }

    public void InsertTable() {
        if(!var.getVariables(sparql)){return ;}  //check getting data sucessfully or not.
        else {

            int number=1;
            ObservableList<ObservableList> csvData = FXCollections.observableArrayList();

            int k=0;
            for (int i = -1; i < var.columnNames.size(); i++) {
                final int finalIdx = i+1;
                if(k==0){
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>("ID");
                    column.setCellValueFactory(param ->
                            new ReadOnlyObjectWrapper<>(param.getValue().get(0))          //insert column ID
                    );
                    myTable.getColumns().add(column);
                    k++;
                }
                else {

                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(var.S[0][i]);
                    column.setCellValueFactory(param ->
                            new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)) //insert columns for variable names.
                    );
                    myTable.getColumns().add(column);
                }
            }
                //ROW
                for(int j=1;j<var.index;j++) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    row.clear();
                    row.add(String.valueOf(number));
                    for(int z=0;z<var.columnNames.size();z++){          //insert rows.
                        row.add(var.S[j][z]);
                    }
                    csvData.add(row);
                    number++;
                }
                myTable.setItems(csvData);
        }
    }
    public void returnScene1(ActionEvent event)throws IOException{ //Button Back.
        URL url = new File("E:\\ProjectOOP\\OOP1\\src\\main\\java\\designing\\Scene1.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openFile(){  //button OpenFile.
        FileChooser f = new FileChooser(file);
    }

}

