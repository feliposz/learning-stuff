import javax.swing.*;
import java.awt.BorderLayout;

public class HelloSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World:");
        frame.setSize(400,100);

        JPanel bottomBar = new JPanel();
        
        JButton go = new JButton("Go!");
        JTextField nameField = new JTextField(20);
        bottomBar.add(nameField);
        bottomBar.add(go);
        
        frame.getContentPane().add(
            bottomBar,
            BorderLayout.SOUTH
        );
        
        frame.setVisible(true);
    }
}