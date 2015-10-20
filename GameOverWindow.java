import javax.swing.JDialog;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverWindow extends JDialog {
	
	private JLabel wyniki;
	private JLabel koniec;
	private JButton ok;
	
	public GameOverWindow() {
		setTitle("Koniec Gry");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(270, 160);
		setModal(true);
		setLocation(new Point(520, 230));
		setResizable(false);
		
		koniec = new JLabel("GAME OVER !!!");
		koniec.setBounds(85, 25, 110, 20);
		koniec.setBackground(Color.white);
				
		wyniki = new JLabel("Twój wynik: " + Board.wynik);
		wyniki.setBounds(85, 45, 110, 20);
		wyniki.setBackground(Color.white);
				
		ok = new JButton("OK");
		ok.setBounds(110, 85, 50, 30);
		ok.setFocusable(false);
		ok.setMargin(new Insets(1,1,1,1));
		ok.addActionListener(new Koniec());
		
		add(ok);
		add(koniec);
		add(wyniki);

		Sound.muza4.play();
		
		setVisible(true);
	}
	
	private class Koniec implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			Sound.muza4.stop();
		}
	}
}