package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Studente;


public class StudenteDAO {
	
	List<Studente> listaStudenti = new ArrayList<>();
	
	
	public List<Studente> getTuttiGliStudenti() {
		
		this.listaStudenti.clear();
		String sql = "SELECT * FROM studente";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet resSet = st.executeQuery();
			
			while(resSet.next()) {
				Studente s = new Studente(resSet.getInt("matricola"), 
											resSet.getString("cognome"),
											resSet.getString("nome"),
											resSet.getString("CDS"));
				this.listaStudenti.add(s);
			}
			
			conn.close();
			
			return this.listaStudenti;
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException("Errore in connessione al database");
		}
	}
	/**
	 * Data la matricola ritorna lo studente ad essa associato
	 * @param matricola
	 * @return Studente (o null se nessuno studente è stato trovato con la matricola pssata
	 */
	public Studente getStudenteByMatricola(int matricola) {
		Studente res = null;
		if(this.listaStudenti.isEmpty()) {
			String sql = "SELECT matricola, cognome, nome, CDS FROM studente WHERE matricola = ?"; 
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				
				ResultSet resSet = st.executeQuery();
				
				
				while(resSet.next())
					res = new Studente(resSet.getInt("matricola"), 
												resSet.getString("cognome"),
												resSet.getString("nome"),
												resSet.getString("CDS"));
				
				
				conn.close();
				
				return res;
				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
				throw new RuntimeException("Errore in connessione al database");
			}
		}
		else {
			for(Studente s: this.listaStudenti)
				if(s.getMatricola() == matricola)
					return res=s;
			return null;
		}
	}

}
