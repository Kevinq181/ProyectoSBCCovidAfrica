package com.example.rdf.entity;

public class Results1 {
	String pais;
	String fecha;
	int casos_confirmados;
	public Results1(String pais, String fecha, int casos_confirmados) {
		super();
		this.casos_confirmados = casos_confirmados;
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
	
	public int getCasos_confirmados() {
		return casos_confirmados;
	}
	
	public void setCasos_confirmados(int casos_confirmados) {
		this.casos_confirmados = casos_confirmados;
	}

}
