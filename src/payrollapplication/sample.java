import java.awt.*;
import javax.swing.*;

public class sample extends JFrame {
    
    public sample() {
        super("Multiple JPanels Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // Create the first panel
        JPanel panel1 = new JPanel(new GridLayout(3, 2));
        panel1.add(new JLabel("Name:"));
        panel1.add(new JTextField(20));
        panel1.add(new JLabel("Age:"));
        panel1.add(new JTextField(3));
        panel1.add(new JLabel("Gender:"));
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);
        panel1.add(genderPanel);
        
        // Create the second panel
        JPanel panel2 = new JPanel(new GridLayout(2, 2));
        panel2.add(new JLabel("Address:"));
        panel2.add(new JTextField(30));
        panel2.add(new JLabel("Phone:"));
        panel2.add(new JTextField(10));
        
        // Create the third panel
        JPanel panel3 = new JPanel(new GridLayout(1, 2));
        panel3.add(new JButton("Save"));
        panel3.add(new JButton("Cancel"));
        
        // Add the panels to the frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new sample();
    }
}
