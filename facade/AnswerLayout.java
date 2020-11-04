package facade;

import count.Calculate;
import fileExport.FileExport;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * C4分支上的修改
 */

/**
 * 答题界面，包括题目显示、数字键盘等
 */
public class AnswerLayout {
    private JPanel rootPanel;
    private JTextField questionField;
    private JButton nextButton;
    private JButton a4Button;
    private JButton a7Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JProgressBar progressBar;
    private JTextField answerField;
    private JButton delButton;
    private JButton nextButton1;
    private JButton minusButton;
    private JLabel timeField;
    private static int totalProblemNum; //总题目数
    private static int remainingProblemNum; //未答完的题目数量
    private static String pattern;  //答题模式
    private static int correctAnswer;  //当前题目正确答案
    private static int rightNum = 0;
    private static int wrongNum = 0;
    private static int remainingTime = 0;
    private JFrame frame;
    ClockDisplay mt;

    public AnswerLayout() {
        //提交按钮监听器
        nextButton.addActionListener(e -> {
            if (answerField.getText().length() != 0 && !answerField.getText().equals("-")) {  //判断答案提交文本框是否为空
                if (Integer.parseInt(answerField.getText()) == correctAnswer) {    //答案正确
                    questionField.setText("Right!");
                    questionField.paintImmediately(questionField.getBounds());
                    rightNum++;
                } else {                                                       //答案不正确
                    questionField.setText("Wrong!");
                    questionField.paintImmediately(questionField.getBounds());
                    wrongNum++;
                }
            }
        });
        //下一题按钮监听器
        nextButton1.addActionListener(e -> {
            answerField.setText("");
            if (remainingProblemNum == 0) {//判断答题是否结束
                questionField.setText("the end");
                questionField.paintImmediately(questionField.getBounds());
                NumberFormat numberFormat = NumberFormat.getPercentInstance();
                numberFormat.setMaximumFractionDigits(2);
                String message = "accuracy:" + numberFormat
                        .format((double) rightNum / (double) (rightNum + wrongNum)) + "\n" + "right:" +
                        rightNum + "\nall:" + (rightNum + wrongNum);
                JOptionPane
                        .showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
                try {
                    FileExport.fileExport(message);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            } else {//生成题目与标准答案的过程
                //启动倒计时器
                if(remainingProblemNum==totalProblemNum) {
                    mt = new ClockDisplay(timeField, remainingTime);
                    mt.start();
                }
                remainingProblemNum--;
                // 更新进度条
                progressBar.setValue((totalProblemNum - remainingProblemNum) * 100 / totalProblemNum);
                Calculate tempCalculate = new Calculate();
                tempCalculate.generateNum();
                int[] temp = tempCalculate.getEquation();
                String sym = "";
                switch (pattern) {
                    case "add":
                        correctAnswer = tempCalculate.getAnswerByPlus();
                        sym = " + ";
                        break;
                    case "multiply":
                        correctAnswer = tempCalculate.getAnswerByMultiply();
                        sym = " × ";
                        break;
                    case "divide":
                        correctAnswer = tempCalculate.getAnswerByDivide();
                        sym = " ÷ ";
                        break;
                    case "minus":
                        correctAnswer = tempCalculate.getAnswerByMinus();
                        sym = " - ";
                        break;
                    default:
                        assert "mix".equals(pattern);
                        List<String> patternList = new ArrayList<>();
                        patternList.add("add");
                        patternList.add("minus");
                        patternList.add("multiply");
                        patternList.add("divide");
                        String randomPattern = patternList.get((int) (Math.random() * 4));
                        switch (randomPattern) {
                            case "add":
                                correctAnswer = tempCalculate.getAnswerByPlus();
                                sym = " + ";
                                break;
                            case "multiply":
                                correctAnswer = tempCalculate.getAnswerByMultiply();
                                sym = " × ";
                                break;
                            case "divide":
                                correctAnswer = tempCalculate.getAnswerByDivide();
                                sym = " ÷ ";
                                break;
                            default:
                                assert "minus".equals(randomPattern);
                                correctAnswer = tempCalculate.getAnswerByMinus();
                                sym = " - ";
                                break;
                        }
                }
                String question = temp[0] + sym + temp[1];
                questionField.setText(question);
                questionField.paintImmediately(questionField.getBounds());
            }
        });
        a0Button.addActionListener(e -> answerField.setText(answerField.getText() + "0"));
        a1Button.addActionListener(e -> answerField.setText(answerField.getText() + "1"));
        a2Button.addActionListener(e -> answerField.setText(answerField.getText() + "2"));
        a3Button.addActionListener(e -> answerField.setText(answerField.getText() + "3"));
        a4Button.addActionListener(e -> answerField.setText(answerField.getText() + "4"));
        a5Button.addActionListener(e -> answerField.setText(answerField.getText() + "5"));
        a6Button.addActionListener(e -> answerField.setText(answerField.getText() + "6"));
        a7Button.addActionListener(e -> answerField.setText(answerField.getText() + "7"));
        a8Button.addActionListener(e -> answerField.setText(answerField.getText() + "8"));
        a9Button.addActionListener(e -> answerField.setText(answerField.getText() + "9"));
        delButton.addActionListener(e -> answerField.setText(answerField.getText().substring(0, answerField.getText().length() - 1)));
        minusButton.addActionListener(e -> {
            String text = answerField.getText();
            if (text.length() == 0) {
                answerField.setText("-");
                return;
            }
            String first = text.substring(0, 1);
            if ("-".equals(first)) {
                answerField.setText(answerField.getText().substring(1));
            } else {
                answerField.setText("-" + answerField.getText());
            }
        });
    }

    public void createGUI(int num, String pat) {
        totalProblemNum = num;
        remainingProblemNum = num;
        pattern = pat;
        frame = new JFrame("MyGUI");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new AnswerLayout().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void createGUI(int num, int time) {
        remainingTime = time;
        totalProblemNum = num;
        remainingProblemNum = num;
        pattern = "mix";
        frame = new JFrame("MyGUI");
        frame.setContentPane(new AnswerLayout().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//从这里往下都是倒计时模块的线程代码
   class ClockDisplay extends Thread{
        private JLabel leftTime;
        private int testTime;

        public ClockDisplay(JLabel lt,int time){
        leftTime=lt;
        testTime=time;
        }
        @Override
        public void run() {
            while (testTime >= 0) {
                long hour = testTime / 3600;
                long minute = (testTime - hour * 3600) / 60;
                long seconds = testTime - hour * 3600 - minute * 60;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("距离考试结束还有").append(hour).append("小时").append(minute)
                        .append("分钟").append(seconds).append("秒");
                leftTime.setText(stringBuilder.toString());
                leftTime.revalidate();
                try{
                    Thread.sleep(1000);
                }
                catch(Exception ex){}
                testTime=testTime-1;
            }
            questionField.setText("the end");
            questionField.paintImmediately(questionField.getBounds());
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMaximumFractionDigits(2);
            String message = "accuracy:" + numberFormat
                    .format((double) rightNum / (double) totalProblemNum) + "\n" + "right:" +
                    rightNum + "\nall:" + totalProblemNum;
            JOptionPane
                    .showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
            try {
                FileExport.fileExport(message);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);
        }
   }
}
