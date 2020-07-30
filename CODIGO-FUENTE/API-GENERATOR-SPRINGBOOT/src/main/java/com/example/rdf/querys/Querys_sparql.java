package com.example.rdf.querys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rdf.entity.*;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import org.apache.jena.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

@Service
public class Querys_sparql {
	@Value("${spring.datasource.url}")
	private String endpointUrl;

	public List<Results1> consulta1() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/> "
				+ "PREFIX gn:<http://www.geonames.org/ontology#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT ?casos ?nombre ?fecha FROM <http://localhost:8890/covidAfrica> WHERE {?var rdf:type newOnto:Confirmed_Cases ;"
				+ " newOnto:totalQuantity ?casos ;" + " gn:locatedIn ?pais;" + " schema:observationDate ?fecha."
				+ " ?pais dbo:name ?nombre." + " FILTER (?fecha = \"2020-06-15\")"
				+ "}ORDER BY DESC (xsd:integer(?casos))";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results1> lista = new ArrayList<Results1>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode casos_confirmados = soln.get("casos");
				RDFNode nombre = soln.get("nombre");
				RDFNode fecha = soln.get("fecha");
				Results1 objResults1 = new Results1(nombre.toString(), fecha.toString(), Integer.parseInt(casos_confirmados.toString()));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	 
	public List<Results2> consulta2() {
		
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX sio:<http://semanticscience.org/resource/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + "SELECT (sum(if($gender=\"male\",1,0)) as ?hombres)"
				+ "(sum(if($gender=\"female\",1,0)) as ?mujeres)" + "{"
				+ "	{SELECT DISTINCT ?person $gender FROM <http://localhost:8890/covidAfrica> WHERE {" + "		?person rdf:type sio:Patient;"
				+ "		foaf:gender $gender" + "	}GROUP BY ?person $gender }" + "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results2> lista = new ArrayList<Results2>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode hombres = soln.get("hombres");
				RDFNode mujeres = soln.get("mujeres");
				String hombresR = hombres.toString().replaceAll("[\\^]", "-");
				String mujeresR = mujeres.toString().replaceAll("[\\^]", "-");
				String[] parts = hombresR.split("-");
				String[] parts1 = mujeresR.split("-");
				Results2 objResults1 = new Results2(parts[0], parts1[0]);
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results3> consulta3() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT ?fecha ?nombre ?fallecidos FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Deaths_Cases ;" + " newOnto:totalQuantity ?fallecidos ;"
				+ " gn:locatedIn ?pais;" + " schema:observationDate ?fecha." + " ?pais dbo:name ?nombre."
				+ " FILTER (?fecha = \"2020-06-15\")" + "}ORDER BY DESC (xsd:integer(?fallecidos))";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results3> lista = new ArrayList<Results3>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode fecha = soln.get("fecha");
				RDFNode cantidad = soln.get("fallecidos");
				RDFNode nombre = soln.get("nombre");
				Results3 objResults1 = new Results3(nombre.toString(), fecha.toString(), Integer.parseInt(cantidad.toString()));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results4> consulta4() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "SELECT ?case ?location FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?case rdf:type newOnto:CaseCovid;" + " newOnto:hasData ?paciente."
				+ " ?paciente newOnto:hasData ?informacion."
				+ " ?informacion newOnto:travel_history_location ?location." + "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results4> lista = new ArrayList<Results4>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode cases = soln.get("case");
				RDFNode location = soln.get("location");
				String[] parts = cases.toString().split("/");
				Results4 objResults1 = new Results4(parts[5], location.toString());
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results5> consulta5() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT ?nombre ?latitud ?longitud ?cantidad ?poblacion ?fecha FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Confirmed_Cases ;" + " newOnto:totalQuantity ?cantidad ;"
				+ " gn:locatedIn ?pais;" + " schema:observationDate ?fecha." + " ?pais dbo:name ?nombre;"
				+ " schema:latitude ?latitud;" + " schema:longitude ?longitud;" + " dbo:populationTotal ?poblacion."
				+ " FILTER (?fecha = \"2020-06-15\")" + "}ORDER BY DESC (xsd:integer(?cantidad))";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results5> lista = new ArrayList<Results5>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				 QuerySolution soln = results.nextSolution();
	                RDFNode nombre = soln.get("nombre");
	                RDFNode latitud = soln.get("latitud");
	                RDFNode longitud = soln.get("longitud");
	                RDFNode cantidad = soln.get("cantidad");
	                RDFNode poblacion = soln.get("poblacion");
	                RDFNode fecha = soln.get("fecha");
					Results5 objResults1 = new Results5(nombre.toString(), Double.parseDouble(latitud.toString()),Double.parseDouble(longitud.toString()), Integer.parseInt(poblacion.toString()),fecha.toString(), Integer.parseInt(cantidad.toString()) );
					lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	
	@SuppressWarnings("deprecation")
	public List<Results6> consulta6() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Confirmed_Cases ;" + " newOnto:quantity ?confirmados."
				/*+ " schema:observationDate ?fecha."*/
				/*+ " FILTER (?fecha = \"2020-06-15\")"*/ + "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results6> lista = new ArrayList<Results6>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				//RDFNode fecha = soln.get("fecha");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results6 objResults1 = new Results6(Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results7> consulta7() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "SELECT ?nombre ?codigo ?latitude ?longitude ?poblacion FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type dbo:Country ;" + " dbo:name ?nombre ;"
				+ " gn:countryCode ?codigo;"
				+ " schema:latitude ?latitude;"
				+ " schema:longitude ?longitude;"
				+ " dbo:populationTotal ?poblacion."
				+ "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results7> lista = new ArrayList<Results7>();
		
		try {
			ResultSet results = qexec.execSelect();
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode nombre = soln.get("nombre");
				RDFNode codigo = soln.get("codigo");
                RDFNode latitud = soln.get("latitude");
                RDFNode longitud = soln.get("longitude");
                RDFNode poblacion = soln.get("poblacion");
				Results7 objResults1 = new Results7(nombre.toString(), codigo.toString(), Double.parseDouble(latitud.toString()), Double.parseDouble(longitud.toString()), Integer.parseInt(poblacion.toString()));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results8> consulta8() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Recovered_Cases ;" + " newOnto:quantity ?confirmados."
				/*+ " schema:observationDate ?fecha."*/
				/*+ " FILTER (?fecha = \"2020-06-15\")"*/ + "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results8> lista = new ArrayList<Results8>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				//RDFNode fecha = soln.get("fecha");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results8 objResults1 = new Results8(Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results9> consulta9() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Deaths_Cases ;" + " newOnto:quantity ?confirmados."
				/*+ " schema:observationDate ?fecha."*/
				/*+ " FILTER (?fecha = \"2020-06-15\")"*/ + "}";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results9> lista = new ArrayList<Results9>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				//RDFNode fecha = soln.get("fecha");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results9 objResults1 = new Results9(Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	

	public List<Results10> consulta10() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Confirmed_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre} GROUP BY ?nombre ORDER BY ASC (?nombre) ";
				/*+ " FILTER (?nombre = \" "+ pais +"\")" + "} GROUP BY ?nombre";*/
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results10> lista = new ArrayList<Results10>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results10 objResults1 = new Results10(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results10> consulta11(String pais) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Confirmed_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre"
				+ " FILTER (?nombre = \""+ pais +"\")" + "} GROUP BY ?nombre";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results10> lista = new ArrayList<Results10>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results10 objResults1 = new Results10(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results11> consulta12() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Recovered_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre} GROUP BY ?nombre ORDER BY ASC (?nombre) ";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results11> lista = new ArrayList<Results11>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results11 objResults1 = new Results11(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results11> consulta13(String pais) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Recovered_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre"
				+ " FILTER (?nombre = \""+ pais +"\")" + "} GROUP BY ?nombre";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results11> lista = new ArrayList<Results11>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results11 objResults1 = new Results11(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results12> consulta14() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Deaths_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre} GROUP BY ?nombre ORDER BY ASC (?nombre) ";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results12> lista = new ArrayList<Results12>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results12 objResults1 = new Results12(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	
	public List<Results12> consulta15(String pais) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>" 
				+ "SELECT (SUM(xsd:int(?confirmados)) AS ?total) ?nombre FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Deaths_Cases ;" + " newOnto:quantity ?confirmados;"
				+ " gn:locatedIn ?pais."
				+ " ?pais dbo:name ?nombre"
				+ " FILTER (?nombre = \""+ pais +"\")" + "} GROUP BY ?nombre";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Results12> lista = new ArrayList<Results12>();
		
		try {
			ResultSet results = qexec.execSelect();
			String separador = Pattern.quote("^");
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode paisr = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String total = cantidad.toString();
				String[] valor = total.split(separador);
				Results12 objResults1 = new Results12(paisr.toString(), Integer.parseInt(valor[0]));
				lista.add(objResults1);
			}
			
		} finally {
			qexec.close();
		}
		
		return lista;
	}
	

