package outputting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
public class JSONFile extends FileHandling {
    public boolean writeOut(String spa) throws IOException
    {
        w.sparql=spa;
        try
        {
            model = w.queryProcessing().execConstruct();   //check if SPARQL text is valid or not
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        if(!checkFile){return true;}//if create file fail, stop immediately and not continue to write into file.
        else
        {
            OutputStream outputStream = new FileOutputStream(file);   //creat a stream for a file.
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            model.write(outputStream, "JSON-LD");//writeIntoFile
            model.write(System.out, "JSON-LD"); //writeOutTheDisplay
            return true;
        }
    }
}
