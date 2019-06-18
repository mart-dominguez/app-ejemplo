package isi.died.app.ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public class ProyectoDaoH2 implements ProyectoDao {

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS Proyecto (ID IDENTITY NOT NULL PRIMARY KEY, NOMBRE VARCHAR(80))";
	private static final String SQL_INSERT = "INSERT INTO Proyecto (NOMBRE) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE Proyecto SET NOMBRE =? WHERE ID = ?";
	private static final String SQL_DELETE = "DELETE FROM Proyecto WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, NOMBRE FROM PROYECTO ";
	
	public ProyectoDaoH2() {
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Proyecto crear(Proyecto p) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setString(1, p.getNombre());
					pst.executeUpdate();
					try(ResultSet rs = pst.getGeneratedKeys()){
						if(rs.next()) {
							p.setId(rs.getInt(1));
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return p;
	}


	public Proyecto actualizar(Proyecto p) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setString(1, p.getNombre());
				pst.setInt(2, p.getId());
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return p;
	}

	@Override
	public void borrar(Integer id) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, id);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Proyecto buscar(Integer id) {
		Proyecto resultado = null;
		String sqlById = SQL_SELECT + " WHERE ID = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, id);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Proyecto();
						resultado.setId(id);
						resultado.setNombre(rs.getString("NOMBRE"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Proyecto> buscarTodos() {
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Proyecto aux = new Proyecto();
						aux.setId(rs.getInt("ID"));
						aux.setNombre(rs.getString("NOMBRE"));
						resultado.add(aux);
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;	
	}

}
