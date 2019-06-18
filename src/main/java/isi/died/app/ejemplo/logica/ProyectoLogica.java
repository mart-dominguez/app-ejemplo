package isi.died.app.ejemplo.logica;

import java.util.List;

import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public interface ProyectoLogica {

	public Proyecto guardar(Proyecto p);
	public void agregarTarea(Proyecto p,Tarea t);
	public void borrar(Integer id);
	public Proyecto buscar(Integer id);
	public List<Proyecto> buscar();
}
