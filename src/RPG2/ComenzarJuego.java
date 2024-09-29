package RPG2;

import Clases.Guerrero;
import Clases.Mago;
import Clases.Sacerdote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

public class ComenzarJuego implements MouseListener {



    int tipo;
    JFrame Jframe;
    JLabel Vidas;
    JLabel Monedas;
    JLabel Objeto;
    JFrame JframePrincipal;
    public ComenzarJuego(int tipo, JFrame jFrame,JFrame JframePrincipal) {
        this.tipo = tipo;
        this.Jframe = jFrame;
        this.JframePrincipal = JframePrincipal;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame Juego = getFrame();

        if (tipo == 1){
            JOptionPane.showMessageDialog(null, "Has escogido al Caballero");
            CerrarAbrirFrame(Jframe,Juego,tipo);
        } else if (tipo == 2) {
            JOptionPane.showMessageDialog(null, "Has escogido el mago");
            CerrarAbrirFrame(Jframe,Juego,tipo);
        }else {
            JOptionPane.showMessageDialog(null, "Has escogido al sacerdote");
            CerrarAbrirFrame(Jframe,Juego,tipo);
        }
    }

    private static JFrame getFrame() {
        JFrame Juego = new JFrame("Juego");
        Juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int windowWidth = (int) (screenWidth * 0.9);
        int windowHeight = (int) (screenHeight * 0.9);
        Toolkit foto = Toolkit.getDefaultToolkit();
        Image Icon = foto.getImage("src/RPG2/DungeonImagen/politecnics.png");
        Juego.setIconImage(Icon);

        Juego.setSize(windowWidth,windowHeight);
        Juego.setPreferredSize(new Dimension(windowWidth,windowHeight));
        Juego.setLocationRelativeTo(null);
        Juego.setFocusable(true);
        Juego.requestFocusInWindow();
        return Juego;
    }

    private void CerrarAbrirFrame(JFrame jframe, JFrame juego, int tipo) {
        Guerrero guerrero = new Guerrero(5,0,"src/RPG2/DungeonImagen/warrior/warrior_down.gif",3,true);
        Mago mago = new Mago(3,0,"src/RPG2/DungeonImagen/wizard/wizard_down.gif",7,true);
        Sacerdote sacerdote = new Sacerdote(4,0,"src/RPG2/DungeonImagen/priest/priest_down.gif",5,true);
        JPanel Contenido = CrearPanel(jframe,juego);
        GenerarInterfaz(Contenido,tipo,guerrero,mago,sacerdote);
        GenerarFondo(Contenido,guerrero,mago,sacerdote,tipo,juego);
    }

    private void GenerarFondo(JPanel juego,Guerrero guerrero, Mago mago, Sacerdote sacerdote,int tipo,JFrame Juego2) {
        GenerarPersonaje(juego,guerrero,mago,sacerdote,tipo,Juego2);
        GenerarMuro(juego);
        JLabel Fondo = new JLabel();
        Fondo.setSize(juego.getWidth(), juego.getHeight());
        ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/Fondo3.png");
        ImageIcon Fondo3 = new ImageIcon(
                Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
        );
        Fondo.setIcon(Fondo3);
        Fondo.setLocation(0, 0);
        juego.add(Fondo);
    }

    private void GenerarInterfaz(JPanel juego, int tipo, Guerrero guerrero, Mago mago, Sacerdote sacerdote) {
        String Items;
        JPanel Tittle = new JPanel();
        Tittle.setLocation(0,0);
        Tittle.setSize(juego.getWidth(),50);
        Tittle.setBackground(Color.GRAY);
        Tittle.setLayout(null);
        juego.add(Tittle);
        JLabel Personaje = new JLabel();
        Vidas = new JLabel();
        Monedas = new JLabel();
        Personaje.setSize(50,50);
        if (tipo ==1){
            Personaje.setIcon(guerrero.getFoto());
            Vidas.setText("Vidas: " + guerrero.getVidas());
            Items ="src/RPG2/DungeonImagen/dungeon/sword.png";
            Monedas.setText("Monedas " + guerrero.getOro());
        } else if (tipo ==2) {
            Personaje.setIcon(mago.getFoto());
            Vidas.setText("Vidas: " + mago.getVidas());
            Items ="src/RPG2/DungeonImagen/dungeon/potion.png";
            Monedas.setText("Monedas " + mago.getOro());
        }else {
            Personaje.setIcon(sacerdote.getFoto());
            Vidas.setText("Vidas: " + sacerdote.getVidas());
            Items= "src/RPG2/DungeonImagen/dungeon/mitra.png";
            Monedas.setText("Monedas " + sacerdote.getOro());
        }
        Personaje.setLocation(0,0);
        Tittle.add(Personaje);
        Vidas.setSize(200,50);
        Vidas.setLocation(100,5);
        Tittle.add(Vidas);

        JLabel FotoCorazon = new JLabel();
        FotoCorazon.setSize(50,50);
        ImageIcon CorazonIcon = new ImageIcon("src/RPG2/DungeonImagen/dungeon/heart.png");
        Icon Heart = new ImageIcon(
                CorazonIcon.getImage().getScaledInstance(FotoCorazon.getWidth(),FotoCorazon.getHeight(),Image.SCALE_DEFAULT)
        );
        FotoCorazon.setLocation(50,0);
        FotoCorazon.setIcon(Heart);
        Tittle.add(FotoCorazon);

        JLabel Moneda = new JLabel();
        Moneda.setSize(50,50);
        ImageIcon Moneda2 = new ImageIcon("src/RPG2/DungeonImagen/dungeon/dollar.png");
        Icon Moneda3 = new ImageIcon(
                Moneda2.getImage().getScaledInstance(Moneda.getWidth(),Moneda.getHeight(),Image.SCALE_DEFAULT)
        );
        Moneda.setLocation(Tittle.getWidth() -200,0);
        Moneda.setIcon(Moneda3);
        Tittle.add(Moneda);

        Monedas.setSize(200,50);
        Monedas.setLocation(Tittle.getWidth() -125,0);
        Tittle.add(Monedas);

        Objeto = new JLabel();
        Objeto.setSize(50,50);
        ImageIcon Objeto2 = new ImageIcon(Items);
        Icon Objeto3 = new ImageIcon(
                Objeto2.getImage().getScaledInstance(Objeto.getWidth(),Objeto.getHeight(),Image.SCALE_DEFAULT)
        );
        Objeto.setLocation(Tittle.getWidth() -400,0);
        Objeto.setIcon(Objeto3);
        Tittle.add(Objeto);
    }

