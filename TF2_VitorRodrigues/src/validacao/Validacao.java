package validacao;

import javax.swing.JRadioButton;
import saida.Visao;

public class Validacao {
	
	public static boolean validaNome(String nome) {
		if(isStringVazia(nome)) {
			return false;
		}
		
		if(isComNumeros(nome)) {
			return false;
		 }
		
		if(nome.length() < 3) {
			Visao.mostrarErro("O nome não pode ter menos de 3 caracteres");
			return false;		
		}
		
		return true;
	}
	
	public static boolean validaSituacaoSaude(JRadioButton t, JRadioButton f, JRadioButton c, JRadioButton s) {
			if(t.isSelected() || f.isSelected() || c.isSelected() || s.isSelected()) {
				return true;
			}else {
				Visao.mostrarErro("Selecione alguma opção de Situação de Saúde");
				return false;
			}
		
	}
	
	public static boolean validaGestacao(JRadioButton s, JRadioButton n, JRadioButton t) {
		if(s.isSelected() || n.isSelected() || t.isSelected()) {
			return true;
		}else {
			Visao.mostrarErro("Selecione alguma opção de gestação");
			return false;
		}
	
	}
	
	public static boolean validaIdade(String idade, int MINIMO, int MAXIMO) {
		if(isStringVazia(idade) || isComLetra(idade)) {
			return false;
		}else {
			try {	
				Integer.parseInt(idade);
			}catch(NumberFormatException e) {
				Visao.mostrarErro("ERRO, insira uma idade válida entre 0 e 150");
				return false;
			}
		}
		
		if(Integer.parseInt(idade) < MINIMO || Integer.parseInt(idade) > MAXIMO) {
			Visao.mostrarErro("Insira uma idade entre 0 e 150");
			return false;
		}
		
		return true;
	}
		
	public static boolean validaId(String id) {
		if(isStringVazia(id) || isComLetra(id)) {
			return false;
		}else {
			try {	
				Integer.parseInt(id);
			}catch(NumberFormatException e) {
				Visao.mostrarErro("ERRO, insira um IDENTIFICADOR válido");
				return false;
			}
		}
		
		if(Integer.parseInt(id) < 100) {
			Visao.mostrarErro("Insira um ID maior que 100");
			return false;
		}
		
		return true;
	}
	
	private static boolean isComLetra(String string) {
		for (char c : string.toCharArray()) {
			if (Character.isLetter(c)) {
				Visao.mostrarErro("Não pode conter letras");
				return true;
			}
		}
		return false;
	}
	
	static boolean isComNumeros(String string) {
		for (char c : string.toString().toCharArray()) {
			if (Character.isDigit(c)) {
				Visao.mostrarErro("Não pode conter números");
				return true;
			}
		}
		return false;
	}
	
	private static boolean isStringVazia(String string) {
		if (string == null || (string = string.trim()).isEmpty()) {
			Visao.mostrarErro("Não pode haver campos vazios");
			return true;
		}
		return false;
	}
	
}
