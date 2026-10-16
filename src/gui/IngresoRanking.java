package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

import datos.Datos;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class IngresoRanking extends JDialog {
	
	public IngresoRanking(JFrame owner, Datos datos, String puntos) {
		super(owner, "Ingreso al ranking", true);
		int centroX = owner.getLocationOnScreen().x + owner.getWidth() / 2;
		int centroY = owner.getLocationOnScreen().y + owner.getHeight() / 2;
		int x = centroX - 450 / 2;
		int y = centroY - 300 / 2;
		setBounds(x, y, 374, 220);
		JPanel contentPanel = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextField textField = new JTextField();
		textField.setBounds(123, 37, 205, 29);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) { 				
				char c = e.getKeyChar();				
				if(c == ' ')
					e.consume();  
			}
		});
		contentPanel.add(textField);
		
		JLabel lblAviso = new JLabel("Nombre");
		lblAviso.setForeground(new Color(255, 140, 0));
		lblAviso.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblAviso.setBounds(26, 37, 80, 28);		
		lblAviso.setVisible(false);
		contentPanel.add(lblAviso);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				if (nombre.equals("")) {
					lblAviso.setVisible(true);
				}else {
					datos.guardarPuntuacion(nombre, puntos);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(26, 111, 122, 47);
		contentPanel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(206, 111, 122, 47);
		contentPanel.add(btnCancelar);
	}
}