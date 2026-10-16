package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datos.Datos;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TablaRanking extends JDialog {
	
    public TablaRanking(JFrame owner, Datos datos) {
		super(owner, "Ranking de puntajes", true);
		setResizable(false);
		int centroX = owner.getLocationOnScreen().x + owner.getWidth() / 2;
		int centroY = owner.getLocationOnScreen().y + owner.getHeight() / 2;
		int x = centroX - 450 / 2;
		int y = centroY - 300 / 2;
		setBounds(x, y, 403, 550);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        String[] columnas = {"Puntaje", "Jugador"};
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);
        JTable tabla = new JTable();
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setFillsViewportHeight(true);
        tabla.setModel(modelo);
        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setBounds(0, 0, 388, 420);
        desplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(desplazamiento);

        for(String[] fila : datos.recuperarPuntuaciones())
            modelo.addRow(fila);        
        
        JButton btnNewButton = new JButton("Reiniciar Tabla");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		datos.reiniciarPuntuaciones();
        		modelo.setRowCount(0);
        	}
        });
        btnNewButton.setBounds(127, 446, 122, 36);
        getContentPane().add(btnNewButton);
    }
}