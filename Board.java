import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//klasa Board dziedziczy z klasy JPanel i implementuje interfejs z ActionListener
public class Board extends JPanel implements ActionListener {
	
	public final int B_HEIGH = 350; //wysokosc planszy
	public final int B_WIDTH = 380; //szerokosc planszy
	private final int DOT_SIZE = 10; //rozmiar jab³ka i punktu
	private final int ALL_DOT = 900; //definiuje mo¿liw¹ iloœæ punktów na planszy
	private int DELAY;
	
	private boolean updirect = false; // zmienna boolowska do kierunku -up
	private boolean downdirect = false; // zmienna boolowska do kierunku -down
	private boolean leftdirect = false; // zmienna boolowska do kierunku -left
	private boolean rightdirect = true; // zmienna boolowska do kierunku -right
	public boolean inGame = true; // zmienna stanu gry
	private boolean bonusstar = true;
	
	// przetrzymuj¹ wspó³rzedne wszystkich po³¹czeñ wê¿a 
	public int x[];
    public int y[];
	
	static int dots; // iloœæ czeœci wê¿a
	private int fruit_x; //wspó³rzedna x jab³ka
	private int fruit_y; //wspó³rzêdna y jab³ka
	private int mushroom_x; //wspó³rzedna x grzyba
	private int mushroom_y; //wspó³rzedna y grzyba
	private int star_x;
	private int star_y;
	static int wynik;
	
	private Image head; //zmienna do zdjecia g³owy wê¿a
	private Image fruit; // zmienna do owocu
	private Image body; // zmienna do zdjecia cia³a wêza
	private Image star;
	public static Timer time; // timmer
	private Image mushroom; // zmienna do zdjecia grzybka
	private Image frame; //zmienna do zdjecia ramki
	
