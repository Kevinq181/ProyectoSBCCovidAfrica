package com.example.rdf.entity;

public class Resultsx3 {
	String nombre;
	int casos_fallecidos;
	String fecha;
	public Resultsx3(String nombre, int casos_fallecidos, String fecha) {
		super();
		this.nombre = nombre;
		this.casos_fallecidos = casos_fallecidos;
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCasos_fallecidos() {
		return casos_fallecidos;
	}
	public void setCasos_fallecidos(int casos_fallecidos) {
		this.casos_fallecidos = casos_fallecidos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}	
