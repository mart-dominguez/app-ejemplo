package isi.died.app.ejemplo.logica;

import java.util.List;

import isi.died.app.ejemplo.dao.ProyectoDao;
import isi.died.app.ejemplo.dao.ProyectoDaoH2;
import isi.died.app.ejemplo.dao.TareaDao;
import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public class ProyectoLogicaDefault implements ProyectoLogica {

	private ProyectoDao proyectoDao;	
	private TareaLogica tareaLogica;

	public ProyectoLogicaDefault() {
		this.proyectoDao = new ProyectoDaoH2();
		this.tareaLogica = new TareaLogicaDefault();
	}

	@Override
	public Proyecto guardar(Proyecto p) {
		// si tiene ID entonces actualizar
		if(p.getId()!= null && p.getId()>0) {
			return this.proyectoDao.actualizar(p);
		} else {
			return this.proyectoDao.crear(p);			
		}
	}

	@Override
	public void agregarTarea(Proyecto p, Tarea t) {
		t.setProyecto(p);
		this.tareaLogica.guardar(t);
	}

	@Override
	public void borrar(Integer id) {
		this.proyectoDao.borrar(id);
	}

	@Override
	public Proyecto buscar(Integer id) {
		return this.proyectoDao.buscar(id);
	}

	@Override
	public List<Proyecto> buscar() {
		return this.proyectoDao.buscarTodos();
	}


}
