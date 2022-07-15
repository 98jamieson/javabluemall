package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexion.Conexion;

import Model.Vendedor;

public class VendedoresDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection cn;
	Conexion acceso = new Conexion();

	public List<Vendedor> listarVendedores() {

		String query = "select *from vendedor";

		List<Vendedor> vendedoresList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vendedor ven = new Vendedor();
				ven.setIdVendedor(rs.getInt(1));
				ven.setNombre(rs.getString(2));
				ven.setCaja(rs.getInt(3));
				ven.setVentas(rs.getInt(4));
				ven.setGenero(rs.getString(5));
				ven.setConstrasenia(rs.getString(6));
				ven.setCodigo(rs.getInt(7));
				vendedoresList.add(ven);
			}
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return vendedoresList;

	}

	public void ingresarVendedor(Vendedor ven) throws SQLException {

		String sql;
		sql = "INSERT INTO vendedor(codigo,nombre,genero,caja,ventas,password)" + "VALUES (?,?,?,?,?,?)";

		cn = acceso.conectar();

		PreparedStatement preparedStmt = cn.prepareStatement(sql);
		preparedStmt.setInt(1, ven.getCodigo());
		preparedStmt.setString(2, ven.getNombre());
		preparedStmt.setString(3, ven.getGenero());
		preparedStmt.setInt(4, ven.getCaja());
		preparedStmt.setInt(5, ven.getVentas());
		preparedStmt.setString(6, ven.getConstrasenia());

		preparedStmt.execute();
		cn.close();

	}

	public Vendedor buscarVendedor(int v) {
		String query = "select *from vendedor where idVendedor=" + v + ";";
		Vendedor ven = new Vendedor();

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				ven.setIdVendedor(rs.getInt(1));
				ven.setNombre(rs.getString(2));
				ven.setCaja(rs.getInt(3));
				ven.setVentas(rs.getInt(4));
				ven.setGenero(rs.getString(5));
				ven.setConstrasenia(rs.getString(6));
				ven.setCodigo(rs.getInt(7));
			}

			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return ven;

	}

	public void Modificar(Vendedor ven) {
		String sql;
		sql = "UPDATE vendedor SET codigo=?, nombre=?,genero=?,caja=?,ventas=?, password=? WHERE idVendedor=?";

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(sql);

			ps.setInt(1, ven.getCodigo());
			ps.setString(2, ven.getNombre());
			ps.setString(3, ven.getGenero());
			ps.setInt(4, ven.getCaja());
			ps.setInt(5, ven.getVentas());
			ps.setString(6, ven.getConstrasenia());
			ps.setInt(7, ven.getIdVendedor());
			if (ps.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Actualizado");
			}

			cn.close();

		} catch (Exception ex) {

		}

	}

	public void Eliminar(Vendedor ven) {
		String sql;
		sql = "DELETE FROM vendedor WHERE idVendedor=?";

		try {

			cn = acceso.conectar();

			ps = cn.prepareStatement(sql);
			ps.setInt(1, ven.getIdVendedor());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dato eliminado");

		} catch (Exception e) {

		}

	}

}
