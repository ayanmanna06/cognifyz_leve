package level3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import javax.sound.sampled.*;
class MusicPlayer extends JFrame implements ActionListener {
    JLabel label, label1;
    JButton btnChooseFile, btnPlay, btnPause;
    File selectedFile;
    Clip clip;
  //  public Player player;
    public MusicPlayer(String title) {
        super(title);
        getContentPane().setBackground(Color.WHITE);
        label = new JLabel("Simple Music Player Using JAVA & JAVA Swing");
        label.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(label);
        label1 = new JLabel("");
        label1.setBounds(20, 30, 730, 40);
        label1.setFont(new Font("", Font.BOLD, 17));
        add(label1);
        btnChooseFile = new JButton("Choose File");
        btnPlay = new JButton("Play");
        btnPause = new JButton("Pause");
        btnChooseFile.addActionListener(this);
        btnPlay.addActionListener(this);
        btnPause.addActionListener(this);
        add(btnChooseFile);
        add(btnPlay);
        add(btnPause);
        label.setBounds(200, 5, 500, 30);
        btnChooseFile.setBounds(100, 70, 150, 30);
        btnPlay.setBounds(350, 70, 100, 30);
        btnPause.setBounds(550, 70, 100, 30);
        setLayout(null);
        setLocation(350, 100);
        setVisible(true);
        setSize(800, 150);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChooseFile) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSystemView(FileSystemView.getFileSystemView());
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                label1.setText(selectedFile.getAbsolutePath());
            }
        }
        if (e.getSource() == btnPlay) {
            try {
                if(clip==null){
                    File soundFile = new File(selectedFile.getAbsolutePath()); // Support onlr .wav file
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                }
                clip.start();
            } catch (Exception a) {
                a.getStackTrace();
                System.out.println("Error playing sound: " + a);
            }
        }
        if (e.getSource() == btnPause) {
                clip.stop();  // For Pause the song 
            }
        }
    }
public class desktop_application {
    public static void main(String[] args) {
        new MusicPlayer("Music Player");
    }
}
