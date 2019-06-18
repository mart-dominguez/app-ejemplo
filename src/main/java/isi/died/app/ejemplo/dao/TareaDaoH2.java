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

public class TareaDaoH2 implements TareaDao {
	
	private ProyectoDao proyectoDaoH2;

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS TAREA (ID IDENTITY NOT NULL PRIMARY KEY, NOMBRE VARCHAR(80), DURACION INTEGER, ID_PROYECTO INTEGER)";
	private static final String SQL_INSERT = "INSERT INTO TAREA (NOMBRE,DURACION,ID_PROYECTO) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE TAREA SET NOMBRE =?, DURACION = ? ,ID_PROYECTO = ? WHERE ID = ?";
	private static final String SQL_DELETE = "DELETE FROM TAREA WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, NOMBRE, DURACION, ID_PROYECTO FROM TAREA";
	
	public TareaDaoH2() {
		this.proyectoDaoH2 = new ProyectoDaoH2();
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Tarea crear(Tarea p) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setString(1, p.getDescripcion());
					pst.setInt(2, p.getDuracion());
					pst.setInt(3, p.getProyecto().getId());
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


	public Tarea actualizar(Tarea p) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setString(1, p.getDescripcion());
				pst.setInt(2, p.getDuracion());
				pst.setInt(3, p.getProyecto().getId());
				pst.setInt(4, p.getId());
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
	public Tarea buscar(Integer id) {
		Tarea resultado = null;
		String sqlById = SQL_SELECT + " WHERE ID = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, id);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Tarea();
						resultado.setId(id);
						resultado.setDescripcion(rs.getString("NOMBRE"));
						resultado.setDuracion(rs.getInt("DURACION"));
						resultado.setProyecto(this.proyectoDaoH2.buscar(rs.getInt("ID_PROYECTO")));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Tarea> buscarTodos() {
		List<Tarea> resultado = new ArrayList<Tarea>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Tarea aux = new Tarea();
						aux.setId(rs.getInt("ID"));
						aux.setDescripcion(rs.getString("NOMBRE"));
						aux.setDuracion(rs.getInt("DURACION"));
						aux.setProyecto(this.proyectoDaoH2.buscar(rs.getInt("ID_PROYECTO")));
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
