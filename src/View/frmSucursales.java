package View;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Controler.SucursalesDAO;
import Model.Sucursal;

public class frmSucursales {

	JTable tabla;
	JScrollPane sp;
	DefaultTableModel dtable = new DefaultTableModel();

	// BOTONES
	JButton crear = new JButton();
	JButton editar = new JButton();
	JButton eliminar = new JButton();
	JButton pdf = new JButton();

	public void botones() {

		// BOTON CREAR
		crear.setText("Crear");
		crear.setBounds(875, 100, 112, 45);

		ActionListener funcion_crear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		};

		crear.addActionListener(funcion_crear);

		// BOTON EDITAR
		editar.setText("Editar");
		editar.setBounds(1000, 100, 112, 45);

		ActionListener funcion_actualizar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int seleccionar = tabla.getSelectedRow();
				if (seleccionar != -1) {

					modificar();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila");

				}
			}
		};

		editar.addActionListener(funcion_actualizar);

		// BOTON ELIMINAR
		eliminar.setText("Eliminar");
		eliminar.setBounds(1000, 160, 112, 45);

		ActionListener funcion_eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				eliminar();

			}
		};
		eliminar.addActionListener(funcion_eliminar);

		// PDF BUTTON
		pdf.setText("PDF");
		pdf.setBounds(1000, 220, 112, 45);

		ActionListener funcion_pdf = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generar_pdf();
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		pdf.addActionListener(funcion_pdf);

	}

	public void tabla() {
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 10) {
					return true;
				} else {
					return false;
				}
			}
		};

		System.out.println("entrando a tabla");
		tableModel.addColumn("id");
		tableModel.addColumn("codigo");
		tableModel.addColumn("nombre");
		tableModel.addColumn("direccion");
		tableModel.addColumn("correo");
		tableModel.addColumn("telefono");

		SucursalesDAO sdao = new SucursalesDAO();
		List<Sucursal> sucList = new ArrayList<>();
		sucList = sdao.listarSucursales();
		String registros[] = new String[6];

		for (Sucursal su : sucList) {
			registros[0] = su.getIdSucursal() + "";
			registros[1] = su.getCodigo() + "";
			registros[2] = su.getNombre();
			registros[3] = su.getDireccion();
			registros[4] = su.getCorreo();
			registros[5] = su.getTelefono();
			tableModel.addRow(registros);
		}

		tabla = new JTable();
		tabla.setModel(tableModel);
		sp = new JScrollPane(tabla);
		sp.setBounds(10, 10, 800, 600);

	}

	public void crear() {
		JFrame crear = new JFrame();
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		crear.setTitle("Ingresar");
		crear.add(p1);
		// crear.setLocationRelativeTo(null);
		crear.setBounds(375, 20, 600, 700);
		crear.setVisible(true);

		JLabel l1 = new JLabel();
		l1.setText("Codigo");
		l1.setFont(new Font("arial", Font.PLAIN, 20));
		l1.setBounds(50, 50, 150, 60);
		l1.setVisible(true);

		JLabel l2 = new JLabel();
		l2.setText("Nombre");
		l2.setFont(new Font("arial", Font.PLAIN, 20));
		l2.setBounds(50, 100, 150, 60);
		l2.setVisible(true);

		JLabel l3 = new JLabel();
		l3.setText("Direccion");
		l3.setFont(new Font("arial", Font.PLAIN, 20));
		l3.setBounds(50, 150, 150, 60);
		l3.setVisible(true);

		JLabel l4 = new JLabel();
		l4.setText("correo");
		l4.setFont(new Font("arial", Font.PLAIN, 20));
		l4.setBounds(50, 200, 150, 60);
		l4.setVisible(true);

		JLabel l5 = new JLabel();
		l5.setText("Telefono");
		l5.setFont(new Font("arial", Font.PLAIN, 20));
		l5.setBounds(50, 250, 150, 60);
		l5.setVisible(true);

		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);

		JTextField t1 = new JTextField();
		t1.setBounds(170, 65, 200, 25);

		JTextField t2 = new JTextField();
		t2.setBounds(170, 115, 200, 25);

		JTextField t3 = new JTextField();
		t3.setBounds(170, 165, 200, 25);

		JTextField t4 = new JTextField();
		t4.setBounds(170, 215, 200, 25);

		JTextField t5 = new JTextField();
		t5.setBounds(170, 265, 200, 25);

		p1.add(t1);
		p1.add(t2);
		p1.add(t3);
		p1.add(t4);
		p1.add(t5);

		JButton b1 = new JButton();
		b1.setText("Guardar");
		b1.setBounds(200, 550, 100, 50);
		p1.add(b1);

		ActionListener ingresar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Sucursal suc = new Sucursal();
					suc.setCodigo(Integer.parseInt(t1.getText()));
					suc.setNombre(t2.getText());
					suc.setDireccion(t3.getText());
					suc.setCorreo(t4.getText());
					suc.setTelefono(t5.getText());

					SucursalesDAO sdao = new SucursalesDAO();

					try {
						sdao.IngresarSucursal(suc);

						JOptionPane.showMessageDialog(null, "Registro Ingresado!");

						crear.dispose();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "llene todos los campos");
				}

			}
		};

		b1.addActionListener(ingresar);

	}

	public void modificar() {
		int seleccionar = tabla.getSelectedRow();
		DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
		String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

		Sucursal su = new Sucursal();
		SucursalesDAO sdao = new SucursalesDAO();
		su = sdao.buscarSucursal(Integer.parseInt(dato));

		if (seleccionar != -1) {

			JFrame crear = new JFrame();
			JPanel p1 = new JPanel();
			p1.setLayout(null);
			crear.setTitle("Modificar");
			crear.add(p1);
			// crear.setLocationRelativeTo(null);
			crear.setBounds(375, 20, 600, 700);
			crear.setVisible(true);

			JLabel l1 = new JLabel();
			l1.setText("Codigo");
			l1.setFont(new Font("arial", Font.PLAIN, 20));
			l1.setBounds(50, 50, 150, 60);
			l1.setVisible(true);

			JLabel l2 = new JLabel();
			l2.setText("Nombre");
			l2.setFont(new Font("arial", Font.PLAIN, 20));
			l2.setBounds(50, 100, 150, 60);
			l2.setVisible(true);

			JLabel l3 = new JLabel();
			l3.setText("Direccion");
			l3.setFont(new Font("arial", Font.PLAIN, 20));
			l3.setBounds(50, 150, 150, 60);
			l3.setVisible(true);

			JLabel l4 = new JLabel();
			l4.setText("correo");
			l4.setFont(new Font("arial", Font.PLAIN, 20));
			l4.setBounds(50, 200, 150, 60);
			l4.setVisible(true);

			JLabel l5 = new JLabel();
			l5.setText("Telefono");
			l5.setFont(new Font("arial", Font.PLAIN, 20));
			l5.setBounds(50, 250, 150, 60);
			l5.setVisible(true);

			String id = su.getIdSucursal() + "";

			p1.add(l1);
			p1.add(l2);
			p1.add(l3);
			p1.add(l4);
			p1.add(l5);

			JTextField t1 = new JTextField();
			t1.setBounds(170, 65, 200, 25);
			t1.setText(su.getCodigo() + "66");

			JTextField t2 = new JTextField();
			t2.setBounds(170, 115, 200, 25);
			t2.setText(su.getNombre());

			JTextField t3 = new JTextField();
			t3.setBounds(170, 165, 200, 25);
			t3.setText(su.getDireccion());

			JTextField t4 = new JTextField();
			t4.setBounds(170, 215, 200, 25);
			t4.setText(su.getCorreo());

			JTextField t5 = new JTextField();
			t5.setBounds(170, 265, 200, 25);
			t5.setText(su.getTelefono());

			p1.add(t1);
			p1.add(t2);
			p1.add(t3);
			p1.add(t4);
			p1.add(t5);

			JButton b1 = new JButton();
			b1.setText("Guardar");
			b1.setBounds(200, 550, 100, 50);
			p1.add(b1);

			ActionListener ingresar = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						Sucursal suc = new Sucursal();
						suc.setIdSucursal(Integer.parseInt(id));
						suc.setCodigo(Integer.parseInt(t1.getText()));
						suc.setNombre(t2.getText());
						suc.setDireccion(t3.getText());
						suc.setCorreo(t4.getText());
						suc.setTelefono(t5.getText());

						SucursalesDAO udao = new SucursalesDAO();
						udao.Modificar(suc);
						crear.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "llene todos los campos");
					}

				}
			};

			b1.addActionListener(ingresar);

		}

	}

	public void eliminar() {

		try {
			int seleccionar = tabla.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
			String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

			Sucursal su = new Sucursal();
			SucursalesDAO sdao = new SucursalesDAO();
			su = sdao.buscarSucursal(Integer.parseInt(dato));

			sdao.Eliminar(su);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Seleccione un Item");

		}

	}

	
	private void generar_pdf() throws FileNotFoundException, DocumentException {

		FileOutputStream gen = new FileOutputStream("Vendedores.pdf");
		Document documento = new Document();

		PdfWriter.getInstance(documento, gen);
		documento.open();

		Paragraph parrafo = new Paragraph("vendedores");
		parrafo.setAlignment(1);
		documento.add(parrafo);
		documento.add(new Paragraph("\n"));

		SucursalesDAO sdao = new SucursalesDAO();
		List<Sucursal> sucList = new ArrayList<>();
		sucList = sdao.listarSucursales();

		for (Sucursal su : sucList) {
			documento.add(new Paragraph("id: " + su.getIdSucursal() + ""));
			documento.add(new Paragraph("codigo: " + su.getCodigo() + ""));
			documento.add(new Paragraph("Nombre: " + su.getNombre()));
			documento.add(new Paragraph("Direccion: " + su.getDireccion()));
			documento.add(new Paragraph("Correo: " + su.getCorreo()));
			documento.add(new Paragraph("Telefono: " + su.getTelefono()));

			documento.add(new Paragraph("\n\n"));
		}
		documento.close();
		JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
		try {
			File sucursales_doc = new File("Vendedores.pdf");
			Desktop.getDesktop().open(sucursales_doc);
		} catch (Exception e) {
		}

	}

	public void ejecutar() {
		botones();
		tabla();
	}

}
