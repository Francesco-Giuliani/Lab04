package it.polito.tdp.lab04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import it.polito.tdp.lab04.DAO.ConnectDB;

public class TestModel {

	public static void main(String[] args) throws SQLException {
		
		Model model = new Model();
		
		
		
		
		
		/*StringBuilder sb = new StringBuilder();
		
		System.out.println("Corsi: ");
		List<Corso> listaCorsi = new LinkedList<>(model.getAllCorsi());
		for(Corso c: listaCorsi) 
			sb.append(c+"\n");
		System.out.println(sb);

		sb.delete(0, sb.length());
		System.out.println("Studenti: ");
		List<Studente> listaStudenti = new LinkedList<>(model.getAllStudenti());
		for(Studente s : listaStudenti)
			sb.append(s.getMatricola()+" "+s.getCognome()+"\n");
		System.out.println(sb.toString());
		
		for(int i=0; i<listaCorsi.size();i++ ) {
			sb.delete(0, sb.length());
			System.out.println("Studenti del corso "+listaCorsi.get(i).getCodiceInsegnamento()+": ");
			List<Studente> listaStudentiDelCorso = new LinkedList<>(model.getIscrittiDelCorso(listaCorsi.get(i)));
			for(Studente s : listaStudentiDelCorso )
				sb.append(s.getMatricola()+" "+s.getCognome()+"\n");
			System.out.println(sb.toString());
		}*/
		
//		System.out.println("test get studente by matricola");
//		System.out.println(model.getStudenteByMatricola(146101).toString());
		List<Corso> corsiStudente = new ArrayList<>(model.getAllCorsiStudente(146101));
		for(Corso c: corsiStudente)
			System.out.println(c.toString()+"\n");		
		
		Corso selectedCorso = null;
		Studente selStudente = model.getStudenteByMatricola(146101);
		if(!corsiStudente.isEmpty())
			selectedCorso = corsiStudente.get(0);
		else
			System.out.println("nessun corso per lo studente");
		if(selStudente.isIscrittoAlCorso(selectedCorso))
			System.out.println("Iscritto");
		else
			System.out.println(" NON Iscritto");
		
		
		Studente fg = model.getStudenteByMatricola(227103);
		model.registerStudentToCourse(fg, selectedCorso);
		
	}

}
