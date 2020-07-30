package com.example.rdf.entity;

public class Results11 {
	String pais;
	int casos_recuperados;
	public Results11(String pais, int casos_recuperados) {
		super();
		this.pais = pais;
		this.casos_recuperados = casos_recuperados;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getCasos_recuperados() {
		return casos_recuperados;
	}
	public void setCasos_recuperados(int casos_recuperados) {
		this.casos_recuperados = casos_recuperados;
	}
	
	
}
