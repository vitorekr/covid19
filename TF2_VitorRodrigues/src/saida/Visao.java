package saida;

import javax.swing.JOptionPane;

public class Visao {
	
	public static void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, null, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void mostrarErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, null, JOptionPane.ERROR_MESSAGE);
	}
	
}
