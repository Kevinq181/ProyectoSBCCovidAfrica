package com.example.rdf.entity;

public class Results5 {
	String pais;
	double latitud;
	double longitud;
	int poblacion;
	String fecha;
	int cantidad_confirmados;
	public Results5(String pais, double latitud, double longitud, int poblacion, String fecha,
			int cantidad_confirmados) {
		super();
		this.pais = pais;
		this.latitud = latitud;
		this.longitud = longitud;
		this.poblacion = poblacion;
		this.fecha = fecha;
		this.cantidad_confirmados = cantidad_confirmados;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCantidad_confirmados() {
		return cantidad_confirmados;
	}
	public void setCantidad_confirmados(int cantidad_confirmados) {
		this.cantidad_confirmados = cantidad_confirmados;
	}
	
}
