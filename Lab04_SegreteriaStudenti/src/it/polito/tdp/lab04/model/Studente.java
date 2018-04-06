package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.*;
import java.util.List;

public class Studente {
	private int matricola;
	private String cognome;
	private String nome;
	private String corsoDiStudi;
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	public Studente(int matricola, String cognome, String nome, String corsoDiStudi) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.corsoDiStudi = corsoDiStudi;
		this.studenteDAO = new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
	}
	public int getMatricola() {
		return matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNome() {
		return nome;
	}
	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return matricola + "\t" + cognome + "\t" + nome + "\t" + corsoDiStudi;
	}
	public boolean isIscrittoAlCorso(Corso selectedCourse) {
		List<Corso> corsiStudente = this.getAllCorsiStudente(this.matricola);
		if(corsiStudente.contains(selectedCourse))
			return true;
		return false;
	}
	public List<Corso> getAllCorsiStudente(int matricola) {
		return this.corsoDAO.getTuttiCorsiStudente(matricola);
	}
	
	
}
