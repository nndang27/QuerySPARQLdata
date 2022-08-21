package outputting;

import java.io.IOException;

public interface FileOperations {
    public boolean createFile()throws IOException;
    public boolean createFile(String filename)throws IOException;
    public boolean writeOut(String spa) throws IOException;
}
