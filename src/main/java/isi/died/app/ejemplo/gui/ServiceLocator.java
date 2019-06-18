package isi.died.app.ejemplo.gui;

import java.awt.Color;
import java.awt.Font;

public class ServiceLocator {

	private static ServiceLocator _INSTANCIA = null;
	private PanelProyecto panelProyecto = null;
	private PanelTarea panelTarea = null;
	private GrafoPanel panelGrafo = null;
	public static final Font FUENTE_TITULO = new Font("Calibri",Font.BOLD,18);
	public static final Color COLOR_TITULO = new Color(5,85,244);
	
	private ServiceLocator() {
	}
	
	public static ServiceLocator getInstance() {
		if(_INSTANCIA == null ) _INSTANCIA = new ServiceLocator();
		return _INSTANCIA;
	}

	public PanelProyecto getPanelProyecto() {
		if(this.panelProyecto == null) this.panelProyecto = new PanelProyecto();
		return panelProyecto;
	}
	
	public PanelTarea getPanelTarea() {
		if(this.panelTarea == null) this.panelTarea = new PanelTarea();
		return panelTarea;
	}
	
	public GrafoPanel getPanelGrafo() {
		if(this.panelGrafo == null) this.panelGrafo = new GrafoPanel();
		return panelGrafo;
	}	  
}
