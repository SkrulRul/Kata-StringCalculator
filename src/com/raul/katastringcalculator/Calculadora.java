package com.raul.katastringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculadora {
 
	private String operando;
	private String[] numerosString;
	private List<Integer> listaNumerosPositivos = new ArrayList<>();
	private List<Integer> listaNumerosNegativos = new ArrayList<>();
	private String delimitador;
		
	public int calcula (String operando) throws NegativeNumberNotAllowedException {
		if (operando.equals("")) return 0;
		
		this.operando = operando;
		
		return extraerDelimitador()
				.remplazarSaltosPorDelimitador()
				.separarPorDelimitador()
				.convierirToIntegersSepararPorSigno()
				.comprobarSiHayNegativos()
				.sumarIntegers();
	}
		
	private Calculadora remplazarSaltosPorDelimitador (){
		this.operando = this.operando.replaceAll("\\n", this.delimitador);
		return this;
	}
	
	private Calculadora separarPorDelimitador(){
		this.numerosString = this.operando.split(this.delimitador);
		return this;
	}
	
	private Calculadora extraerDelimitador(){
		if(operando.startsWith("//")){
			this.delimitador = this.operando.substring(2,3);
			this.operando = this.operando.substring(4);
		}
		else this.delimitador = ",";
		
		return this;
	}
	
	private int sumarIntegers(){
		int resultado = 0;
			
		for(Integer numero : this.listaNumerosPositivos)
			resultado += numero;
		
		return resultado;
	}
		
	private Calculadora convierirToIntegersSepararPorSigno(){
		int numero=0;
		
		for(String numeroString : this.numerosString){
			numero = Integer.parseInt(numeroString);
			if (numero >= 0 && numero <=1000)
				this.listaNumerosPositivos.add(numero);
			if (numero < 0)
				this.listaNumerosNegativos.add(numero);
		}
		return this;
	}
	
	private Calculadora comprobarSiHayNegativos() throws NegativeNumberNotAllowedException{
		String mensajeError = "negatives not allowed: ";
				
		if (!this.listaNumerosNegativos.isEmpty()){ 
			for(int indice = 0; indice < this.listaNumerosNegativos.size(); indice++) {
				mensajeError += this.listaNumerosNegativos.get(indice);
			    if(indice < this.listaNumerosNegativos.size() - 1) mensajeError += this.delimitador;
			}
			throw new NegativeNumberNotAllowedException(mensajeError);
		}
		return this;
	}	
}
