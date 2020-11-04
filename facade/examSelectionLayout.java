package facade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * C4分支上的修改
 */

public class examSelectionLayout {
    private JPanel rootPanel;
    private JTextField numField;
    private JButton submitButton;
    private JLabel title;
    private JLabel explanationButton;
    private JTextField timefield;

    public examSelectionLayout() {
        // 点击文本框后，文本框中的提示消失
        numField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                numField.setText("");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int problemNumber = Integer.parseInt(numField.getText());
                    SwingUtilities.invokeLater(() ->
                            new AnswerLayout().createGUI(problemNumber, Integer.parseInt(timefield.getText())));
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "请输入正整数",
                            "输入题目数错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void createGUI() {
        JFrame frame = new JFrame("examSelectionLayout");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new examSelectionLayout().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(examSelectionLayout::createGUI);
    }
}
