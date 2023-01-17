import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame {
    JButton btnAdd, btnSub, btnMult, btnDiv, btnEq, btnDel, btnClr, btnDec;
    JButton[] numBtn;
    JTextField output;
    String previous, current, operator;
    public void delete() {
        if (current.length() > 0) {
            current = current.substring(0, current.length() - 1);
        }
    }
    public void clear() {
        current = "";
        previous = "";
        operator = null;
    }
    public void processOutputNumber() {
        if (current.length() > 0) {
            String integerPart = current.split("\\.")[0];
            String decimalPart = current.split("\\.")[1];
            if (decimalPart.equals("0")) {
                current = integerPart;
            }
        }
    }
    public void calculate() {
        if (previous.length() < 1 || current.length() < 1) {
            return;
        }
        double result = 0.0;
        double num1 = Double.parseDouble(previous);
        double num2 = Double.parseDouble(current);
        switch (operator) {
            case "*" -> result = num1 * num2;
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "÷" -> result = num1 / num2;
            default -> {
            }
        }
        current = String.valueOf(result);
        operator = null;
        previous = "";
        processOutputNumber();
    }
    public void selectOperator(String newOperator) {
        if (current.isEmpty()) {
            operator = newOperator;
            return;
        }

        if (!previous.isEmpty()) {
            calculate();
        }

        operator = newOperator;
        previous = current;
        current = "";
    }
    public void updateOutput() {
        output.setText(current);
    }
    public void addToOutput(String num) {
        if (num.equals(".") && current.contains(".")) {
            return;
        }
        current += num;
    }
    private class NumBtnHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            for (JButton btn : numBtn) {
                if (selectedBtn == btn) {
                    addToOutput(btn.getText());
                    updateOutput();
                }
            }
        }
    }
    private class OtherBtnHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            if (selectedBtn == btnDel) {
                delete();
            } else if (selectedBtn == btnClr) {
                clear();
            } else if (selectedBtn == btnEq) {
                calculate();
            }
            updateOutput();
        }
    }
    private class OperatorBtnHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            if (selectedBtn == btnMult) {
                selectOperator(btnMult.getText());
            } else if (selectedBtn == btnAdd) {
                selectOperator(btnAdd.getText());
            } else if (selectedBtn == btnSub) {
                selectOperator(btnSub.getText());
            } else if (selectedBtn == btnDiv) {
                selectOperator(btnDiv.getText());
            }
            updateOutput();
        }
    }
    public Calc() {
        super("Calculator");
        JPanel mainPanel = new JPanel();

        current = "";
        previous = "";

        //sub panels
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel row5 = new JPanel();

        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));

        // number buttons
        NumBtnHandler numBtnHandler = new NumBtnHandler();
        numBtn = new JButton[11];

        for (int count = 0; count < numBtn.length - 1; count++){
            numBtn[count] = new JButton(String.valueOf(count));
            numBtn[count].addActionListener(numBtnHandler);

        }

        row2.add(numBtn[7]);
        row2.add(numBtn[8]);
        row2.add(numBtn[9]);
        row3.add(numBtn[4]);
        row3.add(numBtn[5]);
        row3.add(numBtn[6]);
        row4.add(numBtn[1]);
        row4.add(numBtn[2]);
        row4.add(numBtn[3]);
        row5.add(numBtn[0]);

        // Decimal Button
        btnDec = new JButton(".");
        numBtn[10] = btnDec;
        btnDec.addActionListener(numBtnHandler);
        row5.add(btnDec);

        //operation buttons and output
        OtherBtnHandler otherBtnHandler = new OtherBtnHandler();
        OperatorBtnHandler operatorBtnHandler = new OperatorBtnHandler();
        output = new JTextField();
        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMult = new JButton("*");
        btnDiv = new JButton("/");
        btnEq = new JButton("=");
        btnDel = new JButton("Del");
        btnClr = new JButton("Clr");

        row1.add(btnClr);
        row1.add(btnDel);
        row3.add(btnMult);
        row2.add(btnDiv);
        row4.add(btnSub);
        row5.add(btnEq);
        row5.add(btnAdd);

        btnDel.addActionListener(otherBtnHandler);
        btnClr.addActionListener(otherBtnHandler);
        btnEq.addActionListener(otherBtnHandler);
        btnMult.addActionListener(operatorBtnHandler);
        btnAdd.addActionListener(operatorBtnHandler);
        btnSub.addActionListener(operatorBtnHandler);
        btnDiv.addActionListener(operatorBtnHandler);

        //show rows
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(output);
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);
        mainPanel.add(row5);

        //show main panel, end program when x out
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(300, 300);
    }
    public static void main (String[] args) {
        new Calc();
    }
}