	//-------------------------------------konstruktor------------------------------------
	public Board() { 
		
		
		addKeyListener(new TAdapter()); // przekazanie informacji obiektowi aplikacji
		setBackground(Color.black); //kolor planszy
		setFocusable(true);
		
		//wprowadzenie rozmiaru planszy
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGH));
		
		//rysowanie komponentów do gry za pomoc¹ klasy ImageIcon(s³u¿y do pokazywania obrazków)
		ImageIcon iid = new ImageIcon("res/body.png");
		body = iid.getImage();
		
		ImageIcon iih = new ImageIcon("res/head.png");
		head = iih.getImage();
		
		ImageIcon iia = new ImageIcon("res/apple.png");
		fruit = iia.getImage();
		
		ImageIcon iib = new ImageIcon("res/grzyb.png");
		mushroom = iib.getImage();
		
		ImageIcon iie = new ImageIcon("res/star1.png");
		star = iie.getImage();
		
		ImageIcon iic = new ImageIcon("res/rameczka.png");
		frame = iic.getImage();
		
		
		initializationGame();
		
	}
	
	/*inicjalizacja gry: tworzymy wê¿a,umiejscawiamy owoc
	przy pomocy metody putApple i odpalamy timer*/
	private void initializationGame(){
		
		DELAY = 100;

		x = new int[ALL_DOT];
		y = new int[ALL_DOT];
		
		dots = 3;
		wynik = 0;
		
		for(int i = 0; i < dots; i++){
			x[i] = 180 - i * 10;
			y[i] = 180;
 		}
		
		Snake.partsnake.setText(String.valueOf(dots));
		Snake.wyniki.setText(String.valueOf(wynik));
		
		putFruit(); 
		putMushroom();
		
		time = new Timer(DELAY, this);
		time.start();
		
	}
	
	
	//--------------------------------------wstawienie owocu-----------------------------------
	private void putFruit(){
		
		int fru = (int)(Math.random() * 29+1);
		fruit_x = ((fru * DOT_SIZE));
		
		fru = (int)(Math.random() * 29+1);
		fruit_y = ((fru * DOT_SIZE));
	}
	
	//-------------------------------------wstawianie grzybka----------------------------------
	private void putMushroom(){
		
		int grz = (int)(Math.random()* 29+1);
		mushroom_x = ((grz * DOT_SIZE));
		
		grz = (int)(Math.random()* 29+1);
		mushroom_y = ((grz * DOT_SIZE));
	}
	
	private void putStar(){
		
		int str = (int)(Math.random()* 29+1);
		star_x = ((str * DOT_SIZE));
		
		str = (int)(Math.random()* 29+1);
		star_y = ((str * DOT_SIZE));
	}
	
	//------------------------------------sprawdzanie owocu, grzybka i gwiazdy-------------------------
	private void checkElements(){
		if((x[0] == fruit_x) && (y[0] == fruit_y)){
			
			dots++;
			wynik += 10;
			Snake.partsnake.setText(String.valueOf(dots));
			Snake.wyniki.setText(String.valueOf(wynik));
			
			Sound.muza1.play();
			
			putFruit(); //losowanie randomowej pozycji nowego owocu
			putMushroom(); //losowanie randowmowej pozycji nowego grzyba
			
			bonusstar = true;
			
		}
		else if((x[0] == mushroom_x) && (y[0] == mushroom_y)){
			
			dots = dots-2;
			wynik -= 20;
			Snake.partsnake.setText(String.valueOf(dots));
			Snake.wyniki.setText(String.valueOf(wynik));
			
			Sound.muza2.play();
			
			putMushroom(); //losowanie randowmowej pozycji nowego grzyba
			putFruit(); //losowanie randomowej pozycji nowego owocu
		}
		else if((x[0] == star_x) && (y[0] == star_y)){
			dots -= 4;
			wynik +=50;
			
			Snake.partsnake.setText(String.valueOf(dots));
			Snake.wyniki.setText(String.valueOf(wynik));
			
			Sound.muza5.play();
			
			putMushroom();
			putFruit();
			putStar();
			
		}
		if((dots > 10) && (bonusstar == true)){
			putStar();
			bonusstar = false;
		}
	}
	
	
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Drawing(g);
    }
	
	//----------------------------------rysowanie elementów planszy--------------------------
	public void Drawing(Graphics g){
		
		if(inGame){
			
			g.drawImage(fruit, fruit_x, fruit_y, this);
			g.drawImage(mushroom, mushroom_x, mushroom_y, this);
			g.drawImage(star, star_x, star_y, this);
			
			for(int i = 0; i < dots; i++){
				if(i == 0){
					g.drawImage(head, x[i], y[i], this);
				} else {
					g.drawImage(body, x[i], y[i], this);
				}	
			}
			
			for(int i = 0; i <= 390; i += 10){
				g.drawImage(frame, i, 0, this);
				g.drawImage(frame, i, 360, this);
			}
			for(int i = 0; i < 360; i += 10){
				g.drawImage(frame, 0, i, this);
				g.drawImage(frame, 390, i, this);
			}
			
			Toolkit.getDefaultToolkit().sync();
		} 
		else{
			GameOver(g);
		}
		
	}
	
	//-------------------------------------Koniec gry--------------------------------------
	private void GameOver(Graphics g){
		String napis = "Game Over!!!";
		Font moj = new Font("Helvetica", Font.BOLD, 20);
		FontMetrics mrt = getFontMetrics(moj);
		
		g.setColor(Color.GREEN);
		g.setFont(moj);
		g.drawString(napis, (B_WIDTH - mrt.stringWidth(napis)) / 2, B_HEIGH / 2);
		
	}
	
	//dziedziczenie z klasy KeyAdapter
	public class TAdapter extends KeyAdapter
	{
		@Override
		//metoda wywo³ywana po wciœnieciu odpowiedniego klawisza
		public void keyPressed(KeyEvent e) { 
			
			int tmp = e.getKeyCode();
			
			if(((tmp == KeyEvent.VK_RIGHT) && (!leftdirect)) || ((tmp == KeyEvent.VK_D) && (!leftdirect))){
				rightdirect = true;
				updirect = false;
				downdirect = false;
			}//ruch w prawo
			if(((tmp == KeyEvent.VK_LEFT) && (!rightdirect)) || ((tmp == KeyEvent.VK_A) && (!rightdirect))){
				leftdirect = true;
				updirect = false;
				downdirect = false;
			}//ruch w lewo
			if(((tmp == KeyEvent.VK_UP) && (!downdirect)) || ((tmp == KeyEvent.VK_W) && (!downdirect))){
				rightdirect = false;
				updirect = true;
				leftdirect = false;
			}//ruch w górê
			if(((tmp == KeyEvent.VK_DOWN) && (!updirect)) || ((tmp == KeyEvent.VK_S) && (!updirect))){
				rightdirect = false;
				downdirect = true;
				leftdirect = false;
			}//ruch w dó³

		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}

	}
	
	//------------------------------sprawdzanie kolizji--------------------------------------
	
	private void checkCollision(){
		for(int i = dots; i > 0; i--){
			//je¿eli snake uderzy w jak¹œ swoj¹ czeœæ to koniec gry 
			if((i > 2) && (x[0] == x[i]) && (y[0] == y[i])){
				inGame = false;
			}
		} 
		
		//kolizja w wyniku zderzenia ze scian¹
		if(x[0] > B_WIDTH){
			inGame = false;
		}
		
		if(y[0] < 10){
			inGame = false;
		}
		
		if(x[0] < 10){
			inGame = false;
		}
		
		if(y[0] > B_HEIGH){
			inGame = false;
		}
		
		if(dots <= 2){
			inGame = false;
		}
	}
	
	//---------------------------------------ruch wê¿a------------------------
	private void snakeMove(){
		
		//przesuwanie wszystkich czeœci wê¿a
		for(int i = dots; i > 0; i--){
			x[i] = x[(i - 1)];
			y[i] = y[(i - 1)];
			
		}
		
		//ruch do góry
		if(updirect){
			y[0] -= DOT_SIZE;
		}
		//ruch w dó³
		if(downdirect){
			y[0] += DOT_SIZE;
		}
		//ruch w prawo
		if(rightdirect){
			x[0] += DOT_SIZE;
		}
		//ruch w lewo
		if(leftdirect){
			x[0] -= DOT_SIZE;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(inGame){
			
			checkElements();
			checkCollision();
			snakeMove();
			repaint();
		}
		else{
			
			inGame = true;
			GameOverWindow gow = new GameOverWindow();
			time.stop();
			
		}
		
	}
}
