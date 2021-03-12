package dados;

public class Mulher extends Pessoa {
	private Character gestante;
	
	public Mulher(StringBuilder nome, Character sit_saude, Character gestante) {
		super(nome, sit_saude);
		setGestante(gestante);
		
	}
	
	public Character getGestante() {
		return gestante;
	}
	
	public void setGestante(Character gestante) {
		this.gestante = gestante;
	}
}
