package reportes;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class validacion_ruta {
	
	public static File obtenerRutaArchivo(String nombreSugerido) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Guardar reporte PDF");
	    fileChooser.setSelectedFile(new File(nombreSugerido));

	    while (true) {
	        int seleccion = fileChooser.showSaveDialog(null);
	        if (seleccion != JFileChooser.APPROVE_OPTION) {
	            return null; // Cancelado por el usuario
	        }

	        File archivoSeleccionado = fileChooser.getSelectedFile();

	        if (archivoSeleccionado.exists()) {
	            int opcion = JOptionPane.showConfirmDialog(null,
	                    "Ya existe un archivo con ese nombre.\n¿Desea sobrescribirlo?",
	                    "Archivo existente", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

	            if (opcion == JOptionPane.YES_OPTION) {
	                return archivoSeleccionado; // Sobrescribe
	            } else if (opcion == JOptionPane.NO_OPTION) {
	                continue; // Reintentar con otro nombre
	            } else {
	                return null; // Cancelar
	            }
	        } else {
	            return archivoSeleccionado;
	        }
	    }
	}


}
