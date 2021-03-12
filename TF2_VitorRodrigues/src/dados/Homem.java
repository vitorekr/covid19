package dados;

public class Homem extends Pessoa {
	private Integer idade;
	
	public Homem(StringBuilder nome, Character sit_saude, Integer idade) {
		super(nome, sit_saude);
		setIdade(idade);
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
