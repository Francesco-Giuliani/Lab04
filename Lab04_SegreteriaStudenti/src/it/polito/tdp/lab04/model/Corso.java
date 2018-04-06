package it.polito.tdp.lab04.model;


public class Corso {
	private String codiceInsegnamento;
	private String nomeInsegnamento;
	private int numeroCrediti;
	private int periodo;
	
	public Corso(String codiceInsegnamento, int numeroCrediti, String nomeInsegnamento,  int periodo) {
		super();
		this.codiceInsegnamento = codiceInsegnamento;
		this.nomeInsegnamento = nomeInsegnamento;
		this.numeroCrediti = numeroCrediti;
		this.periodo = periodo;
	}

	public String getCodiceInsegnamento() {
		return codiceInsegnamento;
	}

	public String getNomeInsegnamento() {
		return nomeInsegnamento;
	}

	public int getNumeroCrediti() {
		return numeroCrediti;
	}

	public int getPeriodo() {
		return periodo;
	}

	@Override
	public String toString() {
		return codiceInsegnamento + " " +numeroCrediti
				+ " " + nomeInsegnamento + " " + periodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceInsegnamento == null) ? 0 : codiceInsegnamento.hashCode());
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
		Corso other = (Corso) obj;
		if (codiceInsegnamento == null) {
			if (other.codiceInsegnamento != null)
				return false;
		} else if (!codiceInsegnamento.equals(other.codiceInsegnamento))
			return false;
		return true;
	}
	
	
	
	
}
