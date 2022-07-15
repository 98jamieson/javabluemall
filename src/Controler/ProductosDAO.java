package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Model.Producto;

public class ProductosDAO {

	PreparedStatement ps;
	ResultSet rs;
	Connection cn;
	Conexion acceso = new Conexion();

	public List<Producto> listarProducto() {

		String query = "select *from producto";

		List<Producto> productList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto pro = new Producto();
				pro.setIdProducto(rs.getInt(1));
				pro.setCodigo(rs.getInt(2));
				pro.setNombre(rs.getString(3));
				pro.setDescripcion(rs.getString(4));
				pro.setCantidad(rs.getInt(5));
				pro.setPrecio(rs.getDouble(6));
				productList.add(pro);
			}
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return productList;

	}

	public void IngresarProducto(Producto pro) throws SQLException {

		String sql;
		sql = "INSERT INTO producto(codigo,nombre,descripcion,cantidad,precio)" + "VALUES (?,?,?,?,?)";

		cn = acceso.conectar();

		PreparedStatement preparedStmt = cn.prepareStatement(sql);
		preparedStmt.setInt(1, pro.getCodigo());
		preparedStmt.setString(2, pro.getNombre());
		preparedStmt.setString(3, pro.getDescripcion());
		preparedStmt.setInt(4, pro.getCantidad());
		preparedStmt.setDouble(5, pro.getPrecio());

		preparedStmt.execute();
		cn.close();

	}

	public Producto buscarProducto(int p) {
		String query = "select *from producto where idProducto=" + p + ";";
		Producto pro = new Producto();

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				pro.setIdProducto(rs.getInt(1));
				pro.setCodigo(rs.getInt(2));
				pro.setNombre(rs.getString(3));
				pro.setDescripcion(rs.getString(4));
				pro.setCantidad(rs.getInt(5));
				pro.setPrecio(rs.getDouble(6));

			}

			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return pro;

	}

	public void Modificar(Producto pro) {
		String sql;
		sql = "UPDATE producto SET codigo=?, nombre=?,descripcion=?,cantidad=?,precio=? WHERE idProducto=?";

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(sql);
			
			ps.setInt(1, pro.getCodigo());
			ps.setString(2, pro.getNombre());
			ps.setString(3, pro.getDescripcion());
			ps.setInt(4, pro.getCantidad());
			ps.setDouble(5, pro.getPrecio());
			ps.setInt(6, pro.getIdProducto());
			if (ps.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Actualizado");
			}

			cn.close();

		} catch (Exception ex) {

		}

	}

	public void Eliminar(Producto pro) {
		String sql;
		sql = "DELETE FROM producto WHERE idProducto=?";

		try {

			cn = acceso.conectar();

			ps = cn.prepareStatement(sql);
			ps.setInt(1,pro.getIdProducto());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dato eliminado");

		} catch (Exception e) {

		}

	}
}
