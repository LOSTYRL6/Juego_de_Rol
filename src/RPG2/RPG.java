package RPG2;

import javax.swing.*;
import java.awt.*;

public class RPG {
    private JPanel Main;
    private static JFrame frame;

    public RPG(){
        Main.setPreferredSize(new Dimension(800, 800));
        Main.setSize(new Dimension(800, 800));
        Main.setLayout(null);
        Main.setFocusable(true);

        JButton Start = new JButton();
        Start.setSize(100, 50);
        Start.setText("START");
        Start.setLocation(Main.getWidth() / 2 - Start.getWidth() / 2, Main.getHeight() / 3);

        Main.add(Start);
        Start.addActionListener(new Comenzar(Main,frame));

        JButton Ranking = new JButton();
        Ranking.setSize(100,50);
        Ranking.setText("Ranking");
        Ranking.setLocation(Main.getWidth() / 2 - Start.getWidth() / 2, Main.getHeight() / 2);
        Main.add(Ranking);
        Ranking.addActionListener(new VerRanking(frame));

        JLabel Titulo = new JLabel();
        Titulo.setText("RPG2");
        Titulo.setSize(1000,70);
        Titulo.setLocation(Main.getWidth()/2 - 65, Main.getHeight()/8);
        Font font = Titulo.getFont();
        Titulo.setFont(new Font(font.getName(), Font.PLAIN, 70));
        Main.add(Titulo);

        JLabel Fondo = new JLabel();
        Fondo.setSize(Main.getWidth(), Main.getHeight());
        ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/Fondo1.png");
        Icon PaperPaper = new ImageIcon(
                Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
        );
        Fondo.setIcon(PaperPaper);
        Fondo.setLocation(0, 0);
        Main.add(Fondo);
    }

    public static void main(String[] args) {
        frame = new JFrame("RPG2");
        frame.setContentPane(new RPG().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);


        frame.setLayout(null);
        Toolkit foto = Toolkit.getDefaultToolkit();
        Image Icon = foto.getImage("src/RPG2/DungeonImagen/politecnics.png");
        frame.setIconImage(Icon);
    }
}

