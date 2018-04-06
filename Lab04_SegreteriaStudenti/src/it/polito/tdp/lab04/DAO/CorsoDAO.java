package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/**
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}
			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/**
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {

	}

	/**
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {

		final String sql = "SELECT s.matricola, cognome, nome, CDS "
				+ "FROM iscrizione as i, studente as s "
				+ "WHERE i.matricola = s.matricola AND i.codins = ?";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodiceInsegnamento());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome= rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				studenti.add(new Studente(matricola, cognome, nome, cds));
			}
			conn.close();
			return studenti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
}
	

	/**
	 * Dato uno studente e un corso, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES (?, ?)";
		String verificaSql = "SELECT matricola, codins FROM iscrizione WHERE matricola = ? AND codins = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodiceInsegnamento());
			
			st.executeUpdate();
			
			
			//CONTROLLO
//			st = conn.prepareStatement(verificaSql);
//			st.setInt(1, studente.getMatricola());
//			st.setString(2, corso.getCodiceInsegnamento());
//			
//			ResultSet rs = st.executeQuery();
//			rs.next();
//			int matricola = rs.getInt("matricola");
//			String codins = rs.getString("codins");
			
			conn.close();
			
//			if(matricola == studente.getMatricola() && codins.compareTo(corso.getCodiceInsegnamento())==0)
//				return true;
			
			
			return false;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public List<Corso> getTuttiCorsiStudente(int matricola) {
		
		final String sql = "select c.codins, c.crediti, c.nome, c.pd "
				+ "from corso as c, iscrizione as i "
				+ "where i.codins = c.codins and i.matricola = ?";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while(rs.next()) {

				String codins = rs.getString("c.codins");
				int numeroCrediti = rs.getInt("c.crediti");
				String nome = rs.getString("c.nome");
				int periodoDidattico = rs.getInt("c.pd");
				
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}
			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
