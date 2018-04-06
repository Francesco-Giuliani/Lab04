package it.polito.tdp.lab04.controller;
import it.polito.tdp.lab04.controller.exceptions.*;
	import java.util.*;
	import it.polito.tdp.lab04.model.*;
	import java.net.URL;
	import java.util.ResourceBundle;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;

	public class SegreteriaStudentiController {
		
		private Model model;
		private Corso campoVuoto = new Corso("--codInsVuoto--", 0, "--nomeInsVuoto--", 0);
		private Studente selectedStudent = null;
		private Corso selectedCourse = null;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<Corso> cmbCorsi;

	    @FXML
	    private Button btnCercaIscritti;

	    @FXML
	    private TextField txtMatricola;

	    @FXML
	    private Button btnCompletamentoAutmoatico;

	    @FXML
	    private TextField txtNomeStudente;

	    @FXML
	    private TextField txtCognomeStudente;

	    @FXML
	    private Button btnCercaCorsi;

	    @FXML
	    private Button btnIscrivi;

	    @FXML
	    private TextArea txtArea;

	    @FXML
	    private Button btnReset;

	    @FXML
	    void doCercaCorsiStudente(ActionEvent event) {
	    		Studente selectedStudent;
	    		Corso selectedCourse;
	    	try {
		    	int matricola = Integer.parseInt(this.txtMatricola.getText());
		    	
		    	if((selectedStudent = this.model.getStudenteByMatricola(matricola))==null) {
		    		this.txtArea.appendText("Nessuno studente trovato per la matricola fornita.\n");
		    		return;
		    	}
	    	
		    	selectedCourse = this.cmbCorsi.getValue();
		    	if(selectedCourse!=null && selectedCourse!=this.campoVuoto) {
		    		if(selectedStudent.isIscrittoAlCorso(selectedCourse))
		    			this.txtArea.appendText("Studente già iscritto al corso.\n");
		    		else
		    			this.txtArea.appendText("Studente non iscritto al corso. Si desisdera iscriverlo?\n");
		    		return;
		    		
		    	}else {
		    	
			    	List<Corso> corsiStudente = new ArrayList<>(this.model.getAllCorsiStudente(matricola));
			    	
			    	if(corsiStudente.isEmpty()) {
			    		this.txtArea.appendText("Nessun corso trovato per lo studente fornito.\n");
			    		return;
			    	}
			    	for(Corso c : corsiStudente)
			    		this.txtArea.appendText(c.toString()+"\n");
			    }
	    	}catch(NumberFormatException nfe) {
	    		this.txtArea.appendText("Matricola non valida. Fornire un intero come matricola.\n");
	    		return;
	    	}
	   	}

	    @FXML
	    void doCercaIscrittiCorso(ActionEvent event) {
	    	//TODO inserisci controllo che corso sia selezionato
	    	List<Studente> studenti;
	    	Corso c = this.cmbCorsi.getValue();
	    	
	    	try {
		    	if(c == null)
		    		throw new NoSelectedCorsoException();
		    	else if( c.equals(this.campoVuoto)) {
		    		this.txtArea.appendText(" E' stato selezionato in campo vuoto. Elenco di tutti gli studenti:\n");
		    		this.txtArea.appendText(model.getAllStudentiToString()+"\n");
		    	}	
		    	else{
		    		studenti = this.model.getIscrittiDelCorso(c);
		    		StringBuilder sb = new StringBuilder();
			    	for(Studente s : studenti)
			    		sb.append(s.toString()+"\n");
			 
			    	this.txtArea.appendText(sb.toString());	
			    	if(studenti.size()==0)
			    		this.txtArea.appendText("Nessuno studente trovato per il corso "+c.getCodiceInsegnamento()+".\n");
		    	}
	    	
	    	}catch(NoSelectedCorsoException nsce) {
	    		this.txtArea.appendText("Nessun Corso selezionato.\n");
	    		return;
	    	}
	    }

	    @FXML
	    void doCompletamentoAutmatico(ActionEvent event) {
	    	
	    	this.txtCognomeStudente.clear();;
    		this.txtNomeStudente.clear();	    	
	    	
    		try {
		    	int matricola = Integer.parseInt(this.txtMatricola.getText());
		    	Studente s = this.model.getStudenteByMatricola(matricola);
		    	
		    	if(s==null) {
		    		this.txtArea.appendText("Nessuno studente trovato per la matricola fornita.\n"
		    				+ "Impossibile completare i campi richiesti.\n");
		    		return;
		    	}
		    	else {
		    		this.txtCognomeStudente.setText(s.getCognome());
		    		this.txtNomeStudente.setText(s.getNome());
		    	}
		    		
	    	}catch(NumberFormatException nfe) {
	    		this.txtArea.appendText("Inserire un numero intero come matricola!\n");
	    		return;
	    	}
	    }

	    @FXML
	    void doIscrivi(ActionEvent event) {
	    	try {
		    	int matricola = Integer.parseInt(this.txtMatricola.getText());
		    	
		    	Studente selectedStudent = this.model.getStudenteByMatricola(matricola);
	    		Corso selectedCourse = this.cmbCorsi.getValue();
	    		
	    		if(selectedCourse == null || selectedCourse == this.campoVuoto)
	    			throw new NoSelectedCorsoException(); 
	    		if(selectedStudent == null)
	    			throw new NoSelectedStudentException();
	    		
	    		if(selectedStudent.isIscrittoAlCorso(selectedCourse)) { //ANDREBBE NEL METODO DEL MODEL
	    			this.txtArea.appendText("Studente già iscritto al corso. Selezionare uno studente che non sia già iscritto.\n");
	    			return;
	    		}
	    		
	    		if(this.model.registerStudentToCourse(selectedStudent, selectedCourse)==true)
	    			this.txtArea.appendText("Studente iscritto correttamente al corso\n.");
	    		else 
	    			this.txtArea.appendText("Studente non iscritto al corso. Riprovare.");
	    		
    		}catch(NumberFormatException nfe) {
    			this.txtArea.appendText("Inserire un numero intero come matricola!\n");
    			return;
    		}catch(NoSelectedCorsoException nsce) {
    			this.txtArea.appendText("Nessun corso selezionato. Selezionare un corso valido.\n");
    			return;
    		}catch(NoSelectedStudentException nsse) {
    			this.txtArea.appendText("Nessuno studente selezionato. Selezionare uno studente valido.\n");
    			return;
    		}
	    }

	    @FXML
	    void doReset(ActionEvent event) {
	    	this.txtArea.clear();
	    	this.txtCognomeStudente.clear();
	    	this.txtNomeStudente.clear();
	    	this.txtMatricola.clear();
	    }

	    @FXML
	    void doSelezionaCorso(ActionEvent event) {
	    	this.selectedCourse = this.cmbCorsi.getValue();
	    	this.txtArea.appendText("Corso selezionato: "+this.selectedCourse+"\n");
	    }

	    @FXML
	    void initialize() {
	        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnCompletamentoAutmoatico != null : "fx:id=\"btnCompletamentoAutmoatico\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtNomeStudente != null : "fx:id=\"txtNomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtCognomeStudente != null : "fx:id=\"txtCognomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

	    }

		public Model getModel() {
			return model;
		}
		public void setModel(Model mod) {
			this.model = mod;
			List<Corso> listaCorsi = new LinkedList<>(mod.getAllCorsi());
			listaCorsi.add(0, this.campoVuoto);
			this.cmbCorsi.getItems().setAll(listaCorsi);
		}
	    
	    
	    
	}
