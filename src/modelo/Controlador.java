
package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.VistaEstudiante;


public class Controlador implements ActionListener {
    VistaEstudiante vista;
    TransaccionesBD1 modelo;
    Estudiante estudiante;

    public Controlador(VistaEstudiante vista, TransaccionesBD1 modelo, Estudiante estudiante) {
        this.vista = vista;
        this.modelo = modelo;
        this.estudiante = estudiante;
        vista.btnInsertar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnAnterior.addActionListener(this);
        vista.btnSiguiente.addActionListener(this);
    }
    public void iniciar()
    {
        vista.setTitle("Estudiante");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnInsertar)
        {
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            if(modelo.insertar(estudiante)){
                JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null,"No se inserto el registro");
            }
        }
        if(e.getSource()==vista.btnBuscar){
                estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
                if( modelo.buscar(estudiante)){
                    vista.txtNombre.setText(estudiante.getNombre());
                    vista.txtNota.setText(String.valueOf(estudiante.getNota()));
                    vista.txtBuscar.setText(String.valueOf(estudiante.getCodigo()));
                    vista.cbGenero.setSelectedItem(estudiante.getGenero());
                    vista.cbMateria.setSelectedItem(estudiante.getMateria());
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun estudiante");
                    limpiar();
                }
        }
                 if(e.getSource()==vista.btnModificar){
                     estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
                     estudiante.setNombre(vista.txtNombre.getText());
                     estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
                     estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
                     estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
                if( modelo.modificar(estudiante)){
                   JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();                   
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo Modificar");
                    limpiar();
                }
                }
                 
                 if(e.getSource()==vista.btnEliminar){
  estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
                     if(modelo.eliminar(estudiante)){
                       JOptionPane.showMessageDialog(null, "Se ha eliminado el estudiante");
                limpiar();  
                     }else{
                         JOptionPane.showMessageDialog(null, "No se ha podido eliminar al estudiante");
                limpiar();
                     }
                 }
                 if(e.getSource()==vista.btnLimpiar){
                     limpiar();
                 }
                 if(e.getSource()==vista.btnAnterior){
                    if(modelo.anterior(estudiante)){
                        vista.txtBuscar.setText(String.valueOf(estudiante.getCodigo()));
                        vista.txtNombre.setText(estudiante.getNombre());
                        vista.cbMateria.setSelectedItem(estudiante.getMateria());
                        vista.cbGenero.setSelectedItem(estudiante.getGenero());
                        vista.txtNota.setText(String.valueOf(estudiante.getNota()));  
                    }
                 }
                 if(e.getSource()==vista.btnSiguiente){
                     if(modelo.siguiente(estudiante)){
                         vista.txtBuscar.setText(String.valueOf(estudiante.getCodigo()));
                         vista.txtNombre.setText(estudiante.getNombre());
                         vista.cbMateria.setSelectedItem(estudiante.getMateria());
                         vista.cbGenero.setSelectedItem(estudiante.getGenero());
                         vista.txtNota.setText(String.valueOf(estudiante.getNota()));
                     }
                 }
    }
    
    public void limpiar(){
        vista.txtNombre.setText(null);
        vista.txtNota.setText(null);
        vista.cbGenero.setSelectedIndex(0);
        vista.cbMateria.setSelectedIndex(0);
        vista.txtBuscar.setText(null);
}
}
