package com.example.rdf.entity;

public class ResultsCasosCont {
	int casos_confirmados;
	int casos_recuperados;
	public ResultsCasosCont(int casos_confirmados, int casos_recuperados) {
		super();
		this.casos_confirmados = casos_confirmados;
		this.casos_recuperados = casos_recuperados;
	}
	public int getCasos_confirmados() {
		return casos_confirmados;
	}
	public void setCasos_confirmados(int casos_confirmados) {
		this.casos_confirmados = casos_confirmados;
	}
	public int getCasos_recuperados() {
		return casos_recuperados;
	}
	public void setCasos_recuperados(int casos_recuperados) {
		this.casos_recuperados = casos_recuperados;
	}
	
	
}
