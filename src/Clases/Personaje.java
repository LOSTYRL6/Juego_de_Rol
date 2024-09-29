package Clases;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Personaje {
int vidas;
int oro;
Icon Foto;
int velocidad;

int x;
int y;

    public Personaje(int vidas, int oro, String rutaFoto, int velocidad) {
        this.vidas = vidas;
        this.oro = oro;
        this.Foto = cargarImagen(rutaFoto);
        this.velocidad = velocidad;
    }


    private Icon cargarImagen(String rutaFoto) {
        ImageIcon Logo = new ImageIcon(rutaFoto);
        return new ImageIcon(
                Logo.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT)
        );
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public Icon getFoto() {
        return Foto;
    }

    public void setFoto(Icon foto) {
        Foto = foto;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
