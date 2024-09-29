package RPG2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolverPrincipio implements ActionListener {
    JFrame ranking;
    JFrame Principio;
    public VolverPrincipio(JFrame ranking,JFrame Principio) {
        this.ranking = ranking;
        this.Principio = Principio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ranking.dispose();
        Principio.show();
    }
}
