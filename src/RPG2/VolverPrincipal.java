package RPG2;

import Clases.Guerrero;
import Clases.Mago;
import Clases.Sacerdote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VolverPrincipal implements ActionListener {
    JFrame victoria;
    JFrame jframePrincipal;
    int tipo;
    Guerrero guerrero;
    Mago mago;
    Sacerdote sacerdote;

    public VolverPrincipal(JFrame victoria, JFrame jframePrincipal, int tipo, Guerrero guerrero, Mago mago, Sacerdote sacerdote) {
        this.victoria = victoria;
        this.jframePrincipal = jframePrincipal;
        this.tipo = tipo;
        this.guerrero = guerrero;
        this.mago = mago;
        this.sacerdote = sacerdote;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        guardarEnArchivo();
        victoria.dispose();
        jframePrincipal.setVisible(true);
    }

    private void guardarEnArchivo() {
        String nombreJugador = JOptionPane.showInputDialog(null, "Por favor, ingresa tu nombre:");
        if (nombreJugador != null && !nombreJugador.isEmpty()) {
            String clasePersonaje = obtenerClasePersonaje();
            int oroConseguido = obtenerOroConseguido();
            int vidaActual = obtenerVidaActual();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Clasificacion.txt", true))) {
                writer.write(nombreJugador + ";" + clasePersonaje + ";" + oroConseguido + ";" + vidaActual);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar un nombre para guardar la puntuación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerClasePersonaje() {
        return switch (tipo) {
            case 1 -> "Guerrero";
            case 2 -> "Mago";
            case 3 -> "Sacerdote";
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
    }

    private int obtenerOroConseguido() {
        return switch (tipo) {
            case 1 -> guerrero.getOro();
            case 2 -> mago.getOro();
            case 3 -> sacerdote.getOro();
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
    }

    private int obtenerVidaActual() {
        return switch (tipo) {
            case 1 -> guerrero.getVidas();
            case 2 -> mago.getVidas();
            case 3 -> sacerdote.getVidas();
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
    }
}