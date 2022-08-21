package querying;

import org.apache.jena.query.*;
public class WebData implements DataOperations{
    // website location
    static String location = "http://dbpedia.org/sparql";
    //List of prefixes
    final String XSD = "http://www.w3.org/2001/XMLSchema#";
    final String DBR = "http://dbpedia.org/resource/";
    final String DBP = "http://dbpedia.org/property/";
    final String DBO = "http://dbpedia.org/ontology/";
    final String DBC = "http://dbpedia.org/resource/Category:";
    final String DBD = "http://dbpedia.org/datatype/";
    final String DBT = "http://dbpedia.org/resource/Template:";
    final String RDFS = "http://www.w3.org/2000/01/rdf-schema#";
    final String RDF = "https://www.w3.org/1999/02/22-rdf-syntax-ns#";
    final String FLOAF = "http://xmlns.com/floaf/0.1/";
    final String GEO = "http://www.w3.org/2003/01/geo/wgs84_pos#";
    final String SCHEMA = "http://schema.org/";
    final String YAGO = "http://dbpedia.org/class/yago/";
    final String DCT = "http://purl.org/dc/terms/";
    final String DC = "http://purl.org/dc/elements/1.1/";

    //SPARQL text
    public String sparql;
    public WebData() { }

    public QueryExecution queryProcessing()
    {
        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("xsd", XSD);
        queryStr.setNsPrefix("dbr", DBR);
        queryStr.setNsPrefix("dbp", DBP);
        queryStr.setNsPrefix("dbo", DBO);
        queryStr.setNsPrefix("dbc", DBC);
        queryStr.setNsPrefix("dbd", DBD);
        queryStr.setNsPrefix("dbt", DBT);
        queryStr.setNsPrefix("rdfs", RDFS);
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("floaf", FLOAF);
        queryStr.setNsPrefix("geo", GEO);
        queryStr.setNsPrefix("schema", SCHEMA);
        queryStr.setNsPrefix("yago", YAGO);
        queryStr.setNsPrefix("dct",DCT);
        queryStr.setNsPrefix("dc",DC);
        queryStr.append(sparql);

        //Create a Query object from SPARQL text;
        Query query = queryStr.asQuery();
        //Get the data in the website(location) based on above query;
        QueryExecution x = QueryExecutionFactory.sparqlService(location, query);

        return x;
    }
}
