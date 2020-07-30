package com.example.rdf.entity;

public class Results3 {
	String pais;
	String fecha;
	int casos_fallecidos;
	
	public Results3(String pais, String fecha, int casos_fallecidos) {
		super();
		this.casos_fallecidos = casos_fallecidos;
		this.pais = pais;
		this.fecha = fecha;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getCasos_fallecidos() {
		return casos_fallecidos;
	}
	public void setCasos_fallecidos(int casos_fallecidos) {
		this.casos_fallecidos = casos_fallecidos;
	}

	
}
