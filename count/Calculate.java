package count;

public class Calculate {
    private static int num1;
    private static int num2;

    public void generateNum() {
        num1 = (int) (Math.random() * 100 + 1);
        num2 = (int) (Math.random() * 100 + 1);
    }

    public int[] getEquation() {
        int[] temp = new int[2];
        temp[0] = num1;
        temp[1] = num2;
        return temp;
    }

    public int getAnswerByPlus() {
        return num1 + num2;
    }

    public int getAnswerByMinus() {
        return num1 - num2;
    }

    public int getAnswerByMultiply() {
        return num1 * num2;
    }

    public int getAnswerByDivide() {
        if ((num1 % num2) != 0) {
            generateNum();
            getAnswerByDivide();
        }
        return num1 / num2;
    }

}
