import javax.swing.*;

public class FunctionDisplay {
    public FunctionDisplay() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new JLabel("Hello"));
        //frame.getContentPane().add(new JTable(3,3));
        frame.getContentPane().add(new JTextField(7));
        frame.pack();
        frame.setVisible(true);
    }
}
