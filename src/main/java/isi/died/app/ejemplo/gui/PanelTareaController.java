package isi.died.app.ejemplo.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.app.ejemplo.logica.ProyectoLogica;
import isi.died.app.ejemplo.logica.ProyectoLogicaDefault;
import isi.died.app.ejemplo.logica.TareaLogica;
import isi.died.app.ejemplo.logica.TareaLogicaDefault;
import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public class PanelTareaController {
	
	private PanelTarea panel;
	private TareaLogica logica;
	private ProyectoLogica logicaProyecto;
	
	public PanelTareaController(PanelTarea listener) {
		this.panel = listener;
		this.logica = new TareaLogicaDefault();
		this.logicaProyecto = new ProyectoLogicaDefault(); 
	}
	
	public void crearTarea(String nombre,Integer duracion,Proyecto pry) {
			Runnable r = () -> {
				Tarea p = new Tarea();
				p.setDescripcion(nombre);
				p.setDuracion(duracion);
				p.setProyecto(pry);
				p = this.logica.guardar(p);
				List<Tarea> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Tarea Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarTarea(Integer id , String nombre,Integer duracion,Proyecto pry) {
		Runnable r = () -> {
			Tarea p = new Tarea();
			p.setDescripcion(nombre);
			p.setDuracion(duracion);
			p.setProyecto(pry);
			p.setId(id);
			this.logica.guardar(p);
			List<Tarea> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Tarea "+ nombre +" Actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarTarea(Integer id) {
		Runnable r = () -> {
			this.logica.borrar(id);
			List<Tarea> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Tarea borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
	
	public void cargarComboProyectos(JComboBox<Proyecto> combo){
	Runnable r = () -> {
			List<Proyecto> lista = this.logicaProyecto.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					for(Proyecto p : lista){
						combo.addItem(p);
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
}
