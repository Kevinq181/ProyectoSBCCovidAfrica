package com.example.rdf.entity;

public class Results12 {
	String pais;
	int casos_fallecidos;
	public Results12(String pais, int casos_fallecidos) {
		super();
		this.pais = pais;
		this.casos_fallecidos = casos_fallecidos;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getCasos_fallecidos() {
		return casos_fallecidos;
	}
	public void setCasos_fallecidos(int casos_fallecidos) {
		this.casos_fallecidos = casos_fallecidos;
	}

}