    public JPanel CrearPanel(JFrame jframe, JFrame juego){
        jframe.dispose();
        JPanel Contenido = new JPanel();
        Contenido.setPreferredSize(new Dimension(jframe.getWidth(),jframe.getHeight()));
        Contenido.setSize(jframe.getWidth(),jframe.getHeight());
        Contenido.setLayout(null);
        Contenido.setVisible(true);
        Contenido.setFocusable(true);
        Contenido.setBackground(Color.GREEN);
        juego.setContentPane(Contenido);
        juego.setVisible(true);
        return Contenido;
    }

    public void GenerarMuro(JPanel juego) {
        int width = juego.getWidth();
        int height = juego.getHeight();
        int borderSpace = 50;
        int wallSize = 32;

        int numRows = (height - 2 * borderSpace) / wallSize;
        int numCols = (width - 2 * borderSpace) / wallSize;

        ImageIcon wallIcon = cargarYRedimensionarImagen("src/RPG2/DungeonImagen/dungeon/tile004.png", wallSize, wallSize);
        ImageIcon fillIcon = cargarYRedimensionarImagen("src/RPG2/DungeonImagen/dungeon/tile001.png", wallSize, wallSize);
        int entradaWidth = 4;
        int salidaWidth = 4;
        int entradaFila = numRows / 2;
        int salidaFila = entradaFila + 11;
        int entradaColumna = 0;
        int salidaColumna = numCols - salidaWidth;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                JLabel wall = new JLabel();
                wall.setBounds(borderSpace + j * wallSize, borderSpace + i * wallSize, wallSize, wallSize);
                if (i == entradaFila && j < entradaColumna + entradaWidth || i == salidaFila && j >= salidaColumna && j < salidaColumna + salidaWidth) {
                    wall.setIcon(fillIcon);
                } else if (i == 0 || i == numRows - 1 || j == 0 || j == numCols - 1) {
                    wall.setIcon(wallIcon);
                } else {
                    wall.setIcon(fillIcon);
                }
                juego.add(wall);
            }
        }
    }
    private void GenerarPersonaje(JPanel panel,Guerrero guerrero, Mago mago, Sacerdote sacerdote,int tipo,JFrame juego) {
        JLabel monigote = new JLabel();
        int x;
        int y;
        int monigoteSize = 32;
        monigote.setSize(monigoteSize, monigoteSize);
        if (tipo == 1){
            monigote.setIcon(guerrero.getFoto());
            guerrero.setX(90);
            guerrero.setY((panel.getHeight() - monigoteSize) / 2);
            x = guerrero.getX();
            y = guerrero.getY();
        } else if (tipo ==2) {
            monigote.setIcon(mago.getFoto());
            mago.setX(90);
            mago.setY((panel.getHeight() - monigoteSize) / 2);
            x = mago.getX();
            y = mago.getY();
        }else {
            monigote.setIcon(sacerdote.getFoto());
            sacerdote.setX(panel.getWidth() -130);
            sacerdote.setY((panel.getHeight() - 140));
            x = sacerdote.getX();
            y = sacerdote.getY();
        }

        monigote.setLocation(x, y);
        monigote.setBorder(BorderFactory.createEtchedBorder(Color.cyan,Color.black));
        panel.addKeyListener(new MainKeyListener(monigote, tipo, panel, guerrero, mago, sacerdote,Objeto,Monedas,Vidas,juego,JframePrincipal));
        panel.requestFocusInWindow();
        panel.add(monigote);
    }
    public static ImageIcon cargarYRedimensionarImagen(String ruta, int width, int height) {
        ImageIcon icon = new ImageIcon(ruta);
        Image imagen = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
