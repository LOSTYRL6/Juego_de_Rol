package RPG2;

import Clases.Guerrero;
import Clases.Mago;
import Clases.Sacerdote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MainKeyListener extends KeyAdapter {
    int tipo;
    JLabel Personaje;
    JPanel panel;
    Guerrero guerrero;
    Mago mago;
    Sacerdote sacerdote;
    JLabel Objeto;
    JLabel Herramienta;
    int CantidadMoneda;
    JLabel Monedas;
    JLabel Monedas2;
    JLabel Vidas;
    JLabel salida;
    boolean objetoGenerado = false;
    boolean MonedasGenerado = false;
    private boolean timerActivo = false;
    private  boolean unaVEz = false;
    JFrame Frame;
    Timer Enemigo;
    JFrame JframePrincipal;

    public MainKeyListener(JLabel monigote, int tipo, JPanel panel, Guerrero guerrero, Mago mago, Sacerdote sacerdote, JLabel objeto, JLabel Monedas, JLabel vidas,JFrame frame,JFrame Jframeprincipal) {
        this.Personaje = monigote;
        this.tipo = tipo;
        this.panel = panel;
        this.guerrero = guerrero;
        this.mago = mago;
        this.sacerdote = sacerdote;
        this.Objeto = objeto;
        this.Monedas = Monedas;
        this.Vidas = vidas;
        this.Frame = frame;
        this.JframePrincipal = Jframeprincipal;
        Enemigo = new Timer(50, new AparecerEnemigo(panel,monigote, guerrero, mago, sacerdote,tipo,Objeto,Vidas,frame,JframePrincipal));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (!timerActivo){
            Enemigo.start();
            timerActivo = true;
        }
        String MovimientoUP, MovimientoDown, MovimientoRight, MovimientoLeft;
        int x, y;
        final int velocidad;
        if (tipo == 1) {
            MovimientoUP = "src/RPG2/DungeonImagen/warrior/warrior_up.gif";
            MovimientoDown = "src/RPG2/DungeonImagen/warrior/warrior_down.gif";
            MovimientoRight = "src/RPG2/DungeonImagen/warrior/warrior_right.gif";
            MovimientoLeft = "src/RPG2/DungeonImagen/warrior/warrior_left.gif";
            velocidad = guerrero.getVelocidad();
        } else if (tipo == 2) {
            MovimientoUP = "src/RPG2/DungeonImagen/wizard/wizard_up.gif";
            MovimientoDown = "src/RPG2/DungeonImagen/wizard/wizard_down.gif";
            MovimientoRight = "src/RPG2/DungeonImagen/wizard/wizard_right.gif";
            MovimientoLeft = "src/RPG2/DungeonImagen/wizard/wizard_left.gif";
            velocidad = mago.getVelocidad();
        } else {
            MovimientoUP = "src/RPG2/DungeonImagen/priest/priest_up.gif";
            MovimientoDown = "src/RPG2/DungeonImagen/priest/priest_down.gif";
            MovimientoRight = "src/RPG2/DungeonImagen/priest/priest_right.gif";
            MovimientoLeft = "src/RPG2/DungeonImagen/priest/priest_left.gif";
            velocidad = sacerdote.getVelocidad();
        }
        x = Personaje.getLocation().x;
        y = Personaje.getLocation().y;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                x += velocidad;
                ImageIcon Movimiento = new ImageIcon(MovimientoRight);
                Icon MovimientoFinal = new ImageIcon(
                        Movimiento.getImage().getScaledInstance(Personaje.getWidth(), Personaje.getHeight(), Image.SCALE_DEFAULT)
                );
                Personaje.setIcon(MovimientoFinal);
                if (x > panel.getWidth() - 125) {
                    x -= velocidad;
                }
            }
            case KeyEvent.VK_LEFT -> {
                x -= velocidad;
                ImageIcon Movimiento = new ImageIcon(MovimientoLeft);
                Icon MovimientoFinal = new ImageIcon(
                        Movimiento.getImage().getScaledInstance(Personaje.getWidth(), Personaje.getHeight(), Image.SCALE_DEFAULT)
                );
                Personaje.setIcon(MovimientoFinal);
                if (x < 70) {
                    x += velocidad;
                }
            }
            case KeyEvent.VK_UP -> {
                y -= velocidad;
                ImageIcon Movimiento = new ImageIcon(MovimientoUP);
                Icon MovimientoFinal = new ImageIcon(
                        Movimiento.getImage().getScaledInstance(Personaje.getWidth(), Personaje.getHeight(), Image.SCALE_DEFAULT)
                );
                Personaje.setIcon(MovimientoFinal);
                if (y < 75) {
                    y += velocidad;
                }
            }
            case KeyEvent.VK_DOWN -> {
                y += velocidad;
                ImageIcon Movimiento = new ImageIcon(MovimientoDown);
                Icon MovimientoFinal = new ImageIcon(
                        Movimiento.getImage().getScaledInstance(Personaje.getWidth(), Personaje.getHeight(), Image.SCALE_DEFAULT)
                );
                Personaje.setIcon(MovimientoFinal);
                if (y > panel.getHeight() - 130) {
                    y -= velocidad;
                }
            }
        }
        Personaje.setLocation(x, y);

        if (!guerrero.isTieneEspada() || !mago.isTienePocion() || !sacerdote.isTieneMitra()) {
            if (Herramienta == null || !objetoGenerado) {
                Herramienta = GenerarObjeto(panel, tipo);
                objetoGenerado = true;
            }
        }
        if (colisiona(Herramienta, Personaje)) {
            panel.remove(Herramienta);
            if (tipo == 1) {
                guerrero.setTieneEspada(true);
            } else if (tipo == 2) {
                mago.setTienePocion(true);
            } else {
                sacerdote.setTieneMitra(true);
            }
            Objeto.setVisible(true);
            objetoGenerado = false;
            panel.repaint();
        }
        if (Monedas2 == null || !MonedasGenerado){
            Monedas2 = GenerarMoneda(panel);
            MonedasGenerado = true;
        }
        if (colisiona(Monedas2, Personaje)) {
            if (tipo == 1){
                guerrero.setOro(guerrero.getOro() + 5);
                CantidadMoneda = guerrero.getOro();
                Monedas.setText("Monedas " + guerrero.getOro());
            } else if (tipo == 2) {
                CantidadMoneda = mago.getOro();
                mago.setOro(mago.getOro() + 5);
                Monedas.setText("Monedas " + mago.getOro());
            }else {
                CantidadMoneda = sacerdote.getOro();
                sacerdote.setOro(sacerdote.getOro() + 5);
                Monedas.setText("Monedas " + sacerdote.getOro());
            }

            panel.remove(Monedas2);
            MonedasGenerado = false;
            panel.repaint();
        }

        if (guerrero.getOro() >= 50|| mago.getOro() >= 50 || sacerdote.getOro() >=50){
            if (!unaVEz){
                salida = Salida(panel);
                unaVEz = true;
            }
            if (colisiona(salida,Personaje)){
                Enemigo.stop();
                JFrame Victoria = new JFrame("HAs ganado");
                Toolkit foto = Toolkit.getDefaultToolkit();
                Image Icon = foto.getImage("src/RPG2/DungeonImagen/politecnics.png");
                Victoria.setIconImage(Icon);
                Victoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Victoria.show();
                Frame.dispose();
                Victoria.setSize(600,900);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = Victoria.getSize();
                int a = (screenSize.width - frameSize.width) / 2;
                int b = (screenSize.height - frameSize.height) / 2;
                Victoria.setLocation(a, b);
                Victoria.setLayout(null);
                JPanel Contenido = new JPanel();
                Contenido.setPreferredSize(new Dimension(Victoria.getWidth(),Victoria.getHeight()));
                Contenido.setSize(Victoria.getWidth(),Victoria.getHeight());
                Contenido.setLayout(null);
                Contenido.setVisible(true);
                Victoria.setContentPane(Contenido);
                String classe;
                int OroConseguido;
                JLabel Titulo = new JLabel();
                if (tipo == 1){
                    classe = "Caballero";
                    OroConseguido = guerrero.getOro();
                } else if (tipo ==2) {
                    classe = "Mago";
                    OroConseguido = mago.getOro();
                }else {
                    classe = "Sacerdote";
                    OroConseguido = sacerdote.getOro();
                }
                Titulo.setText("TU Personaje es: " + classe);
                Titulo.setForeground(Color.white);
                Titulo.setSize(300,70);
                Titulo.setLocation(Contenido.getWidth()/2 - Titulo.getWidth()/2 , Contenido.getHeight()/2 + 100);
                Font font = Titulo.getFont();
                Titulo.setFont(new Font(font.getName(), Font.PLAIN, 20));
                Contenido.add(Titulo);
                JLabel PuntuacionMoneda = new JLabel();
                PuntuacionMoneda.setSize(300,70);
                PuntuacionMoneda.setForeground(Color.white);
                PuntuacionMoneda.setText("Cantidad de oro conseguido " + OroConseguido);
                PuntuacionMoneda.setLocation(Contenido.getWidth()/2 - Titulo.getWidth()/2 , Contenido.getHeight()/2 + 170);
                PuntuacionMoneda.setFont(new Font(font.getName(), Font.PLAIN, 20));
                Contenido.add(PuntuacionMoneda);

                JButton Volver = new JButton();
                Volver.setSize(100,50);
                Volver.setText("Volver");
                Volver.setLocation(Contenido.getWidth() / 2 - Volver.getWidth() / 2, Contenido.getHeight() / 2 + 300);
                Contenido.add(Volver);
                Volver.addActionListener(new VolverPrincipal(Victoria,JframePrincipal,tipo,guerrero,mago,sacerdote));

                JLabel Fondo = new JLabel();
                Fondo.setSize(Contenido.getWidth(), Contenido.getHeight());
                ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/victory.png");
                Icon PaperPaper = new ImageIcon(
                        Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
                );
                Fondo.setIcon(PaperPaper);
                Fondo.setLocation(0, 0);
                Contenido.add(Fondo);

            }
        }

    }
    private  JLabel Salida(JPanel panel){
        JLabel salida = new JLabel();
        salida.setSize(50, 50);
        String ruta = "src/RPG2/DungeonImagen/dungeon/salida.png";
        ImageIcon Movimiento = new ImageIcon(ruta);
        Icon Enemigo2 = new ImageIcon(
                Movimiento.getImage().getScaledInstance(salida.getWidth(), salida.getHeight(), Image.SCALE_DEFAULT)
        );
        salida.setIcon(Enemigo2);
        salida.setLocation(panel.getWidth() -130, panel.getHeight() - 147);
        salida.setBorder(BorderFactory.createEtchedBorder(Color.cyan, Color.black));
        panel.add(salida);
        panel.setComponentZOrder(salida, 10);
        panel.repaint();
        return salida;
    }
    private JLabel GenerarMoneda(JPanel panel) {
        JLabel Moneda = new JLabel();
        Moneda.setSize(40, 40);
        String ruta = "src/RPG2/DungeonImagen/dungeon/dollar.png";
        Random random = new Random();
        int maxX = panel.getWidth() - 350;
        int minX = 250;
        int maxY = panel.getHeight() -350;
        int minY = 90;
        int x = random.nextInt(maxX - minX +1) + minX;
        int y = random.nextInt(maxY - minY +1) + minY;
        ImageIcon Movimiento = new ImageIcon(ruta);
        Icon Enemigo2 = new ImageIcon(
                Movimiento.getImage().getScaledInstance(Moneda.getWidth(), Moneda.getHeight(), Image.SCALE_DEFAULT)
        );
        Moneda.setIcon(Enemigo2);
        Moneda.setLocation(x, y);
        Moneda.setBorder(BorderFactory.createEtchedBorder(Color.cyan, Color.black));
        panel.add(Moneda);
        panel.setComponentZOrder(Moneda, 10);
        panel.repaint();
        return Moneda;
    }

    private JLabel GenerarObjeto(JPanel panel, int tipo) {
        JLabel Item = new JLabel();
        Item.setSize(40, 40);
        String ruta;
        if (tipo == 1) {
            ruta = "src/RPG2/DungeonImagen/dungeon/sword.png";
        } else if (tipo == 2) {
            ruta = "src/RPG2/DungeonImagen/dungeon/potion.png";
        } else {
            ruta = "src/RPG2/DungeonImagen/dungeon/mitra.png";
        }
        Random random = new Random();
        int maxX = panel.getWidth() - 350;
        int minX = 250;
        int maxY = panel.getHeight() -350;
        int minY = 90;
        int x = random.nextInt(maxX - minX +1) + minX;
        int y = random.nextInt(maxY - minY +1) + minY;
        ImageIcon Movimiento = new ImageIcon(ruta);
        Icon Enemigo2 = new ImageIcon(
                Movimiento.getImage().getScaledInstance(Item.getWidth(), Item.getHeight(), Image.SCALE_DEFAULT)
        );
        Item.setIcon(Enemigo2);
        Item.setLocation(x, y);
        Item.setBorder(BorderFactory.createEtchedBorder(Color.cyan, Color.black));
        panel.add(Item);
        panel.setComponentZOrder(Item, 10);
        panel.repaint();
        return Item;
    }

    private boolean colisiona(JLabel enemigo, JLabel personaje) {
        if (enemigo != null) {
            Rectangle rectEnemigo = new Rectangle(enemigo.getBounds());
            Rectangle rectJugador = new Rectangle(personaje.getBounds());
            return rectEnemigo.intersects(rectJugador);
        } else {
            return false;
        }
    }
}