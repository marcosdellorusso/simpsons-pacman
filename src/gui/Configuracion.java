package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logica.Logica;

@SuppressWarnings("serial")
public class Configuracion extends JDialog {
	
	private int dificultad;
	private int vidas;
	private JLabel lblVida1, lblVida2, lblVida3, lblVida4;
	private JLabel lblDificultadBaja, lblDificultadMedia, lblDificultadDificil;
	
	public Configuracion(JFrame owner, Logica logica) {
		super(owner, "Configuracion", true);
		int centroX = owner.getLocationOnScreen().x + owner.getWidth() / 2;
		int centroY = owner.getLocationOnScreen().y + owner.getHeight() / 2;
		int x = centroX - 450 / 2;
		int y = centroY - 300 / 2;
		setBounds(x, y, 450, 300);
		setUndecorated(true);
		
		dificultad = logica.getDificultad();
		vidas = logica.getVidasIniciales();
		
		JLayeredPane contentPanel = new JLayeredPane();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 450, 300);
		fondo.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/fondoConfiguracion.png")));
		contentPanel.add(fondo, Integer.valueOf(1));
		
		lblDificultadBaja = new JLabel("");
		lblDificultadBaja.setBounds(203, 81, 71, 49);
		lblDificultadBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (dificultad != 0)
					lblDificultadBaja.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadBajaEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (dificultad != 0)
					lblDificultadBaja.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadBaja.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				dificultad = 0;
				actualizarImagenesDificultad();
			}
		});
		contentPanel.add(lblDificultadBaja, Integer.valueOf(2));
		
		lblDificultadMedia = new JLabel("");
		lblDificultadMedia.setBounds(274, 81, 86, 43);
		lblDificultadMedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (dificultad != 1)
					lblDificultadMedia.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadMediaEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (dificultad != 1)
					lblDificultadMedia.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadMedia.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				dificultad = 1;
				actualizarImagenesDificultad();
			}
		});
		contentPanel.add(lblDificultadMedia, Integer.valueOf(2));
		
		lblDificultadDificil = new JLabel("");
		lblDificultadDificil.setBounds(356, 81, 75, 43);
		lblDificultadDificil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (dificultad != 2)
					lblDificultadDificil.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadDificilEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (dificultad != 2)
					lblDificultadDificil.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/dificultadDificil.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				dificultad = 2;
				actualizarImagenesDificultad();
			}
		});
		contentPanel.add(lblDificultadDificil, Integer.valueOf(2));

		lblVida1 = new JLabel("");
		lblVida1.setBounds(232, 134, 41, 61);
		lblVida1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (vidas != 1)
					lblVida1.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas1Entered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (vidas != 1)
					lblVida1.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas1.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				vidas = 1;
				actualizarImagenesVidas();
			}
		});
		contentPanel.add(lblVida1, Integer.valueOf(2));
		
		lblVida2 = new JLabel("");
		lblVida2.setBounds(267, 133, 46, 63);
		lblVida2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (vidas != 2)
					lblVida2.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas2Entered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (vidas != 2)
					lblVida2.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas2.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {				
				vidas = 2;
				actualizarImagenesVidas();
			}
		});
		contentPanel.add(lblVida2, Integer.valueOf(2));
		
		lblVida3 = new JLabel("");
		lblVida3.setBounds(307, 133, 47, 63);
		lblVida3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (vidas != 3)
					lblVida3.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas3Entered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (vidas != 3)
					lblVida3.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas3.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				vidas = 3;
				actualizarImagenesVidas();
			}
		});
		contentPanel.add(lblVida3, Integer.valueOf(2));
		
		lblVida4 = new JLabel("");
		lblVida4.setBounds(346, 135, 47, 61);
		lblVida4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (vidas != 4)
					lblVida4.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas4Entered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (vidas != 4)
					lblVida4.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/vidas4.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				vidas = 4;
				actualizarImagenesVidas();
			}
		});
		contentPanel.add(lblVida4, Integer.valueOf(2));

		JButton btnConfirmar = new JButton(new ImageIcon(Gui.class.getResource("/imagenes/confirmar143x55.png")));
		btnConfirmar.setBounds(32, 220, 143, 55);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setFocusPainted(false);
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirmar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/confirmarEntered143x55.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/confirmar143x55.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnConfirmar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/confirmarPressed143x55.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				logica.setDificultad(dificultad);
				logica.setVidasIniciales(vidas);
				setVisible(false);
			}
		});
		contentPanel.add(btnConfirmar, Integer.valueOf(2));
		
		JButton btnCancelar = new JButton(new ImageIcon(Gui.class.getResource("/imagenes/cancelar143x55.png")));
		btnCancelar.setBounds(198, 220, 143, 55);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setFocusPainted(false);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/cancelarEntered143x55.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/cancelar143x55.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnCancelar.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/cancelarPressed143x55.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setVisible(false);
			}
		});
		contentPanel.add(btnCancelar, Integer.valueOf(2));
		
		actualizarImagenesDificultad();
		actualizarImagenesVidas();
	}
	
	private void actualizarImagenesVidas() {
		switch(vidas) {
		case 1:
			lblVida1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas1Selected.png")));
			lblVida2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas2.png")));
			lblVida3.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas3.png")));
			lblVida4.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas4.png")));
			break;
		case 2:
			lblVida1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas1.png")));
			lblVida2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas2Selected.png")));
			lblVida3.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas3.png")));
			lblVida4.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas4.png")));
			break;
		case 3:
			lblVida1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas1.png")));
			lblVida2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas2.png")));
			lblVida3.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas3Selected.png")));
			lblVida4.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas4.png")));
			break;
		case 4:
			lblVida1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas1.png")));
			lblVida2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas2.png")));
			lblVida3.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas3.png")));
			lblVida4.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/vidas4Selected.png")));
			break;
		}
	}
	
	private void actualizarImagenesDificultad() {
		switch(dificultad) {
		case 0:
			lblDificultadBaja.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadBajaSelected.png")));
			lblDificultadMedia.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadMedia.png")));
			lblDificultadDificil.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadDificil.png")));
			break;
		case 1:
			lblDificultadBaja.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadBaja.png")));
			lblDificultadMedia.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadMediaSelected.png")));
			lblDificultadDificil.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadDificil.png")));
			break;
		case 2:
			lblDificultadBaja.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadBaja.png")));
			lblDificultadMedia.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadMedia.png")));
			lblDificultadDificil.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/dificultadDificilSelected.png")));
			break;
		}
	}
}
