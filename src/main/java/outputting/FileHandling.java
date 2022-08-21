package outputting;

import org.apache.jena.rdf.model.*;
import querying.WebData;
import java.io.*;
public class FileHandling implements FileOperations{
    public String filename;
    public File file;
    public WebData w = new WebData(); //Aggregation
    public static Model model;
    public boolean checkFile;
    static int index=0; // variable to rename file automatically.
    public FileHandling(){};
    public boolean createFile(String filename) throws IOException{
        this.file = new File(filename);
         //initialize File object and passing path as argument
        boolean result = false;
        try
        {
            result = file.createNewFile();  //creates a new file
            if(result)      // test if successfully created a new file
            {   checkFile = true;
                this.filename = filename;
                System.out.println("file created "+file.getCanonicalPath()); //returns the path string
                return true;
            }
            else
            {   checkFile = false;
                System.out.println("File already exist at location: "+file.getCanonicalPath());
                System.out.println("\nChoose another name\n");
                return false;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();    //prints exception if any
            return false;
        }
    }
    public boolean createFile() throws IOException{

        while(1==1){
            index++;
            this.filename="ontology_file" + Integer.toString(index) + ".txt";
            this.file = new File(filename); //initialize File object and passing path as argument
            boolean result;
            result = file.createNewFile();
            if(result)      // test if successfully created a new file
            {   checkFile = true;
                System.out.println("file created sucessfully"+file.getCanonicalPath()); //returns the path string
                return true;
            }
            else{
                checkFile = false;
                System.out.println("file already existed");
                return false;
            }

        }
    }

    public boolean writeOut(String spa) throws IOException {//simple method writeOut for 4 class extends this class
        return true;                                        //will complete it.
    }

}

