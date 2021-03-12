package saida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import conexao.PessoaDAO;

@SuppressWarnings("serial")
public class TelaLista extends JFrame{
	private JPanel contentPane;
	private JTextField textField_nome;
	PessoaDAO dao = new PessoaDAO();
	
	public TelaLista() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel label_nome = new JLabel("NOME: ");
		label_nome.setBounds(220, 478, 292, 14);
		contentPane.add(label_nome);
		
		textField_nome = new JTextField();
		textField_nome.setBounds(280, 475, 200, 20);
		contentPane.add(textField_nome);
		textField_nome.setColumns(10);
		
		String colunas[] = {"ID", "NOME", "SITUAÇÃO DE SAÚDE", "IDADE", "GESTANTE"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		JTable table = new JTable(modelo);
		
		dao.listaPessoa(modelo);
		
		JButton pesquisa = new JButton("Pesquisa");
		pesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaDAO dao = new PessoaDAO(); 
				dao.pesquisaPessoa(textField_nome.getText(), modelo);	
				Visao.mostrarMensagem("Quantidade de cadastros recuperados: " + modelo.getRowCount());
				
			}
		});
		pesquisa.setBounds(500, 465, 119, 32);
		contentPane.add(pesquisa);
		
		JButton cancel_button = new JButton("Cancelar");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_button.setBounds(500, 520, 119, 32);
		contentPane.add(cancel_button);
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
