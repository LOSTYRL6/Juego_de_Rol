package RPG2;

import Clases.Guerrero;
import Clases.Mago;
import Clases.Sacerdote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AparecerEnemigo implements ActionListener {
    JPanel panel;
    JLabel monigote;
    Guerrero guerrero;
    Mago mago;
    Sacerdote sacerdote;
    JLabel Objeto;
    int tipo;
    JLabel Vidas;
    JFrame frame;
    JFrame principal;
    int vidas;
    boolean derrotaMostrada = false;
    public AparecerEnemigo(JPanel panel, JLabel monigote, Guerrero guerrero, Mago mago, Sacerdote sacerdote,int tipo, JLabel Objeto, JLabel vidas, JFrame frame,JFrame principal) {
        this.panel = panel;
        this.monigote = monigote;
        this.guerrero = guerrero;
        this.mago = mago;
        this.sacerdote = sacerdote;
        this.tipo = tipo;
        this.Objeto = Objeto;
        this.Vidas = vidas;
        this.frame = frame;
        this.principal = principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int x = random.nextInt(panel.getWidth() - 275);
        int y = random.nextInt(panel.getHeight() - 275);
        double probabilidad = random.nextDouble();
        if (x >= 70 && x <= panel.getWidth() - 200 && y >= 60 && y <= panel.getHeight() - 200) {
            if (probabilidad <= 0.01){
                CrearEnemigo(x,y,panel,monigote,guerrero,mago,sacerdote,tipo,Objeto,Vidas);
            }
        }
    }

    private void CrearEnemigo(int x, int y, JPanel panel, JLabel Persoaje,Guerrero guerrero, Mago mago, Sacerdote sacerdote, int tipo,JLabel objeto, JLabel Vidas) {
        JLabel Enemigo = new JLabel();
        Enemigo.setSize(40, 50);
        ImageIcon Movimiento = new ImageIcon("src/RPG2/DungeonImagen/skeleton/skeleton_down.gif");
        Icon Enemigo2 = new ImageIcon(
                Movimiento.getImage().getScaledInstance(Enemigo.getWidth(), Enemigo.getHeight(), Image.SCALE_DEFAULT)
        );
        Enemigo.setIcon(Enemigo2);
        Enemigo.setLocation(x, y);
        Enemigo.setBorder(BorderFactory.createEtchedBorder(Color.cyan, Color.black));
        panel.add(Enemigo);
        panel.setComponentZOrder(Enemigo, 10);
        panel.repaint();
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverEnemigo(Enemigo, panel, Persoaje, guerrero, mago, sacerdote, tipo, objeto, Vidas);
            }

            private void moverEnemigo(JLabel enemigo, JPanel panel, JLabel Personaje, Guerrero guerrero, Mago mago, Sacerdote sacerdote, int tipo, JLabel objeto, JLabel vida) {
                Random random = new Random();
                int direccion = random.nextInt(4);
                int velocidadMovimiento = 10;
                int x = enemigo.getX();
                int y = enemigo.getY();
                ImageIcon MovimientoUP = new ImageIcon("src/RPG2/DungeonImagen/skeleton/skeleton_up.gif");
                Icon EnemigoArriba = new ImageIcon(
                        MovimientoUP.getImage().getScaledInstance(Enemigo.getWidth(), Enemigo.getHeight(), Image.SCALE_DEFAULT)
                );
                ImageIcon MovimientoDOWN = new ImageIcon("src/RPG2/DungeonImagen/skeleton/skeleton_down.gif");
                Icon EnemigoAbajo = new ImageIcon(
                        MovimientoDOWN.getImage().getScaledInstance(Enemigo.getWidth(), Enemigo.getHeight(), Image.SCALE_DEFAULT)
                );
                ImageIcon MovimientoRIGHT = new ImageIcon("src/RPG2/DungeonImagen/skeleton/skeleton_right.gif");
                Icon EnemigoDerecha = new ImageIcon(
                        MovimientoRIGHT.getImage().getScaledInstance(Enemigo.getWidth(), Enemigo.getHeight(), Image.SCALE_DEFAULT)
                );
                ImageIcon MovimientoLEFT = new ImageIcon("src/RPG2/DungeonImagen/skeleton/skeleton_left.gif");
                Icon EnemigoIzquierda = new ImageIcon(
                        MovimientoLEFT.getImage().getScaledInstance(Enemigo.getWidth(), Enemigo.getHeight(), Image.SCALE_DEFAULT)
                );
                switch (direccion) {
                    case 0:
                        x += velocidadMovimiento;
                        if (x > panel.getWidth() - 175) {
                            x -= 20;
                            enemigo.setIcon(EnemigoIzquierda);
                        } else {
                            enemigo.setIcon(EnemigoDerecha);
                        }
                        break;
                    case 1: //
                        y -= velocidadMovimiento;
                        if (y < 55) {
                            y += 20;
                            enemigo.setIcon(EnemigoAbajo);
                        } else {
                            enemigo.setIcon(EnemigoArriba);
                        }
                        break;
                    case 2:
                        y += velocidadMovimiento;
                        if (y > panel.getHeight() - 150) {
                            y -= 20;
                            enemigo.setIcon(EnemigoArriba);
                        } else {
                            enemigo.setIcon(EnemigoAbajo);
                        }
                        break;
                    case 3:
                        x -= velocidadMovimiento;
                        if (x < 65) {
                            x += 20;
                            enemigo.setIcon(EnemigoDerecha);
                        } else {
                            enemigo.setIcon(EnemigoIzquierda);
                        }
                        break;
                }
                boolean Activo;
                enemigo.setLocation(x, y);
                if (tipo == 1) {
                    Activo = guerrero.isTieneEspada();
                    vidas = guerrero.getVidas();
                } else if (tipo == 2) {
                    Activo = mago.isTienePocion();
                    vidas = mago.getVidas();
                } else {
                    Activo = sacerdote.isTieneMitra();
                    vidas = sacerdote.getVidas();
                }
                if (colisiona(enemigo, Personaje)) {
                    if (Activo) {
                        if (tipo == 1) {
                            guerrero.setTieneEspada(false);
                            Activo = guerrero.isTieneEspada();
                            enemigo.setLocation(700, 700);
                            panel.remove(enemigo);
                            panel.repaint();
                            objeto.setVisible(false);
                        } else if (tipo == 2) {
                            mago.setTienePocion(false);
                            Activo = mago.isTienePocion();
                            panel.repaint();
                            objeto.setVisible(false);
                            mago.setVidas(vidas += 1);
                            vidas = mago.getVidas();
                            vida.setText("Vidas: " + vidas);
                            Personaje.setLocation(90,(panel.getHeight() -32)/2);
                        } else {
                            sacerdote.setTieneMitra(false);
                            Activo = sacerdote.isTieneMitra();
                            panel.repaint();
                            objeto.setVisible(false);
                            Personaje.setLocation(90,(panel.getHeight() -32)/2);
                        }
                    } else {
                        Personaje.setLocation(90, (panel.getHeight() -32)/2);
                        if (tipo == 1) {
                            guerrero.setVidas(vidas -= 1);
                            vidas = guerrero.getVidas();
                        } else if (tipo == 2) {
                            mago.setTienePocion(false);
                            mago.setVidas(vidas -= 1);
                            vidas = mago.getVidas();
                        } else {
                            sacerdote.setTieneMitra(false);
                            sacerdote.setVidas(vidas -= 1);
                            vidas = sacerdote.getVidas();
                        }
                        vida.setText("Vidas: " + vidas);
                    }
                }
                if (vidas <= 0 && !derrotaMostrada) {
                    derrotaMostrada = true;
                    frame.dispose();
                    JFrame Defeat = new JFrame("Eleccion");
                    Defeat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Defeat.setSize(600, 900);
                    Defeat.show();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Dimension frameSize = Defeat.getSize();
                    int a = (screenSize.width - frameSize.width) / 2;
                    int b = (screenSize.height - frameSize.height) / 2;
                    Defeat.setLocation(a, b);
                    Defeat.setLayout(null);
                    Toolkit foto = Toolkit.getDefaultToolkit();
                    Image Icon = foto.getImage("src/RPG/DungeonImagen/politecnics.png");
                    Defeat.setIconImage(Icon);
                    JPanel Contenido = new JPanel();
                    Contenido.setPreferredSize(new Dimension(Defeat.getWidth(), Defeat.getHeight()));
                    Contenido.setSize(Defeat.getWidth(), Defeat.getHeight());
                    Contenido.setLayout(null);
                    Contenido.setVisible(true);
                    Defeat.setContentPane(Contenido);

                    JButton Volver = new JButton();
                    Volver.setSize(100, 50);
                    Volver.setText("Volver");
                    Volver.setLocation(Contenido.getWidth() / 2 - Volver.getWidth() / 2, Contenido.getHeight() / 2 + 300);
                    Contenido.add(Volver);
                    Volver.addActionListener(new VolverPrincipal(Defeat, principal, tipo, guerrero, mago, sacerdote));

                    JLabel Fondo = new JLabel();
                    Fondo.setSize(Contenido.getWidth(), Contenido.getHeight());
                    ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/Defeat.png");
                    Icon PaperPaper = new ImageIcon(
                            Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
                    );
                    Fondo.setIcon(PaperPaper);
                    Fondo.setLocation(0, 0);
                    Contenido.add(Fondo);
                }
            }
        });
        timer.start();
    }
    private boolean colisiona(JLabel enemigo, JLabel personaje) {
        Rectangle rectEnemigo = new Rectangle(enemigo.getBounds());
        Rectangle rectJugador = new Rectangle(personaje.getBounds());
        return rectEnemigo.intersects(rectJugador);
    }
}
