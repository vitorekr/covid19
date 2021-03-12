package saida;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import conexao.PessoaDAO;
import dados.Homem;
import validacao.Validacao;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastrarHomens extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nome;
	private JTextField textField_idade;

	public TelaCadastrarHomens() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 150, 450, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome Completo:");
		lblNewLabel.setBounds(23, 37, 109, 14);
		contentPane.add(lblNewLabel);

		textField_nome = new JTextField();
		textField_nome.setBounds(133, 34, 301, 20);
		contentPane.add(textField_nome);
		textField_nome.setColumns(10);

		JRadioButton t = new JRadioButton("T");
		t.setBounds(81, 250, 51, 23);
		contentPane.add(t);

		JRadioButton f = new JRadioButton("F");
		f.setBounds(157, 250, 41, 23);
		contentPane.add(f);

		JRadioButton c = new JRadioButton("C");
		c.setBounds(220, 250, 51, 23);
		contentPane.add(c);

		JRadioButton s = new JRadioButton("S");
		s.setBounds(285, 250, 109, 23);
		contentPane.add(s);

		ButtonGroup bg = new ButtonGroup();
		bg.add(t);
		bg.add(f);
		bg.add(c);
		bg.add(s);

		JLabel lblNewLabel_1 = new JLabel("Situa\u00E7\u00E3o de Sa\u00FAde:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 115, 176, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("T - CONTAMINADA EM TRATAMENTO");
		lblNewLabel_2.setBounds(30, 150, 292, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("F - CONTAMINADA FALECIDA");
		lblNewLabel_3.setBounds(30, 174, 292, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("C - CONTAMINADA CURADA");
		lblNewLabel_4.setBounds(30, 198, 279, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("S - SEM CONTAMINA\u00C7\u00C3O");
		lblNewLabel_5.setBounds(30, 222, 304, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Idade:");
		lblNewLabel_6.setBounds(54, 62, 51, 21);
		contentPane.add(lblNewLabel_6);

		textField_idade = new JTextField();
		textField_idade.setBounds(136, 65, 51, 18);
		contentPane.add(textField_idade);
		textField_idade.setColumns(10);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validacao.validaNome(textField_nome.getText().trim())
						&& Validacao.validaIdade(textField_idade.getText(), 0, 150)
						&& Validacao.validaSituacaoSaude(t, f, c, s)) {

					Homem homem = new Homem(new StringBuilder(textField_nome.getText().trim()), getCharSituacaoSaude(t, f, c, s), 
							Integer.parseInt(textField_idade.getText()));
					Visao.mostrarMensagem("Registro Efetuado com sucesso!");
					PessoaDAO pessoaDAO = new PessoaDAO();
					pessoaDAO.cadastrarHomem(homem);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(120, 300, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_button.setBounds(220, 300, 89, 23);
		contentPane.add(cancel_button);
		
		setVisible(true);
	}

	public static char getCharSituacaoSaude(JRadioButton rdbtnNewRadioButton, JRadioButton rdbtnNewRadioButton_1,
			JRadioButton rdbtnNewRadioButton_2, JRadioButton rdbtnNewRadioButton_3) {
		char sit_saude = 0;

		if (rdbtnNewRadioButton.isSelected()) {
			sit_saude = 'T';
		}
		if (rdbtnNewRadioButton_1.isSelected()) {
			sit_saude = 'F';
		}
		if (rdbtnNewRadioButton_2.isSelected()) {
			sit_saude = 'C';
		}
		if (rdbtnNewRadioButton_3.isSelected()) {
			sit_saude = 'S';
		}

		return sit_saude;
	}
}
