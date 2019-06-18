package isi.died.app.ejemplo.logica;

import java.util.List;

import isi.died.app.ejemplo.modelo.Tarea;

public interface TareaLogica {

	public Tarea crear(String nombreTarea,Integer duracion,Integer idProyecto);
	public Tarea actualizar(Integer id,String nombreTarea,Integer duracion,Integer idProyecto);
	public void borrar(Integer id);
	public Tarea buscar(Integer id);
	public List<Tarea> buscar();
	public List<Tarea> buscarPorProyecto(Integer idProyecto);
}
