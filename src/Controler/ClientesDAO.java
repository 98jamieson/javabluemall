package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Model.Cliente;

public class ClientesDAO {

	PreparedStatement ps;
	ResultSet rs;
	Connection cn;
	Conexion acceso = new Conexion();

	public List<Cliente> listarCliente() {

		String query = "select *from cliente";

		List<Cliente> clienteList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setIdCliente(rs.getInt(1));
				cli.setCodigo(rs.getInt(2));
				cli.setNit(rs.getString(3));
				cli.setCorreo(rs.getString(4));
				cli.setGenero(rs.getString(5));
				cli.setNombre(rs.getString(6));
				clienteList.add(cli);
			}
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return clienteList;

	}

	public void IngresarCliente(Cliente cli) throws SQLException {

		String sql;
		sql = "INSERT INTO cliente(codigo,nombre,correo,genero,nit)" + "VALUES (?,?,?,?,?)";

		cn = acceso.conectar();

		PreparedStatement preparedStmt = cn.prepareStatement(sql);
		preparedStmt.setInt(1, cli.getCodigo());
		preparedStmt.setString(2, cli.getNombre());
		preparedStmt.setString(3, cli.getCorreo());
		preparedStmt.setString(4, cli.getGenero());
		preparedStmt.setString(5, cli.getNit());

		preparedStmt.execute();
		cn.close();

	}

	public Cliente buscarCliente(int c) {
		String query = "select *from cliente where idCliente=" + c + ";";
		Cliente cli = new Cliente();

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				cli.setIdCliente(rs.getInt(1));
				cli.setCodigo(rs.getInt(2));
				cli.setNit(rs.getString(3));
				cli.setCorreo(rs.getString(4));
				cli.setGenero(rs.getString(5));
				cli.setNombre(rs.getString(6));

			}

			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return cli;

	}

	public void Modificar(Cliente cli) {
		String sql;
		sql = "UPDATE cliente SET codigo=?, nombre=?,nit=?,correo=?,genero=? WHERE idCliente=?";

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(sql);

			ps.setInt(1, cli.getCodigo());
			ps.setString(2, cli.getNombre());
			ps.setString(3, cli.getNit());
			ps.setString(4, cli.getCorreo());
			ps.setString(5, cli.getGenero());
			ps.setInt(6, cli.getIdCliente());
			if (ps.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Actualizado");
			}

			cn.close();

		} catch (Exception ex) {

		}

	}

	public void Eliminar(Cliente cli) {
		String sql;
		sql = "DELETE FROM cliente WHERE idCliente=?";

		try {

			cn = acceso.conectar();

			ps = cn.prepareStatement(sql);
			ps.setInt(1, cli.getIdCliente());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dato eliminado");

		} catch (Exception e) {

		}

	}

}
