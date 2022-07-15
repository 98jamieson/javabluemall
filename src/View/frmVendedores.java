package View;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Controler.ClientesDAO;
import Controler.VendedoresDAO;
import Model.Cliente;
import Model.Vendedor;

public class frmVendedores {

	JTable tabla;
	JScrollPane sp;
	DefaultTableModel dtable = new DefaultTableModel();

	JButton crear = new JButton();
	JButton editar = new JButton();
	JButton eliminar = new JButton();
	JButton pdf = new JButton();

	public void botones() {

//		**************** CREAR **********************
		crear.setText("Crear");
		crear.setBounds(875, 100, 112, 45);

		ActionListener funcion_crear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		};

		crear.addActionListener(funcion_crear);

//		****************** EDITAR  ************************
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

		// ************** BOTON ELIMINAR ***********************
		eliminar.setText("Eliminar");
		eliminar.setBounds(1000, 160, 112, 45);

		ActionListener funcion_eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				eliminar();

			}
		};
		eliminar.addActionListener(funcion_eliminar);

		// *************** BOTON PDF *************
		pdf.setText("PDF");
		pdf.setBounds(1000, 220, 112, 45);

		ActionListener funcion_pdf = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generar_pdf();
					System.out.println("hola");
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		pdf.addActionListener(funcion_pdf);

	}

	public void tabla() {
		DefaultTableModel tableModel = new DefaultTableModel();

		tableModel.addColumn("id");
		tableModel.addColumn("codigo");
		tableModel.addColumn("nombre");
		tableModel.addColumn("genero");
		tableModel.addColumn("caja");
		tableModel.addColumn("ventas");
		tableModel.addColumn("Password");

		VendedoresDAO vdao = new VendedoresDAO();
		List<Vendedor> vendedoresList = new ArrayList<>();
		vendedoresList = vdao.listarVendedores();

		String registros[] = new String[7];

		for (Vendedor ven : vendedoresList) {
			registros[0] = ven.getIdVendedor() + "";
			registros[1] = ven.getCodigo() + "";
			registros[2] = ven.getNombre();
			registros[3] = ven.getGenero();
			registros[4] = ven.getCaja() + "";
			registros[5] = ven.getVentas() + "";
			registros[6] = ven.getConstrasenia();
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
		l3.setText("Genero");
		l3.setFont(new Font("arial", Font.PLAIN, 20));
		l3.setBounds(50, 150, 150, 60);
		l3.setVisible(true);

		JLabel l4 = new JLabel();
		l4.setText("Caja");
		l4.setFont(new Font("arial", Font.PLAIN, 20));
		l4.setBounds(50, 200, 150, 60);
		l4.setVisible(true);

		JLabel l5 = new JLabel();
		l5.setText("Ventas");
		l5.setFont(new Font("arial", Font.PLAIN, 20));
		l5.setBounds(50, 250, 150, 60);
		l5.setVisible(true);

		JLabel l6 = new JLabel();
		l6.setText("Password");
		l6.setFont(new Font("arial", Font.PLAIN, 20));
		l6.setBounds(50, 300, 150, 60);
		l6.setVisible(true);

		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);

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

		JTextField t6 = new JTextField();
		t6.setBounds(170, 315, 200, 25);

		p1.add(t1);
		p1.add(t2);
		p1.add(t3);
		p1.add(t4);
		p1.add(t5);
		p1.add(t6);

		JButton b1 = new JButton();
		b1.setText("Guardar");
		b1.setBounds(200, 550, 100, 50);
		p1.add(b1);

		ActionListener ingresar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Vendedor ven = new Vendedor();
					ven.setCodigo(Integer.parseInt(t1.getText()));
					ven.setNombre(t2.getText());
					ven.setGenero(t3.getText());
					ven.setCaja(Integer.parseInt(t4.getText()));
					ven.setVentas(Integer.parseInt(t5.getText()));
					ven.setConstrasenia(t6.getText());

					VendedoresDAO vdao = new VendedoresDAO();

					try {
						vdao.ingresarVendedor(ven);

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

	public void eliminar() {

		try {
			int seleccionar = tabla.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
			String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

			Vendedor ven = new Vendedor();
			VendedoresDAO vdao = new VendedoresDAO();
			ven = vdao.buscarVendedor(Integer.parseInt(dato));

			vdao.Eliminar(ven);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Seleccione un Item");

		}

	}

	public void modificar() {
		int seleccionar = tabla.getSelectedRow();
		DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
		String dato = String.valueOf(tm.getValueAt(tabla.getSelectedRow(), 0));

		Vendedor ven = new Vendedor();
		VendedoresDAO vdao = new VendedoresDAO();
		ven = vdao.buscarVendedor(Integer.parseInt(dato));

		if (seleccionar != -1) {

			JFrame crear = new JFrame();
			JPanel p1 = new JPanel();
			p1.setLayout(null);
			crear.setTitle("Modificar");
			crear.add(p1);
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
			l3.setText("Genero");
			l3.setFont(new Font("arial", Font.PLAIN, 20));
			l3.setBounds(50, 150, 150, 60);
			l3.setVisible(true);

			JLabel l4 = new JLabel();
			l4.setText("Caja");
			l4.setFont(new Font("arial", Font.PLAIN, 20));
			l4.setBounds(50, 200, 150, 60);
			l4.setVisible(true);

			JLabel l5 = new JLabel();
			l5.setText("Ventas");
			l5.setFont(new Font("arial", Font.PLAIN, 20));
			l5.setBounds(50, 250, 150, 60);
			l5.setVisible(true);

			JLabel l6 = new JLabel();
			l6.setText("Password");
			l6.setFont(new Font("arial", Font.PLAIN, 20));
			l6.setBounds(50, 300, 150, 60);
			l6.setVisible(true);

			String id = ven.getIdVendedor() + "";

			p1.add(l1);
			p1.add(l2);
			p1.add(l3);
			p1.add(l4);
			p1.add(l5);
			p1.add(l6);

			JTextField t1 = new JTextField();
			t1.setBounds(170, 65, 200, 25);
			t1.setText(ven.getCodigo() + "");

			JTextField t2 = new JTextField();
			t2.setBounds(170, 115, 200, 25);
			t2.setText(ven.getNombre());

			JTextField t3 = new JTextField();
			t3.setBounds(170, 165, 200, 25);
			t3.setText(ven.getGenero());

			JTextField t4 = new JTextField();
			t4.setBounds(170, 215, 200, 25);
			t4.setText(ven.getCaja() + "");

			JTextField t5 = new JTextField();
			t5.setBounds(170, 265, 200, 25);
			t5.setText(ven.getVentas() + "");

			JTextField t6 = new JTextField();
			t6.setBounds(170, 315, 200, 25);
			t6.setText(ven.getConstrasenia());

			p1.add(t1);
			p1.add(t2);
			p1.add(t3);
			p1.add(t4);
			p1.add(t5);
			p1.add(t6);

			JButton b1 = new JButton();
			b1.setText("Guardar");
			b1.setBounds(200, 550, 100, 50);
			p1.add(b1);

			ActionListener ingresar = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						Vendedor ven = new Vendedor();
						ven.setIdVendedor(Integer.parseInt(id));
						ven.setCodigo(Integer.parseInt(t1.getText()));
						ven.setNombre(t2.getText());
						ven.setGenero(t3.getText());
						ven.setCaja(Integer.parseInt(t4.getText()));
						ven.setVentas(Integer.parseInt(t5.getText()));
						ven.setConstrasenia(t6.getText());
						VendedoresDAO vdao = new VendedoresDAO();
						vdao.Modificar(ven);
						crear.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "llene todos los campos");
					}

				}
			};

			b1.addActionListener(ingresar);

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

		VendedoresDAO vdao = new VendedoresDAO();
		List<Vendedor> vendedoresList = new ArrayList<>();
		vendedoresList = vdao.listarVendedores();

		for (Vendedor ven : vendedoresList) {
			documento.add(new Paragraph("id: " + ven.getIdVendedor() + ""));
			documento.add(new Paragraph("codigo: " + ven.getCodigo() + ""));
			documento.add(new Paragraph("nombre: " + ven.getNombre()));
			documento.add(new Paragraph("genero: " + ven.getGenero()));
			documento.add(new Paragraph("caja: " + ven.getCaja()));
			documento.add(new Paragraph("ventas: " + ven.getVentas()));
			documento.add(new Paragraph("password: " + ven.getConstrasenia()));
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
