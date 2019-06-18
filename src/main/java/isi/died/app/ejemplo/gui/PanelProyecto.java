package isi.died.app.ejemplo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import isi.died.app.ejemplo.gui.util.GenericTableColumn;
import isi.died.app.ejemplo.gui.util.GenericTableModel;
import isi.died.app.ejemplo.modelo.Proyecto;

public class PanelProyecto extends JPanel {

	private JLabel lblNombreProyecto;
	private JLabel lblTituloPanel;
	private JTextField nombreProyecto;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaProyectos;
	GenericTableModel<Proyecto> gtm ;
	
	private Integer idProyectoSeleccionado;
	
	
	private PanelProyectoController controller;

	public PanelProyecto() {
		super();
		controller = new PanelProyectoController(this);
		this.armar();
		this.configurarEventos();
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(450,250);
    }

    public void armar() {
    	this.setLayout(new GridBagLayout());
    	
    	int fila = 0;
    	int col = 0;
    	
    	GridBagConstraints c = new GridBagConstraints();    	

    	this.lblTituloPanel = new JLabel("Gestion de proyectos");
    	this.lblTituloPanel.setFont(ServiceLocator.FUENTE_TITULO);
    	this.lblTituloPanel.setForeground(ServiceLocator.COLOR_TITULO);
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=3;
    	c.anchor=GridBagConstraints.CENTER;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblTituloPanel,c);
    	// reinicio variables de celda
    	col =0;
    	fila++;

    	this.lblNombreProyecto = new JLabel("Nombre: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblNombreProyecto,c);
    	
    	this.nombreProyecto = new JTextField(30);
    	this.nombreProyecto.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(nombreProyecto,c);
    	
    	this.btnGuardar = new JButton("Guardar");
    	this.btnGuardar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.0;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnGuardar,c);
    	
    	this.btnCancelar = new JButton("Cancelar");
    	this.btnCancelar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.2;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnCancelar,c);

    	
    	// reinicio variables de celda
    	c.gridwidth=col;
    	col =0;
    	fila++;
    	
    	c.gridx=col++;
    	c.gridy=fila;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=1.0;
    	c.weightx=1.0;
    	
    	this.gtm = crearModeloTabla();
    	this.tablaProyectos = new JTable(this.gtm);
    	this.tablaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new  JScrollPane(this.tablaProyectos);
    	this.add(scrollPane,c);
    	
    	col =0;
    	fila++;
    	JPanel panelBotones = new JPanel();

    	this.btnNuevo = new JButton("Nuevo");
    	this.btnEditar = new JButton("Editar");
    	this.btnBuscar = new JButton("Buscar");
    	this.btnBorrar = new JButton("Eliminar");
    	this.btnEditar.setEnabled(false);
    	this.btnBorrar.setEnabled(false);
    	panelBotones.add(btnBuscar);
    	panelBotones.add(btnNuevo);
    	panelBotones.add(btnEditar);
    	panelBotones.add(btnBorrar);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.NONE;
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=3;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=0.0;
    	c.weightx=1.0;
    	this.add(panelBotones,c);
    	
    }
    
    private void configurarEventos() {
    	this.btnGuardar.addActionListener( e -> {
    		if(idProyectoSeleccionado<=0) controller.crearProyecto(nombreProyecto.getText());
    		else controller.actualizarProyecto(idProyectoSeleccionado,nombreProyecto.getText());
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		nombreProyecto.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		nombreProyecto.setText("");
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombreProyecto.setEnabled(true);
    		btnNuevo.setEnabled(false);
    		nombreProyecto.setText("");
    		idProyectoSeleccionado=-1;
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		nombreProyecto.setEnabled(false);
    		nombreProyecto.setText("");
    		idProyectoSeleccionado=-1;
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    	});
    	
    	btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombreProyecto.setEnabled(true);
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borar proyecto", "Desea borrar el proyecto "+nombreProyecto.getText()+ "?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarProyecto(idProyectoSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaProyectos.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Proyecto aux = gtm.datos.get(lse.getFirstIndex());
	    		idProyectoSeleccionado = aux.getId();
	    		nombreProyecto.setText(aux.getNombre());
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
    		}
        });
    }
    
    
    private GenericTableModel<Proyecto> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Proyecto>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("Id" , "getId"));
    	lista.add(new GenericTableColumn("Nombre" , "getNombre"));
    	gtm.setColumnas(lista);
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Proyecto> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
