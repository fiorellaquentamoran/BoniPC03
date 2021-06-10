package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ClienteModel;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class FrmReporteClienteConsulta extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDNI;
	private JButton txtFiltrarXDni;
	private Panel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		///TRY CHATCH DE ESTILOS 
		
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
					
				} catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteClienteConsulta frame = new FrmReporteClienteConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmReporteClienteConsulta() {
		setTitle("Consulta Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CONSULTA POR DNI");
		lblNewLabel.setIcon(new ImageIcon(FrmReporteClienteConsulta.class.getResource("/iconos/Search.gif")));
		lblNewLabel.setFont(new Font("Source Code Pro Semibold", Font.BOLD, 22));
		lblNewLabel.setBounds(43, 23, 331, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setFont(new Font("Source Code Pro Light", Font.BOLD, 14));
		lblNewLabel_1.setBounds(53, 81, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(116, 79, 172, 22);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		txtFiltrarXDni = new JButton("Filtrar");
		txtFiltrarXDni.setBackground(new Color(153, 153, 153));
		txtFiltrarXDni.addActionListener(this);
		txtFiltrarXDni.setBounds(321, 79, 95, 32);
		contentPane.add(txtFiltrarXDni);
		
		panelReporte = new Panel();
		panelReporte.setForeground(Color.GRAY);
		panelReporte.setBounds(10, 123, 802, 521);
		contentPane.add(panelReporte);
	
		panelReporte.setLayout(new BorderLayout(0, 0));
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == txtFiltrarXDni) {
			handle_txtFiltrarXDni_actionPerformed(e);
		}
	}
	protected void handle_txtFiltrarXDni_actionPerformed(ActionEvent e) {
		
		//
		String DNI = txtDNI.getText().trim();
		//
		ClienteModel modelCli = new ClienteModel();
		//
		List<Cliente> data = null;
		if(DNI.isEmpty()) {
			
			data= modelCli.LISTACLIENTES();
		}
			
		else {
			
			data= modelCli.CONSULTAPORDNI(DNI);
		}
		
		
		

		///////////////////////////////////////////////////////////77
		///CODIGO PARA MOSTRAR LA DATA EN EL REPORTE 
		//1 DATA 
		//instancio y envio la data obtenida
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		
		//2 DISEÑO
		String RutaFile= "reporteCliente.jasper";
		
		//3 GENERAR REPORTE 
		//se envia el diseño, la data obtenida
		JasperPrint print =GeneradorReporte.genera(RutaFile, dataSource, null);
		
		
		//4 MOSTRAR EN EL VISOR 
		JRViewer JRViewer = new JRViewer(print);
		
		
		// MOSTRAR EN EL PANEL 
	panelReporte.removeAll();
		//se añade el reporte al panel 
		panelReporte.add(JRViewer);
		//
		panelReporte.repaint();
		//
		panelReporte.revalidate();
		
	}
}
