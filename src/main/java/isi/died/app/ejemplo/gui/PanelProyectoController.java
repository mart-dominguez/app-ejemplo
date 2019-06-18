package isi.died.app.ejemplo.gui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import isi.died.app.ejemplo.logica.ProyectoLogica;
import isi.died.app.ejemplo.logica.ProyectoLogicaDefault;
import isi.died.app.ejemplo.modelo.Proyecto;

public class PanelProyectoController {
	
	private PanelProyecto panel;
	private ProyectoLogica logica;
	
	public PanelProyectoController(PanelProyecto listener) {
		this.panel = listener;
		this.logica = new ProyectoLogicaDefault();
	}
	
	public void crearProyecto(String nombre) {
			Runnable r = () -> {
				Proyecto p = new Proyecto();
				p.setNombre(nombre);
				this.logica.guardar(p);
				List<Proyecto> lista = logica.buscar();
				try {
					SwingUtilities.invokeAndWait(() -> {
						panel.actualizarDatosTabla(lista);
						JOptionPane.showMessageDialog((Component) panel, "Proyecto Creado");
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread hilo = new Thread(r);
			
			hilo.start();
	}
	
	public void actualizarProyecto(Integer id , String nombre) {
		Runnable r = () -> {
			Proyecto p = new Proyecto();
			p.setNombre(nombre);
			p.setId(id);
			this.logica.guardar(p);
			List<Proyecto> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista);
					JOptionPane.showMessageDialog((Component) panel, "Proyecto "+ nombre +" Actualizado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}

	public void borrarProyecto(Integer id) {
		Runnable r = () -> {
			this.logica.borrar(id);
			List<Proyecto> lista = logica.buscar();
			try {
				SwingUtilities.invokeAndWait(() -> {
					panel.actualizarDatosTabla(lista );
					JOptionPane.showMessageDialog((Component) panel, "Proyecto borrado");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
}
