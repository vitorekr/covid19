package saida;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import conexao.PessoaDAO;
import dados.Mulher;
import validacao.Validacao;

@SuppressWarnings("serial")
public class TelaCadastrarMulheres extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nome;

	public TelaCadastrarMulheres() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 150, 450, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(30, 21, 46, 14);
		contentPane.add(lblNewLabel);

		textField_nome = new JTextField();
		textField_nome.setBounds(86, 18, 279, 20);
		contentPane.add(textField_nome);
		textField_nome.setColumns(10);

		JLabel lblNewLabel_id = new JLabel("ID:");
		lblNewLabel_id.setBounds(30, 45, 46, 14);
		contentPane.add(lblNewLabel_id);

		JLabel lblNewLabel_1 = new JLabel("Situa\u00E7\u00E3o de Sa\u00FAde:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 65, 176, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("T - CONTAMINADA EM TRATAMENTO");
		lblNewLabel_2.setBounds(30, 96, 292, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("F - CONTAMINADA FALECIDA");
		lblNewLabel_3.setBounds(30, 124, 292, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("C - CONTAMINADA CURADA");
		lblNewLabel_4.setBounds(30, 149, 279, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("S - SEM CONTAMINA\u00C7\u00C3O");
		lblNewLabel_5.setBounds(30, 174, 304, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("J\u00E1 foi gestante ou est\u00E1 em periodo de gesta\u00E7\u00E3o?");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(30, 250, 365, 20);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("S - SIM");
		lblNewLabel_7.setBounds(30, 281, 46, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("N - N\u00C3O");
		lblNewLabel_8.setBounds(30, 306, 46, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("T - N\u00C3O TEM CERTEZA");
		lblNewLabel_9.setBounds(30, 331, 254, 14);
		contentPane.add(lblNewLabel_9);

		JRadioButton t = new JRadioButton("T");
		t.setBounds(24, 200, 52, 23);
		contentPane.add(t);

		JRadioButton f = new JRadioButton("F");
		f.setBounds(86, 200, 52, 23);
		contentPane.add(f);

		JRadioButton c = new JRadioButton("C");
		c.setBounds(160, 200, 46, 23);
		contentPane.add(c);

		JRadioButton s = new JRadioButton("S");
		s.setBounds(236, 200, 60, 23);
		contentPane.add(s);

		ButtonGroup bg = new ButtonGroup();
		bg.add(t);
		bg.add(f);
		bg.add(c);
		bg.add(s);

		JRadioButton gestS = new JRadioButton("S");
		gestS.setBounds(24, 365, 52, 23);
		contentPane.add(gestS);

		JRadioButton gestN = new JRadioButton("N");
		gestN.setBounds(97, 365, 52, 23);
		contentPane.add(gestN);

		JRadioButton gestT = new JRadioButton("T");
		gestT.setBounds(175, 365, 109, 23);
		contentPane.add(gestT);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(gestS);
		bg2.add(gestN);
		bg2.add(gestT);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Validacao.validaNome(textField_nome.getText()) && Validacao.validaSituacaoSaude(t, f, c, s) && Validacao.validaGestacao(gestS, gestN, gestT)) {
					Mulher mulher = new Mulher(new StringBuilder(textField_nome.getText()), getCharSituacaoSaude(t, f, c, s),
							 getCharGestacao(gestS, gestN, gestT));
					Visao.mostrarMensagem("Registro Efetuado com sucesso!");
					PessoaDAO pessoaDAO = new PessoaDAO();
					pessoaDAO.cadastrarMulher(mulher);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(120, 453, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_button.setBounds(220, 453, 89, 23);
		contentPane.add(cancel_button);
		
		setVisible(true);
	}
	
	public static char getCharSituacaoSaude(JRadioButton t, JRadioButton f, JRadioButton c, JRadioButton s) {
		char sit_saude = 0;
			
			if(t.isSelected()) {
				sit_saude = 'T';
			}
			if(f.isSelected()) {
				sit_saude = 'F';
			}
			if(c.isSelected()) {
				sit_saude = 'C';
			}
			if(s.isSelected()) {
				sit_saude = 'S';
			}
		
		return sit_saude;
	}
	
	public static char getCharGestacao(JRadioButton s, JRadioButton n, JRadioButton t) {
		char gestacao = 0;
		
		if(s.isSelected()) {
			gestacao = 'S';
		}
		if(n.isSelected()) {
			gestacao = 'N';
		}
		if(t.isSelected()) {
			gestacao = 'T';
		}
		
		return gestacao;
	}

}
