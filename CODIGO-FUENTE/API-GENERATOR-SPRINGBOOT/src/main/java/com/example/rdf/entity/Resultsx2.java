package com.example.rdf.entity;

public class Resultsx2 {
	String nombre;
	int casos_recuperados;
	String fecha;
	public Resultsx2(String nombre, int casos_recuperados, String fecha) {
		super();
		this.nombre = nombre;
		this.casos_recuperados = casos_recuperados;
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCasos_recuperados() {
		return casos_recuperados;
	}
	public void setCasos_recuperados(int casos_recuperados) {
		this.casos_recuperados = casos_recuperados;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
