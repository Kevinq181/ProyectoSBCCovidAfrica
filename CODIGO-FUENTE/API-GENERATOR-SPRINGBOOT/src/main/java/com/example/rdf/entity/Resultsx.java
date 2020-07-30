package com.example.rdf.entity;

public class Resultsx {
	String nombre;
	int casos_confirmados;
	String fecha;
	
	public Resultsx(String nombre, int casos_confirmados, String fecha) {
		super();
		this.nombre = nombre;
		this.casos_confirmados = casos_confirmados;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCasos_confirmados() {
		return casos_confirmados;
	}

	public void setCasos_confirmados(int casos_confirmados) {
		this.casos_confirmados = casos_confirmados;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
		
	
}
