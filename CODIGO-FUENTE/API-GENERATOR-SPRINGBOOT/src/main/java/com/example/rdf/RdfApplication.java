package com.example.rdf;

import org.springframework.boot.SpringApplication;
/*import org.apache.jena.query.Query;

import org.apache.jena.atlas.json.JsonArray;
import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;*/
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdfApplication.class, args);
		//sparqlTest();
	}
	
	/*@SuppressWarnings("deprecation")
	static void sparqlTest() {
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("C:\\Users\\Freddy\\Desktop/data.rdf");
		String queryString =
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
			"SELECT * WHERE { " +
			" ?person foaf:name ?x ;" +
			" foaf:age ?y ." +
			"}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		//
		JsonArray array = new JsonArray();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal name = soln.getLiteral("x");
				Literal age = soln.getLiteral("y");
				
				JsonObject json = new JsonObject();
				json.put("nombre", String.valueOf(name));
				json.put("edad", Integer.parseInt(String.valueOf(age)));
				
				array.add(json);
			}
			
			System.out.println(array);
			
		} finally {
			qexec.close();
		}
	}*/

}

