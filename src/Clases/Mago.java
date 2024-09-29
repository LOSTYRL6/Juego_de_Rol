package Clases;

import javax.swing.*;
import java.awt.*;

public class Mago extends  Personaje{
    boolean TienePocion;
    public Mago(int vidas, int oro, String rutaFoto, int velocidad,boolean tiene) {
        super(vidas, oro, rutaFoto, velocidad);
        this.TienePocion = true;
    }

    public boolean isTienePocion() {
        return TienePocion;
    }

    public void setTienePocion(boolean tienePocion) {
        TienePocion = tienePocion;
    }
}
