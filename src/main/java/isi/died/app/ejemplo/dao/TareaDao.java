package isi.died.app.ejemplo.dao;

import java.util.List;

import isi.died.app.ejemplo.modelo.Tarea;

public interface TareaDao {
	public Tarea crear(Tarea t);

	public Tarea actualizar(Tarea t);

	public void borrar(Integer id);

	public Tarea buscar(Integer id);

	public List<Tarea> buscarTodos();
	
	
}
