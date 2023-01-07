import javax.swing.*;

public class Calc extends JFrame {
    JButton btnAdd, btnSub, btnMult, btnDiv, btnEq, btnDel, btnClr, btnDec;
    JButton numBtn [];
    JTextField output;
    String previous, current, operator;

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
        numBtn = new JButton[11];
        numBtn [10] = btnDec;
        for (int count = 0; count < numBtn.length - 1; count++){
            numBtn[count] = new JButton(String.valueOf(count));
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

        //operation buttons and output
        output = new JTextField();
        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMult = new JButton("*");
        btnDiv = new JButton("/");
        btnEq = new JButton("=");
        btnDel = new JButton("Del");
        btnClr = new JButton("Clr");
        btnDec = new JButton(".");

        row1.add(btnClr);
        row1.add(btnDel);
        row3.add(btnMult);
        row2.add(btnDiv);
        row4.add(btnSub);
        row5.add(btnDec);
        row5.add(btnEq);
        row5.add(btnAdd);

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