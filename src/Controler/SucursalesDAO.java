package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Model.Sucursal;


public class SucursalesDAO {

	PreparedStatement ps;
	ResultSet rs;
	Connection cn;
	Conexion acceso = new Conexion();

	public List<Sucursal> listarSucursales() {

		String query = "select *from sucursal";

		List<Sucursal> sucursalList = new ArrayList<>();
		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sucursal su = new Sucursal();
				su.setIdSucursal(rs.getInt(1));
				su.setCodigo(rs.getInt(2));
				su.setNombre(rs.getString(3));
				su.setDireccion(rs.getString(4));
				su.setCorreo(rs.getString(5));
				su.setTelefono(rs.getString(6));
				sucursalList.add(su);
			}
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return sucursalList;

	}

	public void IngresarSucursal(Sucursal su) throws SQLException {

		String sql;
		sql = "INSERT INTO sucursal(codigo,nombre,direccion,correo,telefono)" + "VALUES (?,?,?,?,?)";

		cn = acceso.conectar();
		PreparedStatement preparedStmt = cn.prepareStatement(sql);
		preparedStmt.setInt(1, su.getCodigo());
		preparedStmt.setString(2, su.getNombre());
		preparedStmt.setString(3, su.getDireccion());
		preparedStmt.setString(4, su.getCorreo());
		preparedStmt.setString(5, su.getTelefono());
		preparedStmt.execute();
		cn.close();
		
	}

	public Sucursal buscarSucursal(int u) {
		String query = "select *from sucursal where idSucusal=" + u + ";";
		Sucursal su = new Sucursal();

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				su.setIdSucursal(rs.getInt(1));
				su.setCodigo(rs.getInt(2));
				su.setNombre(rs.getString(3));
				su.setDireccion(rs.getString(4));
				su.setCorreo(rs.getString(5));
				su.setTelefono(rs.getString(6));
				System.out.println(su.getNombre());
				
			}
			

			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return su;

	}

	public void Modificar(Sucursal suc) {
		String sql;
		sql = "UPDATE sucursal SET codigo=?, nombre=?,direccion=?,correo=?,telefono=? WHERE idSucusal=?";

		try {
			cn = acceso.conectar();
			ps = cn.prepareStatement(sql);
			ps.setInt(1, suc.getCodigo());
			ps.setString(2, suc.getNombre());
			ps.setString(3, suc.getDireccion());
			ps.setString(4, suc.getCorreo());
			ps.setString(5, suc.getTelefono());
			ps.setInt(6, suc.getIdSucursal());
			if (ps.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Actualizado");
			}

			cn.close();

		} catch (Exception ex) {

		}

	}

	
	public void Eliminar(Sucursal su) {
		String sql;
		sql = "DELETE FROM sucursal WHERE idSucusal=?";

		try {

			cn = acceso.conectar();

			ps = cn.prepareStatement(sql);
			ps.setInt(1, su.getIdSucursal());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dato eliminado");

		} catch (Exception e) {

		}

	}

	
	
	
	
	
}
