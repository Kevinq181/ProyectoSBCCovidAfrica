package com.example.rdf.entity;

public class Resultsx4 {
	String pais;
	int total;

	public Resultsx4(String pais, int total) {
		super();
		this.pais = pais;
		this.total = total;
	}
	public String getNombre() {
		return pais;
	}
	public void setNombre(String nombre) {
		this.pais = nombre;
	}
	public int getCasos_fallecidos() {
		return total;
	}
	public void setCasos_fallecidos(int casos_fallecidos) {
		this.total = casos_fallecidos;
	}
}	
