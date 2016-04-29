package se.liu.ida.antla703.tddd78.conquest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Conquest frame. Creates frame in which board and score is drawn with option to quit or restart the game.
 */
public class ConquestFrame extends JFrame {
    private Board board;
    private ConquestComponent conquestComponent;

    public ConquestFrame(Board board) {
        super("Conquest");
        this.board = board;
        this.createMenus();

        this.conquestComponent = new ConquestComponent(this.board);

        this.setLayout(new BorderLayout());
        this.add(conquestComponent, BorderLayout.CENTER);
        this.setKeyBindings();
    }

    public void createMenus() {
        final JMenu gameMenu = new JMenu("Game");
        final JMenuItem quit = new JMenuItem("Quit");
        gameMenu.add(quit);
        final JMenuItem restart = new JMenuItem("Restart");
        gameMenu.add(restart);

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = " Really quit? ";
                String title = " Quit ";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) { System.exit(0); }
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = " Really restart? ";
                String title = " restart ";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) { board.resetBoard(); }
            }
        });

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
    }

    private Action moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveLeft();
        }
    };

    private Action moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveRight();
        }
    };

    private Action moveDown = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveDown();
        }
    };

    private Action moveUp = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveUp();
        }
    };

    private Action toggleMove = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.toggleMove();
        }
    };

    private Action battlecry = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.battlecry();
        }
    };

    private Action sprint = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.sprint();
        }
    };

    private void setKeyBindings() {
        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
        conquestComponent.getActionMap().put("moveLeft", moveLeft);

        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");
        conquestComponent.getActionMap().put("moveRight", moveRight);

        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
        conquestComponent.getActionMap().put("moveDown", moveDown);

        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
        conquestComponent.getActionMap().put("moveUp", moveUp);

	conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("P"), "toggleMove");
 	conquestComponent.getActionMap().put("toggleMove", toggleMove);

        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("K"), "battlecry");
        conquestComponent.getActionMap().put("battlecry", battlecry);

        conquestComponent.getInputMap().put(KeyStroke.getKeyStroke("L"), "sprint");
        conquestComponent.getActionMap().put("sprint", sprint);
    }
}
