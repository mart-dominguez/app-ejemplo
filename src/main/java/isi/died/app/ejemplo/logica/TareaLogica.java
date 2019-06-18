package isi.died.app.ejemplo.logica;

import java.util.List;

import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public interface TareaLogica {

	public Tarea guardar(Tarea p);
	public void borrar(Integer id);
	public Tarea buscar(Integer id);
	public List<Tarea> buscar();
}
