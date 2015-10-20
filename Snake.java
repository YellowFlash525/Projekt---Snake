import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//klasa Snake dziedziczy z klasy JFrame
public class Snake extends JFrame {
	
	private JButton zakoncz;
	private JLabel wynikiTekst;
	static JLabel wyniki;
	private JLabel partsnake_t;
	static JLabel partsnake;
	private JLabel obraz;
	
	public Snake() {

		super("Snake by Magic");
		setSize(500, 400);
		setResizable(false); // blokada zmiany rozmiaru okna
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(new Point(420, 120));
		
		Insets insets = new Insets(1, 1, 1, 1); // rozmiar buttona
		
		// miejsce gdzie jest wskazywana iloœc punktów
		wynikiTekst = new JLabel("Score:");
		wynikiTekst.setBounds(406, 200, 40, 30);
		
		wyniki = new JLabel("" + Board.wynik);
		wyniki.setBounds(450, 200, 42, 30);
		
		// miejsce gdzie jest wskazywana iloœæ punktów
		partsnake_t = new JLabel("Length:");
		partsnake_t.setBounds(406, 180, 42, 30);
		
		partsnake = new JLabel("" + Board.dots);
		partsnake.setBounds(450, 180, 44, 30);
		
		//obrazek we¿a w grze
		obraz = new JLabel(new ImageIcon("res/w¹¿.png"));
		obraz.setBounds(401, 50, 90, 118);
	
		//prarametry przycisku zakoñcz
		zakoncz = new JButton("Powrót");
		zakoncz.setMargin(insets);
		zakoncz.setFocusable(false);
		zakoncz.setBounds(407, 240, 80, 30);
		zakoncz.addActionListener(new Zakoncz());
		
		Board board = new Board();
		board.setBounds(0,0,400,372);
		
		add(board);
		add(obraz);
		add(partsnake_t);
		add(partsnake);
		add(wynikiTekst);
		add(wyniki);
		add(zakoncz);
		
	
		setVisible(true);
	}
	
	private class Zakoncz implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Board.time.stop();
			dispose();	
		}
		
	}

}
