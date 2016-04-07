package conquest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConquestFrame extends JFrame
{
    private Board board;
    private ConquestComponent conquestComponent;

    public ConquestFrame(Board board) {
        super("Conquest");
        this.board = board;
        this.createMenus();

        this.conquestComponent = new ConquestComponent(this.board);

        this.setLayout(new BorderLayout());
        this.add(conquestComponent, BorderLayout.CENTER);
        //this.setKeyBindings();
    }

    public void createMenus() {
        final JMenu gameMenu = new JMenu("Game");
        final JMenuItem quit = new JMenuItem("Quit");
        gameMenu.add(quit);

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = " Really quit? ";
                String title = " Quit ";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) { System.exit(0); }
            }
        });

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
    }
}
