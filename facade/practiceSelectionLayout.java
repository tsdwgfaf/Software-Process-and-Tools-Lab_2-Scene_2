package facade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * C4分支上的修改
 */

public class practiceSelectionLayout {

    private JPanel rootPanel;
    private JRadioButton addButton;
    private JRadioButton multiButton;
    private JRadioButton divButton;
    private JRadioButton mixButton;
    private JLabel title;
    private JTextField numField;
    private JButton submitButton;
    private JRadioButton minusButton;
    private String pattern = "null";

    public practiceSelectionLayout() {
        addButton.addActionListener(e -> pattern = "add");
        minusButton.addActionListener(e -> pattern = "minus");
        multiButton.addActionListener(e -> pattern = "multiply");
        divButton.addActionListener(e -> pattern = "divide");
        mixButton.addActionListener(e -> pattern = "mix");
        submitButton.addActionListener(e -> {
            int problemNum = 0;
            try {
                problemNum = Integer.parseInt(numField.getText());
                if (problemNum < 1 || problemNum > 100) {
                    JOptionPane.showMessageDialog(null, "输入的题目数不在范围内",
                            "题目数输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!pattern.equals("add") && !pattern.equals("minus")
                        && !pattern.equals("multiply") && !pattern.equals("divide") && !pattern.equals("mix")) {
                    JOptionPane.showMessageDialog(null, "请在五种题目类型中选择一个",
                            "未选择题目类型", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                AnswerLayout answerLayout = new AnswerLayout();
                int finalProblemNum = problemNum;
                SwingUtilities.invokeLater(() -> answerLayout.createGUI(finalProblemNum, pattern));             //将题目数量与模式作为参数交给answerLayOut
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "输入的题目数不是整数",
                        "题目数输入错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        numField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                numField.setText("");
            }
        });
    }

    public static void createGUI() {
        JFrame frame = new JFrame("practiceSelectionLayout");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new practiceSelectionLayout().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(practiceSelectionLayout::createGUI);
    }
}
