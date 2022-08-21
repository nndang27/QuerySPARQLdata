package designing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;

import outputting.*;

public class InformationBox {
    @FXML
    TextField fileName;
    @FXML
    TextArea SPARQLtext;
    @FXML
    RadioButton TurtleCheck,JSONCheck,RDFCheck,NTRIPLESCheck;


    public static String filename;
    public static String sparql;
    public static String format;
    public static File file;
    public static boolean isCreated;
    public static boolean isSPARQL;
    public static boolean isTicked;
    public InformationBox(){}
    public void handlingInput(ActionEvent event) throws IOException{
        FileHandling tu1 = new FileHandling();
        filename = fileName.getText();
        sparql = SPARQLtext.getText();
        System.out.println(sparql);

        if(TurtleCheck.isSelected())//check format is selected?.
        {
            tu1 = new TurtleFile();
            isTicked = true;
        }
        else if (JSONCheck.isSelected())
        {
            tu1 = new JSONFile();
            isTicked = true;
        }
        else if (RDFCheck.isSelected())
        {
            tu1 = new RDFFile();
            isTicked = true;
        }
        else if (NTRIPLESCheck.isSelected())
        {
            tu1 = new NTRIPLESFile();
            isTicked = true;
        }
        else{ isTicked = false; }


        if(filename.equals(""))//check filename is valid?
        {
            tu1.createFile();
            file = tu1.file;
            isCreated = true;
            isSPARQL = tu1.writeOut(sparql);//check SPARQL is valid?

        }
        else
        {
            if(tu1.createFile(filename)) //check filename is valid?
            {
                file = tu1.file;
                isCreated = true;
                isSPARQL = tu1.writeOut(sparql);//check SPARQL is valid
            }
            else//check filename is valid?
            {
                isCreated = false;
                if(!tu1.writeOut(sparql)){ isSPARQL = false;}//check SPARQL is valid
                else if(tu1.writeOut(sparql)){ isSPARQL = true;}
            }
        }

    }
}
