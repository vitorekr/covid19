package saida;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import conexao.PessoaDAO;
import validacao.Validacao;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	JButton button1 = new JButton("Novo");
	JButton button2 = new JButton("Lista");
	JButton button3 = new JButton("Mostra");
	JButton button4 = new JButton("Pesquisa");
	JButton button5 = new JButton("Sair");
	JTextField textField_id = new JTextField();
	
	public TelaPrincipal(){
		super("COVID-19");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 150, 400, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaGenero();
			}
		});
		btnNewButton.setBounds(84, 42, 119, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lista");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaLista();
			}
		});
		btnNewButton_1.setBounds(84, 99, 119, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Mostra");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaDAO dao = new PessoaDAO();
				if(Validacao.validaId(textField_id.getText())) {
					dao.mostraPessoa(Integer.parseInt(textField_id.getText()));
				}
			}
		});
		btnNewButton_2.setBounds(190, 152, 119, 32);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Sair");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaDAO dao = new PessoaDAO();
				dao.quantidade();
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(84, 261, 119, 32);
		contentPane.add(btnNewButton_4);
		setVisible(true);
	
		JLabel id = new JLabel("ID:");
		id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		id.setBounds(84, 165, 365, 10);
		contentPane.add(id);
		
		textField_id = new JTextField();
		textField_id.setBounds(110, 160, 60, 20);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
	}
}
