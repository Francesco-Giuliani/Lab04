package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.*;
import java.util.*;

public class Model {

	private List<Studente> listaStudenti;
	private List<Corso> listaCorsi;
	//private Map<String, Integer> mappaIscrizioni;
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	private Studente selectedStudent = null;
	private Corso selectedCourse = null;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.listaStudenti = new ArrayList<>();
		this.listaCorsi = new ArrayList<>();
		//this.mappaIscrizioni = new HashMap<>();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getAllCorsi(){
		return this.listaCorsi = new ArrayList<>(corsoDAO.getTuttiICorsi());
	}
	public List<Studente> getAllStudenti(){
		return this.listaStudenti = new ArrayList<>(this.studenteDAO.getTuttiGliStudenti());
	}

	public List<Studente> getIscrittiDelCorso(Corso c) {
		List<Studente> risultato = new ArrayList<>(this.corsoDAO.getStudentiIscrittiAlCorso(c));
		
		return risultato;
	}
	
	public String getAllCorsiToString() {
		StringBuilder sb = new StringBuilder();
		for(Corso c: this.getAllCorsi())
			sb.append(c.toString()+"\n");
	
		return sb.toString();
	}
	public String getAllStudentiToString() {
		StringBuilder sb = new StringBuilder();
		for(Studente c: this.getAllStudenti())
			sb.append(c.toString()+"\n");
	
		return sb.toString();
	}
	public Studente getStudenteByMatricola(int matricola) {
		this.selectedStudent = this.studenteDAO.getStudenteByMatricola(matricola);
		return this.selectedStudent;
	}

	public List<Corso> getAllCorsiStudente(int matricola) {
		return this.corsoDAO.getTuttiCorsiStudente(matricola);
	}

	public boolean registerStudentToCourse(Studente selectedStudent2, Corso selectedCourse2) {
		this.corsoDAO.inscriviStudenteACorso(selectedStudent2, selectedCourse2);
		if(selectedStudent2.isIscrittoAlCorso(selectedCourse2))
			return true;
		else
			return false;
	}
	
	
}
