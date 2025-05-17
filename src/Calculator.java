import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10]; // Now we have 10 buttons
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, percentButton; // Added percentButton
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD,15);

    double num1=0, num2=0, result=0;
    char operator;


    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        // Initialize all buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        percentButton = new JButton("%");  // Added percentButton

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = percentButton; // Added to functionButtons array

        // Add action listeners
        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // Set bounds for the buttons
        negButton.setBounds(50,430,60,50);
        delButton.setBounds(120,430,75,50);
        clrButton.setBounds(205,430,75,50);
        percentButton.setBounds(290, 430, 60, 50); // Position for % button

        // Panel for numbers and functions
        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout((new GridLayout(4,4,10,10)));

        // Add buttons to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);

        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(percentButton); // Add % button to the frame

        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        // Handle decimal point
        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        // Handle operator buttons (+, -, *, /)
        if (e.getSource() == addButton || e.getSource() == subButton || 
            e.getSource() == mulButton || e.getSource() == divButton) {

            if (!textField.getText().isEmpty()) {
                num2 = Double.parseDouble(textField.getText());

                if (operator != '\u0000') {
                    calculateIntermediate();
                } else {
                    num1 = num2;
                }
            }

            if (e.getSource() == addButton) operator = '+';
            if (e.getSource() == subButton) operator = '-';
            if (e.getSource() == mulButton) operator = '*';
            if (e.getSource() == divButton) operator = '/';

            textField.setText("");
        }

        // Handle equals button
        if (e.getSource() == equButton) {
            if (!textField.getText().isEmpty() && operator != '\u0000') {
                num2 = Double.parseDouble(textField.getText());
                calculateIntermediate();
                operator = '\u0000'; 
            }
        }

        // Handle clear button
        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = '\u0000';
        }

        // Handle delete button
        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }

        // Handle negation button (-)
        if (e.getSource() == negButton) {
            if (!textField.getText().isEmpty()) {
                double temp = Double.parseDouble(textField.getText());
                temp *= -1;
                textField.setText(String.valueOf(temp));
            }
        }

        // Handle percent button
        if (e.getSource() == percentButton) {
            if (!textField.getText().isEmpty()) {
                double temp = Double.parseDouble(textField.getText());
                temp = temp / 100;  // Calculate percentage
                textField.setText(String.valueOf(temp));
            }
        }
    }

    private void calculateIntermediate() {
        switch (operator) {
            case '+': num1 += num2; break;
            case '-': num1 -= num2; break;
            case '*': num1 *= num2; break;
            case '/': num1 /= num2; break;
        }
        textField.setText(String.valueOf(num1));
    }
}
