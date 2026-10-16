package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import logica.Logica;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.event.MenuKeyEvent;

import audio.Sonido;
import datos.Datos;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	
	private JPanel contentPane;	
	private Logica logica;
	private Set<JLabel> entidades;
	private JLayeredPane panelLaberinto;
	private JLabel imagenLaberinto;
	private JLayeredPane panelMenu;
	private JLabel pausa;	
	private JButton configuracion;
	private JButton sonido;
	private boolean sonidoActivado;		
	private JLabel lblPuntos;	
	private JPanel panelVidas;
	private JLabel[] lblVidas;
	private int vidas;	
	private JPanel panelPowerUps;
	private JLabel[] lblPowerUps;
	private int cantPowerUps;
	private final ImageIcon pocionVelocidad = new ImageIcon(Gui.class.getResource("/imagenes/pocionVelocidadCerveza32x32.png"));
	private final ImageIcon pocionBomba = new ImageIcon(Gui.class.getResource("/imagenes/pocionBomba32x32.png"));
	private final ImageIcon pocionHielo = new ImageIcon(Gui.class.getResource("/imagenes/pocionHielo32x32.png"));
	private JButton nuevoJuego;
	private JButton records;
	private JButton salir;
	private Datos datos;
	
	@SuppressWarnings("deprecation")
	public Gui(Logica logica) {
		Sonido musica = new Sonido();
		setTitle("Pacman");		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/imagenes/protagonistaHomero32x32.png")));		
		this.logica = logica;
		entidades = new HashSet<JLabel>();
		lblVidas = new JLabel[4];
		lblPowerUps = new JLabel[4];
		sonidoActivado = true;
		cantPowerUps = 0;
		datos = new Datos();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(250, 32, 1024, 839);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLaberinto = new JLayeredPane();
		panelLaberinto.setBounds(0, 0, 800, 800);
		panelLaberinto.setLayout(null);
		contentPane.add(panelLaberinto);
		
		imagenLaberinto = new JLabel();
		imagenLaberinto.setBounds(0, 0, 800, 800);
		setImagenLaberinto("/imagenes/laberintoNivel1.png");
		panelLaberinto.add(imagenLaberinto, new Integer(1));
		
		panelMenu = new JLayeredPane();
		panelMenu.setBackground(Color.GRAY);
		panelMenu.setBounds(800, 0, 208, 800);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
				
		JLabel fondoMenu = new JLabel("");
		fondoMenu.setBounds(0, 0, 208, 800);
		fondoMenu.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/fondoMenu208x800.png")));
		panelMenu.add(fondoMenu, new Integer(1));
		
		nuevoJuego = new JButton("");
		nuevoJuego.setContentAreaFilled(false);
		nuevoJuego.setBorderPainted(false);
		nuevoJuego.setFocusPainted(false);
		nuevoJuego.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/nuevoJuego156x58.png")));
		nuevoJuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				nuevoJuego.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/nuevoJuego156x58.png")));
				iniciarJuego();
				getContentPane().requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				nuevoJuego.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/nuevoJuegoEntered156x58.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nuevoJuego.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/nuevoJuego156x58.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				nuevoJuego.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/nuevoJuegoPressed156x58.png")));
			}
		});
		nuevoJuego.setBounds(26, 427, 156, 58);
		panelMenu.add(nuevoJuego, new Integer(2));
		
		records = new JButton("");
		records.setContentAreaFilled(false);
		records.setBorderPainted(false);
		records.setFocusPainted(false);
		records.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/records156x58.png")));
		records.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				records.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/records156x58.png")));
				mostrarRecords();
				getContentPane().requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				records.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/recordsEntered156x58.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				records.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/records156x58.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				records.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/recordsPressed156x58.png")));
			}
		});
		records.setBounds(26, 496, 156, 58);
		panelMenu.add(records, new Integer(2));
		
		salir = new JButton("");
		salir.setContentAreaFilled(false);
		salir.setBorderPainted(false);
		salir.setFocusPainted(false);
		salir.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/salir156x58.png")));
		salir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				salir.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/salirEntered156x58.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				salir.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/salir156x58.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				salir.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/salirPressed156x58.png")));
			}
		});
		salir.setBounds(26, 565, 156, 58);
		panelMenu.add(salir, new Integer(2));
		
		panelVidas = new JPanel();
		panelVidas.setOpaque(false);
		panelVidas.setBorder(null);
		panelVidas.setBounds(12, 265, 185, 32);
		panelMenu.add(panelVidas, new Integer(2));
		panelVidas.setLayout(null);		
		
		JLabel lblVida1 = new JLabel();
		lblVidas[0] = lblVida1;
		lblVida1.setBounds(147, 0, 32, 32);
		lblVida1.setVisible(false);
		panelVidas.add(lblVida1);
		lblVida1.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/protagonistaHomero32x32.png")));

		JLabel lblVida2 = new JLabel();
		lblVidas[1] = lblVida2;
		lblVida2.setBounds(100, 0, 32, 32);
		lblVida2.setVisible(false);
		panelVidas.add(lblVida2);
		lblVida2.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/protagonistaHomero32x32.png")));
		
		JLabel lblVida3= new JLabel();
		lblVidas[2] = lblVida3;
		lblVida3.setBounds(53, 0, 32, 32);
		lblVida3.setVisible(false);
		panelVidas.add(lblVida3);
		lblVida3.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/protagonistaHomero32x32.png")));
		
		JLabel lblVida4 = new JLabel();
		lblVidas[3] = lblVida4;
		lblVida4.setBounds(6, 0, 32, 32);
		lblVida4.setVisible(false);
		panelVidas.add(lblVida4);
		lblVida4.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/protagonistaHomero32x32.png")));
		
		configuracion = new JButton("");
		configuracion.setContentAreaFilled(false);
		configuracion.setBorderPainted(false);
		configuracion.setFocusPainted(false);
		configuracion.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/configuracion32x32.png")));
		configuracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				configuracion.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/configuracion32x32.png")));
				mostrarConfiguracion();
				getContentPane().requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				configuracion.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/configuracionEntered32x32.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				configuracion.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/configuracion32x32.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				configuracion.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/configuracionPressed32x32.png")));
			}
		});
		configuracion.setBounds(166, 11, 32, 32);
		panelMenu.add(configuracion, new Integer(2));
		
		sonido = new JButton("");
		sonido.setContentAreaFilled(false);
		sonido.setBorderPainted(false);
		sonido.setFocusPainted(false);
		sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoActivado32x32.png")));
		sonido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (sonidoActivado) {
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoDesactivado32x32.png")));
					sonidoActivado = false;
					musica.mutear();
				}else {
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoActivado32x32.png")));
					sonidoActivado = true;
					musica.desmutear();
				}
				getContentPane().requestFocus();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (sonidoActivado)
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoActivadoEntered32x32.png")));
				else
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoDesactivadoEntered32x32.png")));
					
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (sonidoActivado)
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoActivado32x32.png")));
				else
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoDesactivado32x32.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (sonidoActivado)
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoActivadoPressed32x32.png")));
				else
					sonido.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/sonidoDesactivadoPressed32x32.png")));
			}
		});
		sonido.setBounds(166, 757, 32, 32);
		panelMenu.add(sonido, new Integer(2));
		
		lblPuntos = new JLabel("0");
		lblPuntos.setForeground(new Color(0, 0, 0));
		lblPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuntos.setFont(new Font("Open Sans", Font.PLAIN, 25));
		lblPuntos.setBounds(19, 168, 170, 27);
		panelMenu.add(lblPuntos, new Integer(2));
		
		panelPowerUps = new JPanel();
		panelPowerUps.setBounds(12, 360, 185, 32);
		panelPowerUps.setOpaque(false);
		panelPowerUps.setBorder(null);
		panelMenu.add(panelPowerUps, new Integer(2));
		panelPowerUps.setLayout(null);		
		
		JLabel lblPowerUp1 = new JLabel();
		lblPowerUps[0] = lblPowerUp1;
		lblPowerUp1.setBounds(147, 0, 32, 32);
		panelPowerUps.add(lblPowerUp1);
		
		JLabel lblPowerUp2 = new JLabel();
		lblPowerUps[1] = lblPowerUp2;
		lblPowerUp2.setBounds(100, 0, 32, 32);
		panelPowerUps.add(lblPowerUp2);
		
		JLabel lblPowerUp3 = new JLabel();
		lblPowerUps[2] = lblPowerUp3;
		lblPowerUp3.setBounds(53, 0, 32, 32);
		panelPowerUps.add(lblPowerUp3);
		
		JLabel lblPowerUp4 = new JLabel();
		lblPowerUps[3] = lblPowerUp4;
		lblPowerUp4.setBounds(6, 0, 32, 32);
		panelPowerUps.add(lblPowerUp4);		
		
		pausa = new JLabel();
		pausa.setBounds(169, 300, 460, 150);
		pausa.setVisible(false);
		panelLaberinto.add(pausa, new Integer(3));
		pausa.setIcon(new ImageIcon(Gui.class.getResource("/imagenes/pausa460x150.png")));		
		
		getContentPane().setFocusable(true);
		getContentPane().addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
                	logica.cambiarDireccionProtagonista(0);                	
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                	logica.cambiarDireccionProtagonista(90);
                }
                if(e.getKeyCode()==KeyEvent.VK_UP){
                	logica.cambiarDireccionProtagonista(270);
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT) {
                	logica.cambiarDireccionProtagonista(180);
                }
                if(e.getKeyCode()==MenuKeyEvent.VK_SPACE) {
                	if (logica.soltarBomba())
                		eliminarPowerUp(pocionBomba);
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                	if (logica.juegoEnCurso())
                		if(logica.juegoEnPausa()) {
                			reanudarJuego();
                		}else {
                			pausarJuego();
                		}
                }
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	
	@SuppressWarnings("deprecation")
	public void agregarEntidadGrafica(JLabel entidadGrafica) {
		panelLaberinto.add(entidadGrafica, new Integer(2));
		entidades.add(entidadGrafica);
		entidadGrafica.setVisible(true);
	}
	
	public void setImagenLaberinto(String url) {
		imagenLaberinto.setIcon(new ImageIcon(Gui.class.getResource(url)));
	}

	private void iniciarJuego() {
		logica.iniciarJuego();
		pausa.setVisible(false);
		vidas = logica.getVidasIniciales();
		int i = 0;
		while (i<vidas)
			lblVidas[i++].setVisible(true);
		while(i<lblVidas.length)
			lblVidas[i++].setVisible(false);
		for(JLabel lbl : lblPowerUps)
			lbl.setIcon(null);
		cantPowerUps = 0;
	}
	
	public void perderVida() {
		lblVidas[--vidas].setVisible(false);
		if (vidas == 0)
			finalizarJuego();
	}
	
	public void ganarVida() {
		lblVidas[vidas++].setVisible(true);
	}
	
	public void actualizarPuntos(int puntos) {
		lblPuntos.setText(""+puntos);
	}
	
	private void pausarJuego() {
		logica.pausarJuego();
		pausa.setVisible(true);
	}
	
	private void reanudarJuego() {
		logica.reanudarJuego();
		pausa.setVisible(false);
	}
	
	private void finalizarJuego() {
		IngresoRanking ingresoRanking = new IngresoRanking(this, datos, lblPuntos.getText());
		ingresoRanking.setVisible(true);
	}
	
	public void borrarLaberinto() {
		for(JLabel j : entidades) {
			j.setVisible(false);
			panelLaberinto.remove(j);
		}
		imagenLaberinto.setIcon(null);
		for(JLabel lbl : lblPowerUps)
			lbl.setIcon(null);
		cantPowerUps = 0;
	}
	
	private void mostrarRecords() {
		boolean estabaEnPausa = logica.juegoEnPausa();
		if (logica.juegoEnCurso() && !logica.juegoEnPausa())
			pausarJuego();
		TablaRanking ranking = new TablaRanking(this, datos);
		ranking.setVisible(true);
		if (logica.juegoEnCurso() && !estabaEnPausa)
			reanudarJuego();
	}
	
	private void mostrarConfiguracion() {
		boolean estabaEnPausa = logica.juegoEnPausa();
		if (logica.juegoEnCurso() && !logica.juegoEnPausa())
			pausarJuego();
		Configuracion configuracion = new Configuracion(this, logica);
		configuracion.setVisible(true);
		if (logica.juegoEnCurso() && !estabaEnPausa)
			reanudarJuego();
	}

	public void activarPocionBomba() {
		lblPowerUps[cantPowerUps++].setIcon(pocionBomba);
	}
	
	public void activarPocionVelocidad() {
		lblPowerUps[cantPowerUps++].setIcon(pocionVelocidad);
	}
	
	public void activarPocionHielo() {
		lblPowerUps[cantPowerUps++].setIcon(pocionHielo);
	}
	
	public void desactivarPocionVelocidad() {
		eliminarPowerUp(pocionVelocidad);
	}

	public void desactivarPocionHielo() {
		eliminarPowerUp(pocionHielo);
	}
	
	private void eliminarPowerUp(ImageIcon image) {
		int i = 0;
		while(lblPowerUps[i].getIcon() != image) 
			i++;
		while(i < lblPowerUps.length - 1) {
			lblPowerUps[i].setIcon(lblPowerUps[i+1].getIcon());
			i++;
		}
		lblPowerUps[i].setIcon(null);
    	cantPowerUps--;
	}
}
