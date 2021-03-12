package dados;

public abstract class Pessoa {
	private StringBuilder nome;
	private Character sit_saude;
	
	public Pessoa(StringBuilder nome, Character sit_saude) {
		setNome(nome);
		setSituacaoSaude(sit_saude);
	}
	
	public StringBuilder getNome() {
		return nome;
	}
	
	public void setNome(StringBuilder nome) {
		this.nome = nome;
	}
	
	public Character getSituacaoSaude() {
		return sit_saude;
	}
	
	public void setSituacaoSaude(char sit_saude) {
		this.sit_saude = sit_saude;
	}
}
