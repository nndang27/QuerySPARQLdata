package outputting;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import querying.WebData;
import java.util.List;
public class Variables {
    public String[][] S = new String[200][200];//A 2 dimension string to store variables are queried in website.
    public List<String> columnNames;
    public int index=1;// a variable to count the total row of string S
    public ResultSet results;//object results to get the result directly.
    public Variables(){}
    public boolean getVariables(String spa)
    {
        WebData w = new WebData();  //Aggregation for using WebData object
        w.sparql=spa;
        try {
             results = w.queryProcessing().execSelect(); //object results for check query data successfully or not.
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        columnNames = results.getResultVars();// list columnNames to store the variable name of SPARQL text.

        for(int j=0;j< columnNames.size();j++) //put the data of columnNames into first row of string S.
        {
            S[0][j] = columnNames.get(j);
        }

        for ( ; results.hasNext() ; )
        {
            QuerySolution soln = results.nextSolution() ;
            for(int i=0;i<columnNames.size();i++)    //put the values of variables into remain rows of string S.
            {
                String columnName=columnNames.get(i);
                RDFNode rdfNode=soln.get(columnName);
                S[index][i] = rdfNode.toString();
            }
            index++;
        }

        for(int i=0;i<index;i++)    //print out all data are queried.
        {
            for(int j=0;j<columnNames.size();j++)
            {
                System.out.print(S[i][j]+"  ");
            }
            System.out.println("\n\n");
        }
        return true;
    }
}
