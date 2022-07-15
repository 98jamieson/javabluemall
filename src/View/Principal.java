package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Principal {

	JPanel Psucursales = new JPanel();
	JPanel Pclientes = new JPanel();
	JPanel Pvendedores = new JPanel();
	JPanel Pproductos = new JPanel();

	frmSucursales sm = new frmSucursales();
	frmClientes cl = new frmClientes();
	frmVendedores ve = new frmVendedores();
	frmProducto pro = new frmProducto();

	JFrame FAdministrador = new JFrame();
	JTabbedPane pestañas = new JTabbedPane();

	public static void main(String[] args) {

		Principal pr = new Principal();
		pr.valoresIniciales();

	}

	public void valoresIniciales() {

		FAdministrador.setTitle("Modulo Administrador");
		FAdministrador.setLocationRelativeTo(null);
		FAdministrador.setBounds(90, 20, 1200, 700);

		FAdministrador.setVisible(true);
		FAdministrador.setResizable(false);
		FAdministrador.add(pestañas);

		Psucursales.setLayout(null);
		Pclientes.setLayout(null);
		Pvendedores.setLayout(null);
		Pproductos.setLayout(null);

		pestañas.addTab("Sucursales", Psucursales);
		pestañas.addTab("Clientes", Pclientes);
		pestañas.addTab("Vendedores", Pvendedores);
		pestañas.addTab("Productos", Pproductos);

		sm.ejecutar();
		Psucursales.add(sm.crear);
		Psucursales.add(sm.editar);
		Psucursales.add(sm.eliminar);
		Psucursales.add(sm.pdf);
		Psucursales.add(sm.sp);

		pro.ejecutar();
		Pproductos.add(pro.crear);
		// Pproductos.add(pro.carga);
		Pproductos.add(pro.editar);
		Pproductos.add(pro.eliminar);
		Pproductos.add(pro.pdf);
		Pproductos.add(pro.sp);

		cl.ejecutar();
		Pclientes.add(cl.crear);
		Pclientes.add(cl.editar);
		Pclientes.add(cl.eliminar);
		Pclientes.add(cl.pdf);
		Pclientes.add(cl.sp);
		
		ve.ejecutar();
		Pvendedores.add(ve.crear);
		Pvendedores.add(ve.editar);
		Pvendedores.add(ve.eliminar);
		Pvendedores.add(ve.pdf);
		Pvendedores.add(ve.sp);
		

	}

	public void pantallaAdmin() {

	}

}
