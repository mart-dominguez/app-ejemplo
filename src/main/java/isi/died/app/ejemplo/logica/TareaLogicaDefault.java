package isi.died.app.ejemplo.logica;

import java.util.List; 

import isi.died.app.ejemplo.dao.TareaDao;
import isi.died.app.ejemplo.dao.TareaDaoH2;
import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public class TareaLogicaDefault implements TareaLogica {

	private TareaDao tareaDao;
	
	public TareaLogicaDefault() {
		this.tareaDao = new TareaDaoH2();
	}

	@Override
	public Tarea guardar(Tarea p) {
		// si tiene ID entonces actualizar
		if(p.getId()!= null && p.getId()>0) {
			return this.tareaDao.actualizar(p);
		} else {
			return this.tareaDao.crear(p);			
		}
	}

	@Override
	public void borrar(Integer id) {
		this.tareaDao.borrar(id);
	}

	@Override
	public Tarea buscar(Integer id) {
		return this.tareaDao.buscar(id);
	}

	@Override
	public List<Tarea> buscar() {
		return this.tareaDao.buscarTodos();
	}


}
