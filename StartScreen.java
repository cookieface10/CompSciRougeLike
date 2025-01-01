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
    JLabel gameTitle = new JLabel("SuperDuper Astro Destroyer");
    JLabel credits = new JLabel("By Ryan Mcgurrin and William Kim");

    public StartScreen() {
        startPanel.setBackground(Color.blue);
        startPanel.setLayout(null);

        gameTitle.setBounds(340, 150, 200, 50);
        gameTitle.setFont(new Font("Serif", Font.PLAIN, 10));
        gameTitle.setForeground(Color.WHITE);

        credits.setBounds(330, 250, 200, 50);
        credits.setFont(new Font("Serif", Font.PLAIN, 10));
        credits.setForeground(Color.WHITE);

        startButton.setBounds(350, 300, 100, 50);
        exitButton.setBounds(350, 350, 100, 50);

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
        if (e.getSource() == startButton) {
            Game.characterPosX = Game.center.x - 25;
            Game.characterPosY = Game.center.y - 25;
            Game.openStartScreen = false;
            startFrame.setVisible(false);
            Game.frame.setVisible(true);
        }
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
