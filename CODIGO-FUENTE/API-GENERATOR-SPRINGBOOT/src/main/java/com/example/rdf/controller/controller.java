package com.example.rdf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rdf.entity.*;
import com.example.rdf.querys.Querys_sparql;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/query")
public class controller {
	
	@Autowired
	private Querys_sparql service;
	
	
	@GetMapping(value="/query1")
	public List<Results1> getConsulta1(){
		return service.consulta1();
	}
	
	@GetMapping(value="/query2")
	public List<Results2> getConsulta2(){
		return service.consulta2();
	}
	
	@GetMapping(value="/query3")
	public List<Results3> getConsulta3(){
		return service.consulta3();
	}
	
	@GetMapping(value="/query4")
	public List<Results4> getConsulta4(){
		return service.consulta4();
	}
	
	@GetMapping(value="/query5")
	public List<Results5> getConsulta5(){
		return service.consulta5();
	}
	
	@GetMapping(value="/confirmados")
	public List<Results6> getConsulta6(){
		return service.consulta6();
	}
	
	@GetMapping(value="/paises")
	public List<Results7> getConsulta7(){
		return service.consulta7();
	}
	
	@GetMapping(value="/recuperados")
	public List<Results8> getConsulta8(){
		return service.consulta8();
	}
	
	@GetMapping(value="/fallecidos")
	public List<Results9> getConsulta9(){
		return service.consulta9();
	}
	
	@GetMapping(value="/confirmados_pais")
	public List<Results10> getConsulta10(){
		return service.consulta10();
	}
	
	@GetMapping(value="/confirmados/{pais}")
	public List<Results10> getConsulta11(@PathVariable("pais") String pais){
		return service.consulta11(pais);
	}
	
	@GetMapping(value="/recuperados_pais")
	public List<Results11> getConsulta12(){
		return service.consulta12();
	}
	
	@GetMapping(value="/recuperados/{pais}")
	public List<Results11> getConsulta13(@PathVariable("pais") String pais){
		return service.consulta13(pais);
	}
	
	@GetMapping(value="/fallecidos_pais")
	public List<Results12> getConsulta14(){
		return service.consulta14();
	}
	
	@GetMapping(value="/fallecidos/{pais}")
	public List<Results12> getConsulta15(@PathVariable("pais") String pais){
		return service.consulta15(pais);
	}
	
	@GetMapping(value="/confirmados_tiempo/{nombre}")
	public List<Resultsx> getConsulta16(@PathVariable("nombre") String pais){
		return service.consultax(pais);
	}
	
	@GetMapping(value="/recuperados_tiempo/{nombre}")
	public List<Resultsx2> getConsulta17(@PathVariable("nombre") String pais){
		return service.consultax2(pais);
	}
	
	@GetMapping(value="/fallecidos_tiempo/{nombre}")
	public List<Resultsx3> getConsulta18(@PathVariable("nombre") String pais){
		return service.consultax3(pais);
	}
	
	@GetMapping(value="/travelLocation")
	public List<Resultsx4> getConsulta19(){
		return service.consultax4();
	}
}
