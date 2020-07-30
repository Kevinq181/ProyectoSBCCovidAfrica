package com.example.rdf.entity;

public class Results7 {
	String pais;
	String codigo;
	double latitud;
	double longitud;
	int poblacion;
	public Results7(String pais, String codigo, double latitud, double longitud, int poblacion) {
		super();
		this.pais = pais;
		this.codigo = codigo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.poblacion = poblacion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public int getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}
	
}
