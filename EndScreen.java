import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndScreen implements ActionListener {
    JFrame endFrame = new JFrame();
    JPanel endPanel = new JPanel();
    JButton restartButton = new JButton("Restart");
    JButton quitButton = new JButton("Quit");
    JLabel endMessage = new JLabel("You Died");

    
    public EndScreen()
    {
        endPanel.setBackground(Color.black);
        endPanel.setLayout(null);

        endMessage.setBounds(340, 150, 200, 50);
        endMessage.setFont(new Font("Serif", Font.ROMAN_BASELINE, 10));
        endMessage.setForeground(Color.red);

        restartButton.setBounds(350,300, 100, 50);
        quitButton.setBounds(350, 350, 100, 50);

        restartButton.addActionListener(this);
        quitButton.addActionListener(this);

        endPanel.add(restartButton);
        endPanel.add(quitButton);
        endPanel.add(endMessage);

        endFrame.add(endPanel);
        endFrame.setUndecorated(true);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(800, 400);
        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(false);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == restartButton)
        {
            endFrame.setVisible(false);
            endFrame.dispose();
            Game.startScreen.startFrame.setVisible(true);
        }
        if(e.getSource() == quitButton)
        {
            System.exit(0);
        }
    }
}
