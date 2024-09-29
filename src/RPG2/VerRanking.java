package RPG2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class VerRanking implements ActionListener {
    JFrame frame;

    public VerRanking(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] opciones = {"Guerrero", "Mago", "Sacerdote"};
        String NombreTitulo;
        String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una clase:", "Ranking", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (seleccion != null) {
            switch (seleccion) {
                case "Guerrero":
                    NombreTitulo = "Ranking guerrero";
                    MostrarRAnking(1,NombreTitulo,frame);
                    break;
                case "Mago":
                    NombreTitulo = "Ranking Mago";
                    MostrarRAnking(2,NombreTitulo,frame);
                    break;
                case "Sacerdote":
                    NombreTitulo = "Ranking Sacerdote";
                    MostrarRAnking(3,NombreTitulo,frame);
                    break;
                default:
                    break;
            }
        }
    }

    private void MostrarRAnking(int tipo, String NombreTitulo, JFrame frame) {
        this.frame.dispose();
        JFrame Ranking = new JFrame("Eleccion");
        Ranking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ranking.show();
        Ranking.setSize(600, 900);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = Ranking.getSize();
        int a = (screenSize.width - frameSize.width) / 2;
        int b = (screenSize.height - frameSize.height) / 2;
        Ranking.setLocation(a, b);
        Ranking.setLayout(null);
        Toolkit foto = Toolkit.getDefaultToolkit();
        Image Icon = foto.getImage("src/RPG/DungeonImagen/politecnics.png");
        Ranking.setIconImage(Icon);
        JPanel Contenido = new JPanel();
        Contenido.setPreferredSize(new Dimension(Ranking.getWidth(), Ranking.getHeight()));
        Contenido.setSize(Ranking.getWidth(), Ranking.getHeight());
        Contenido.setLayout(null);
        Contenido.setVisible(true);
        Ranking.setContentPane(Contenido);

        JLabel Titulo = new JLabel();
        Titulo.setText(NombreTitulo);
        Titulo.setSize(300,70);
        Titulo.setLocation(Ranking.getWidth() / 2 - 125,100);
        Font font = Titulo.getFont();
        Titulo.setFont(new Font(font.getName(), Font.PLAIN, 35));
        Titulo.setForeground(Color.green);
        Contenido.add(Titulo);

        JPanel Rectangulo = new JPanel();
        Rectangulo.setSize(300, 450);
        Rectangulo.setPreferredSize(new Dimension(300, 450));
        Rectangulo.setLayout(null);
        Rectangulo.setBackground(Color.GRAY);
        Rectangulo.setLocation(Ranking.getWidth() / 2 - Rectangulo.getWidth() / 2, Ranking.getHeight() / 2 - Rectangulo.getHeight() / 2);
        Contenido.add(Rectangulo);

        // Leer datos del archivo y almacenar en una lista
        List<String[]> datos;
        datos = new ArrayList<>();
        Set<String> nombresSet = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Clasificacion.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4 && (
                        (tipo == 1 && partes[1].equals("Guerrero")) ||
                                (tipo == 2 && partes[1].equals("Mago")) ||
                                (tipo == 3 && partes[1].equals("Sacerdote")))) {
                    if (!nombresSet.contains(partes[0])) {
                        nombresSet.add(partes[0]);
                        datos.add(partes);
                    } else {
                        for (String[] dato : datos) {
                            if (dato[0].equals(partes[0]) && Integer.parseInt(partes[3]) > Integer.parseInt(dato[3])) {
                                dato[2] = partes[2];
                                dato[3] = partes[3];
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        datos.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int puntos1 = Integer.parseInt(o1[3]);
                int puntos2 = Integer.parseInt(o2[3]);
                return puntos2 - puntos1;
            }
        });

        for (int i = 0; i < Math.min(datos.size(), 8); i++) {
            String[] partes = datos.get(i);
            int monedas = Integer.parseInt(partes[2]);
            int vidas = Integer.parseInt(partes[3]);
            int posicion = i + 1;
            String nombreJugador = posicion + "- " + partes[0];
            JLabel estadisticaLabel = new JLabel(nombreJugador + ": " + partes[1] + ": Monedas=" + monedas + ", Vidas=" + vidas);
            estadisticaLabel.setHorizontalAlignment(SwingConstants.CENTER);
            estadisticaLabel.setForeground(Color.white);
            estadisticaLabel.setSize(Rectangulo.getWidth(), 50);
            int y = (i * estadisticaLabel.getHeight()) + 20;
            estadisticaLabel.setLocation(0, y);
            Rectangulo.add(estadisticaLabel);
        }


        JButton Volver = new JButton();
        Volver.setSize(100, 50);
        Volver.setText("Volver");
        Volver.setLocation(Ranking.getWidth() / 2 - Volver.getWidth() / 2, Ranking.getHeight() - 100);
        Ranking.add(Volver);
        Volver.addActionListener(new VolverPrincipio(Ranking,frame));

        JLabel Fondo = new JLabel();
        Fondo.setSize(Contenido.getWidth(), Contenido.getHeight());
        ImageIcon Fondo2 = new ImageIcon("src/RPG2/ImagenDeFondo/Ranking.png");
        Icon PaperPaper = new ImageIcon(
                Fondo2.getImage().getScaledInstance(Fondo.getWidth(), Fondo.getHeight(), Image.SCALE_DEFAULT)
        );
        Fondo.setIcon(PaperPaper);
        Fondo.setLocation(0, 0);
        Contenido.add(Fondo);
    }
}