	public List<Resultsx> consultax(String name) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
				+ "SELECT ?nombre ?cantidad  ?fecha FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Confirmed_Cases ;" + " newOnto:totalQuantity ?cantidad;"
				+ " gn:locatedIn ?pais;" + " schema:observationDate ?fecha." + " ?pais dbo:name ?nombre."
				+ "FILTER (?nombre= \"" + name + "\")" + "} ORDER BY ASC (?fecha)";
			
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Resultsx> lista = new ArrayList<Resultsx>();
		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode nombre = soln.get("nombre");
				RDFNode cantidad = soln.get("cantidad");
				RDFNode fecha = soln.get("fecha");
				Resultsx objResults1 = new Resultsx(nombre.toString(), Integer.parseInt(cantidad.toString()), fecha.toString());
				lista.add(objResults1);
			}

		} finally {
			qexec.close();
		}
		return lista;
	}
	
	public List<Resultsx2> consultax2(String name) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
				+ "SELECT ?nombre ?cantidad  ?fecha FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Recovered_Cases ;" + " newOnto:totalQuantity ?cantidad;"
				+ " gn:locatedIn ?pais;" + " schema:observationDate ?fecha." + " ?pais dbo:name ?nombre."
				+ "FILTER (?nombre= \"" + name + "\")" + "} ORDER BY ASC (?fecha)";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Resultsx2> lista = new ArrayList<Resultsx2>();
		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode nombre = soln.get("nombre");
				RDFNode cantidad = soln.get("cantidad");
				RDFNode fecha = soln.get("fecha");
				Resultsx2 objResults1 = new Resultsx2(nombre.toString(), Integer.parseInt(cantidad.toString()), fecha.toString());
				lista.add(objResults1);
			}

		} finally {
			qexec.close();
		}
		return lista;
	}
	
	public List<Resultsx3> consultax3(String name) {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>"
				+ "PREFIX gn:<http://www.geonames.org/ontology#>" + "PREFIX schema:<http://schema.org/>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>"
				+ "SELECT ?nombre ?cantidad  ?fecha FROM <http://localhost:8890/covidAfrica> WHERE {"
				+ " ?var rdf:type newOnto:Deaths_Cases ;" + " newOnto:totalQuantity ?cantidad;"
				+ " gn:locatedIn ?pais;" + " schema:observationDate ?fecha." + " ?pais dbo:name ?nombre."
				+ "FILTER (?nombre= \"" + name + "\")" + "} ORDER BY ASC (?fecha)";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Resultsx3> lista = new ArrayList<Resultsx3>();
		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode nombre = soln.get("nombre");
				RDFNode cantidad = soln.get("cantidad");
				RDFNode fecha = soln.get("fecha");
				Resultsx3 objResults1 = new Resultsx3(nombre.toString(), Integer.parseInt(cantidad.toString()), fecha.toString());
				lista.add(objResults1);
			}

		} finally {
			qexec.close();
		}
		return lista;
	}
	
	
	public List<Resultsx4> consultax4() {
		VirtGraph set = new VirtGraph(endpointUrl, "dba", "dba");
		String queryString = "PREFIX newOnto:<http://utpl.edu.ec/lod/dataCOVID/ontology/>" + 
				"PREFIX gn:<http://www.geonames.org/ontology#>" + 
				"PREFIX schema:<http://schema.org/>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"PREFIX dbo: <http://dbpedia.org/ontology/>" + 
				"SELECT ?nombre (count(?nombre) AS ?total) FROM <http://localhost:8890/covidAfrica> WHERE {" + 
				"?var rdf:type newOnto:Medical_Information;" + 
				"newOnto:travel_history_location ?nombre." + 
				"}GROUP BY ?nombre" + 
				"";
		Query query = QueryFactory.create(queryString);
		VirtuosoQueryExecution qexec = VirtuosoQueryExecutionFactory.create(query, set);
		List<Resultsx4> lista = new ArrayList<Resultsx4>();
		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				RDFNode nombre = soln.get("nombre");
				RDFNode cantidad = soln.get("total");
				String cantidad1 = cantidad.toString().replaceAll("[\\^]", "-");
				String[] parts = cantidad1.split("-");
				Resultsx4 objResults1 = new Resultsx4(nombre.toString(), Integer.parseInt(parts[0]));
				lista.add(objResults1);
			}

		} finally {
			qexec.close();
		}
		return lista;
	}
	
	
	
}
