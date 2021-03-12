package saida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import conexao.PessoaDAO;

@SuppressWarnings("serial")
public class TelaGenero extends JFrame {
	JButton masculino = new JButton("MASCULINO");
	JButton feminino = new JButton("FEMININO");
	PessoaDAO pessoaDAO = new PessoaDAO();
	
	public TelaGenero() {
		super("Sexo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 150, 314, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("MASCULINO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastrarHomens();
				dispose();
			}
		});
		btnNewButton.setBounds(84, 68, 119, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("FEMININO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastrarMulheres();
				dispose();
			}
		});
		btnNewButton_1.setBounds(84, 127, 119, 32);
		contentPane.add(btnNewButton_1);
		
		JButton cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_button.setBounds(84, 190, 119, 32);
		contentPane.add(cancel_button);
		
		setVisible(true);
	}
}
