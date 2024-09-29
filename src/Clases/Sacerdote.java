package Clases;

import java.awt.*;

public class Sacerdote extends  Personaje{
    boolean TieneMitra;
    public Sacerdote(int vidas, int oro, String rutaFoto, int velocidad,boolean tiene) {
        super(vidas, oro, rutaFoto, velocidad);
        this.TieneMitra = tiene;
    }

    public boolean isTieneMitra() {
        return TieneMitra;
    }

    public void setTieneMitra(boolean tieneMitra) {
        TieneMitra = tieneMitra;
    }
}
