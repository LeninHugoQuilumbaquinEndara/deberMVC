package estudiantemvc;


import modelo.Controlador;
import modelo.Estudiante;
import modelo.TransaccionesBD1;
import vista.VistaEstudiante;

public class EstudianteMvc {

    public static void main(String[] args) {
        VistaEstudiante vista = new VistaEstudiante();
        TransaccionesBD1 modelo = new TransaccionesBD1();
        Estudiante estudiante = new Estudiante();
        Controlador controlador = new Controlador(vista, modelo, estudiante);
        controlador.iniciar();
        
        vista.setVisible(true);
    }

}
