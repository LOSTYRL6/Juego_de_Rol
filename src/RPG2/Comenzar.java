package RPG2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Comenzar implements ActionListener {
    JPanel Main;
    JFrame Jframe;


    public Comenzar(JPanel main, JFrame jFrame) {
        this.Main = main;
        this.Jframe = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame Eleccion = new JFrame("Eleccion");
        Eleccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Eleccion.show();
        Jframe.dispose();
        Eleccion.setSize(900,900);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = Eleccion.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        Eleccion.setLocation(x, y);
        Eleccion.setLayout(null);
        Toolkit foto = Toolkit.getDefaultToolkit();
        Image Icon = foto.getImage("src/RPG2/DungeonImagen/politecnics.png");
        Eleccion.setIconImage(Icon);
        JPanel Contenido = new JPanel();
        Contenido.setPreferredSize(new Dimension(Eleccion.getWidth(),Eleccion.getHeight()));
        Contenido.setSize(Eleccion.getWidth(),Eleccion.getHeight());
        Contenido.setLayout(null);
        Contenido.setVisible(true);
        Eleccion.setContentPane(Contenido);

        JLabel Titulo = new JLabel();
        Titulo.setText("CHOOSE YOUR CHARACTER");
        Titulo.setSize(950,100);
        Titulo.setLocation(Contenido.getWidth()/7 + 40, Contenido.getHeight()/12);
        Font font = Titulo.getFont();
        Titulo.setFont(new Font(font.getName(), Font.PLAIN, 40));
        Titulo.setForeground(Color.white);
        Contenido.add(Titulo);

        JLabel Mago = new JLabel();
        Mago.setSize(200,200);
        ImageIcon paper = new ImageIcon("src/RPG2/Icono/mage.png");
        Icon PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(Mago.getWidth(),Mago.getHeight(),Image.SCALE_DEFAULT)
        );
        Mago.setIcon(PaperPaper);
        Mago.setLocation(Contenido.getWidth()/2 - Mago.getWidth()/2,Contenido.getHeight()/2 - Mago.getHeight());
        Mago.addMouseListener(new ComenzarJuego(2,Eleccion,Jframe) {});
        Contenido.add(Mago);

        JLabel Caballero = new JLabel();
        Caballero.setSize(200,200);
        paper = new ImageIcon("src/RPG2/Icono/Caballero.png");
        PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(Caballero.getWidth(),Caballero.getHeight(),Image.SCALE_DEFAULT)
        );
        Caballero.setIcon(PaperPaper);
        Caballero.setLocation(Contenido.getWidth()/4 - Caballero.getWidth()/2 - 75,Mago.getLocation().y);
        Caballero.addMouseListener(new ComenzarJuego(1,Eleccion,Jframe) {});
        Contenido.add(Caballero);

        JLabel Priest = new JLabel();
        Priest.setSize(175,175);
        paper = new ImageIcon("src/RPG2/Icono/priest.png");
        PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(Priest.getWidth(),Priest.getHeight(),Image.SCALE_DEFAULT)
        );
        Priest.setIcon(PaperPaper);
        Priest.setLocation(Contenido.getWidth() - 250 ,Mago.getLocation().y);
        Priest.addMouseListener(new ComenzarJuego(3,Eleccion,Jframe) {});
        Contenido.add(Priest);

        JLabel FondoPersonaje1 = new JLabel();
        FondoPersonaje1.setSize(250,250);
        paper = new ImageIcon("src/RPG2/Icono/FondoPersonaje.png");
        PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(FondoPersonaje1.getWidth(),FondoPersonaje1.getHeight(),Image.SCALE_DEFAULT)
        );
        FondoPersonaje1.setIcon(PaperPaper);
        FondoPersonaje1.setLocation(Caballero.getLocation().x -25,Caballero.getLocation().y - 25);
        Contenido.add(FondoPersonaje1);

        JLabel FondoPersonaje2 = new JLabel();
        FondoPersonaje2.setSize(250,250);
        paper = new ImageIcon("src/RPG2/Icono/FondoPersonaje.png");
        PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(FondoPersonaje2.getWidth(),FondoPersonaje1.getHeight(),Image.SCALE_DEFAULT)
        );
        FondoPersonaje2.setIcon(PaperPaper);
        FondoPersonaje2.setLocation(Priest.getLocation().x -45,Priest.getLocation().y -25);
        Contenido.add(FondoPersonaje2);

        JLabel FondoPersonaje3 = new JLabel();
        FondoPersonaje3.setSize(250,250);
        paper = new ImageIcon("src/RPG2/Icono/FondoPersonaje.png");
        PaperPaper = new ImageIcon(
                paper.getImage().getScaledInstance(FondoPersonaje3.getWidth(),FondoPersonaje3.getHeight(),Image.SCALE_DEFAULT)
        );
        FondoPersonaje3.setIcon(PaperPaper);
        FondoPersonaje3.setLocation(Mago.getLocation().x -45,Mago.getLocation().y -25);
        Contenido.add(FondoPersonaje3);

        JLabel NombreCaballero = new JLabel();
        NombreCaballero.setText("KNIGHT");
        NombreCaballero.setSize(300,300);
        NombreCaballero.setLocation(Caballero.getLocation().x + 15,Caballero.getLocation().y + 100);
        font = NombreCaballero.getFont();
        NombreCaballero.setFont(new Font(font.getName(), Font.PLAIN, 50));
        NombreCaballero.setForeground(Color.white);
        Contenido.add(NombreCaballero);

        JLabel NombreMago = new JLabel();
        NombreMago.setText("MAGE");
        NombreMago.setSize(300,300);
        NombreMago.setLocation(Mago.getLocation().x + 15,Mago.getLocation().y + 100);
        font = NombreMago.getFont();
        NombreMago.setFont(new Font(font.getName(), Font.PLAIN, 50));
        NombreMago.setForeground(Color.white);
        Contenido.add(NombreMago);

        JLabel NombrePriest = new JLabel();
        NombrePriest.setText("PRIEST");
        NombrePriest.setSize(300,300);
        NombrePriest.setLocation(Priest.getLocation().x + 15,Priest.getLocation().y + 100);
        font = NombrePriest.getFont();
        NombrePriest.setFont(new Font(font.getName(), Font.PLAIN, 50));
        NombrePriest.setForeground(Color.white);
        Contenido.add(NombrePriest);


        JLabel Fondo = new JLabel();
        Fondo.setSize(Contenido.getWidth(), Contenido.getHeight());
        ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/BackgroundImage.png");
        PaperPaper = new ImageIcon(
                Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
        );
        Fondo.setIcon(PaperPaper);
        Fondo.setLocation(0, 0);
        Contenido.add(Fondo);

    }


}

