package Clases;

import java.awt.*;

public class Guerrero extends Personaje{
    boolean TieneEspada ;
    public Guerrero(int vidas, int oro, String rutaFoto, int velocidad,boolean tiene) {
        super(vidas, oro, rutaFoto, velocidad);
        this.TieneEspada = tiene;
    }

    public boolean isTieneEspada() {
        return TieneEspada;
    }

    public void setTieneEspada(boolean tieneEspada) {
        TieneEspada = tieneEspada;
    }
}
