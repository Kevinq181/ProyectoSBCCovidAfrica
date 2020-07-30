package com.example.rdf.entity;

public class Results10 {
	String pais;
	int casos_confirmados;
	public Results10(String pais, int casos_confirmados) {
		super();
		this.pais = pais;
		this.casos_confirmados = casos_confirmados;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getCasos_confirmados() {
		return casos_confirmados;
	}
	public void setCasos_confirmados(int casos_confirmados) {
		this.casos_confirmados = casos_confirmados;
	}

}
