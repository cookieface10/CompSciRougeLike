import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen implements ActionListener {
    JFrame startFrame = new JFrame();
    JPanel startPanel = new JPanel();
    JButton startButton = new JButton("Start!");
    JButton exitButton = new JButton("Exit");
    JLabel gameTitle = new JLabel("Block Survival");
    JLabel credits = new JLabel("By Ryan Mcgurrin and William Kim");

    public StartScreen() {
        // Setting up GUI of the start screen, including game title, credits and start
        // and quit buttons
        startPanel.setBackground(Color.blue);
        startPanel.setLayout(null);

        gameTitle.setBounds(315, 130, 200, 50);
        gameTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        gameTitle.setForeground(Color.WHITE);

        credits.setBounds(300, 190, 300, 50);
        credits.setFont(new Font("Serif", Font.PLAIN, 15));
        credits.setForeground(Color.WHITE);

        startButton.setBounds(350, 260, 100, 50);
        exitButton.setBounds(350, 320, 100, 50);

        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        startPanel.add(startButton);
        startPanel.add(exitButton);
        startPanel.add(gameTitle);
        startPanel.add(credits);

        startFrame.add(startPanel);
        startFrame.setUndecorated(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(800, 400);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        // If the start button is pressed, hid the start screen and make the main game
        // frame visible
        if (e.getSource() == startButton) {
            Game.openStartScreen = false;
            startFrame.setVisible(false);
            Game.frame.setVisible(true);
        }
        // If the exit button is pressed close the thread and stop running the game
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
