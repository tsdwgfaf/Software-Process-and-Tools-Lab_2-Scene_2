package facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
<<<<<<< HEAD
 * 菜单界面
 */
public class MenuLayout {

    private JButton practiceButton;
    private JButton examButton;
    private JLabel title;
    private JPanel rootPanel;

    public MenuLayout() {
        practiceButton.addActionListener(e -> SwingUtilities.invokeLater(practiceSelectionLayout::createGUI));
        examButton.addActionListener(e -> SwingUtilities.invokeLater(examSelectionLayout::createGUI));
    }

    public static void createGUI() {
        JFrame frame = new JFrame("MenuLayout");
        frame.setLocationRelativeTo(null);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MenuLayout().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createGUI());
    }
}
