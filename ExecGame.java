import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExecGame extends JFrame implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	//declare our coke object
	private TheCar myCar;
	private warPlane myPlane;
	private bullet myBullet[];
	private bullet myBullet2[];
	
	//image icons to hold the png graphics
	private ImageIcon CarImage;
	private ImageIcon PlaneImage;
	private ImageIcon BulletImage;
	private ImageIcon BulletImage2;
	private ImageIcon wallpaper;
	private ImageIcon jam;
	
	//labels to display the image icons
	private JLabel CarLabel, PlaneLabel, wPaperLabel,jamLabel, BulletLabel2[], BulletLabel[];
	//button to control the War Plane
	private JButton HidePlaneButton, AnimationButton, scoreButton;
	
	private BulletThreadManager BTM;
	
	//container for graphics
	private Container content;
	
	
	public ExecGame() {
		
		//initialize variables
		myCar = new TheCar();
		myPlane = new warPlane();
		myBullet = new bullet [10000];
		BulletLabel = new JLabel[10000];
		for (int i=0; i<10000; i++ ) {
			myBullet[i] = new bullet();
			BulletLabel[i] = new JLabel();
			BulletLabel[i].setVisible(false);
			myBullet[i].setBulletType(1);
			myBullet[i].setPlane(myPlane);
			myBullet[i].setBulletNumber(i);
		}
		
		myBullet2 = new bullet [10000];
		BulletLabel2 = new JLabel[10000];
		for (int i=0; i<10000; i++ ) {
			myBullet2[i] = new bullet();
			BulletLabel2[i] = new JLabel();
			BulletLabel2[i].setVisible(false);
			myBullet2[i].setBulletType(2);
			myBullet2[i].setPlane(myPlane);
			myBullet2[i].setBulletNumber(i);
		}
		jamLabel = new JLabel();
		CarLabel = new JLabel();
		PlaneLabel = new JLabel();
		HidePlaneButton = new JButton();
		AnimationButton = new JButton();
		wPaperLabel = new JLabel();
		scoreButton = new JButton();
		for (int i=0; i<10000; i++) {
			myBullet[i].setBulletLabel(BulletLabel[i]);
			myBullet[i].setCarLabel(CarLabel);
			myBullet[i].setCar(myCar);
		}
		for (int i=0; i<10000; i++) {
			myBullet2[i].setBulletLabel(BulletLabel2[i]);
			myBullet2[i].setCarLabel(CarLabel);
			myBullet2[i].setCar(myCar);
		}
		//myBullet.setBulletLabel(BulletLabel2);
		myPlane.setPlaneLabel(PlaneLabel);
		myPlane.setPlane(myPlane);
		
		
		
		System.out.println("bullet: "+myBullet[0].getCokeName());
		CarImage = new ImageIcon( getClass().getResource(myCar.getCokeName()));
		PlaneImage = new ImageIcon( getClass().getResource(myPlane.getCokeName()));
		BulletImage = new ImageIcon( getClass().getResource(myBullet[0].getCokeName()));
		BulletImage2 = new ImageIcon(
				getClass().getResource("bullet2.png")
				);
		wallpaper = new ImageIcon(
				getClass().getResource("bg.png")
				);
		jam = new ImageIcon(
				getClass().getResource("boom.png")
				);
		content = getContentPane();
		
		//set up GUI
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content.setBackground(Color.white);
		setLayout(null);
		
		//set up Jam notification
		jamLabel.setSize(500,500);
		jamLabel.setIcon(jam);
		
		jamLabel.setLocation(100,140);
		add(jamLabel);
		jamLabel.setVisible(false);
		if (bullet.getScore() > 0) {
			jamLabel.setVisible(true);
			for(int i=0; i>=1000; i++) {
				
			}

			jamLabel.setVisible(false);
		}
		
		//set up the Car
		myCar.setCarLabel(CarLabel);
		myCar.setCokeX(500);
		myCar.setCokeY(438);
		myCar.setBulletArray(myBullet2);
		myCar.setBulletLabel(BulletLabel2);
		CarLabel.setIcon(CarImage);
		CarLabel.setSize(myCar.getCokeW(),myCar.getCokeH());
		CarLabel.setLocation(myCar.getCokeX(),myCar.getCokeY());
		add(CarLabel);
		
		//set up the myPlane
		myPlane.setCokeX(70);
		myPlane.setCokeY(30);
		PlaneLabel.setIcon(PlaneImage);
		PlaneLabel.setSize(myPlane.getCokeW(),myPlane.getCokeH());
		PlaneLabel.setLocation(myPlane.getCokeX(),myPlane.getCokeY());
				
		myPlane.setMoving(false);
		myPlane.setVisible(true);
		add(PlaneLabel);
		
		//set up the bullet
		for(int i=0; i <10000; i++) {
			//int add = 0;
			myBullet[i].setCokeX(400);
			myBullet[i].setCokeY(150);
			BulletLabel[i].setIcon(BulletImage);
			BulletLabel[i].setSize(myBullet[i].getCokeW(),myBullet[i].getCokeH());
			BulletLabel[i].setLocation(myBullet[i].getCokeX(),myBullet[i].getCokeY());
					
			myBullet[i].setMoving(false);
			myBullet[i].setVisible(true);
			//add = add + 4;
			add(BulletLabel[i]);
		}
		
		myPlane.setBulletArray(myBullet);
		myPlane.setBulletLabel(BulletLabel);
		
		//set up the bullet2
		for(int i=0; i <10000; i++) {
			myBullet2[i].setCokeX(500);
			myBullet2[i].setCokeY(440);
			BulletLabel2[i].setIcon(BulletImage2);
			BulletLabel2[i].setSize(myBullet2[i].getCokeW(),myBullet2[i].getCokeH());
			BulletLabel2[i].setLocation(myBullet2[i].getCokeX(),myBullet2[i].getCokeY());							
			myBullet2[i].setMoving(false);
			myBullet2[i].setVisible(true);
			add(BulletLabel2[i]);
		}	
		myCar.setBulletArray(myBullet2);
		myCar.setBulletLabel(BulletLabel2);

				
		
		HidePlaneButton.setLocation(GameProperties.SCREEN_WIDTH-100, GameProperties.SCREEN_HEIGHT-120);
		HidePlaneButton.setSize(100, 100);
		HidePlaneButton.setText("Hide");
		HidePlaneButton.addActionListener(this);
		HidePlaneButton.setFocusable(false);
		add(HidePlaneButton);
		
		AnimationButton.setLocation(GameProperties.SCREEN_WIDTH-100, GameProperties.SCREEN_HEIGHT-250);
		AnimationButton.setSize(100, 100);
		AnimationButton.setText("Run");
		AnimationButton.addActionListener(this);
		AnimationButton.setFocusable(false);
		add(AnimationButton);
		
		scoreButton.setLocation(GameProperties.SCREEN_WIDTH-100, GameProperties.SCREEN_HEIGHT-380);
		scoreButton.setSize(100, 100);
		scoreButton.setText("Score");
		scoreButton.addActionListener(this);
		scoreButton.setFocusable(false);
		add(scoreButton);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		wPaperLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		wPaperLabel.setIcon(wallpaper);
		
		wPaperLabel.setLocation(0,0);
		add(wPaperLabel);
		
		BTM = new BulletThreadManager(myPlane, PlaneLabel, BulletLabel, myBullet);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String [] args) {
		ExecGame myGame = new ExecGame();
		myGame.setVisible(true);
		
	}
	public static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next() ) {
			int id = rs.getInt("id");
			String score = rs.getString("score");

			
			System.out.println("id: " + id);
			System.out.println("score: " + score);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == HidePlaneButton) {
			//System.out.println("Show or Hide plane");
			if (myPlane.getVisible()) {
				//Plane is currently visible
				//myPlane.setVisible(false);
				myPlane.hide();
				HidePlaneButton.setText("Appear");
				
			} else {
				//Plane is currently hidden
				//myPlane.setVisible(true);
				myPlane.show();
				HidePlaneButton.setText("Ghost");
				
			}
			PlaneLabel.setVisible(myPlane.getVisible());
			
			
		} else if (e.getSource() == AnimationButton) {
			System.out.println("call thread code");
			if (myPlane.getMoving()) {
				//Plane is moving
				myPlane.stopPlane();

				AnimationButton.setText("Hover");
			} else {
							
				//Plane is not moving
				myPlane.movePlane();
				
				BTM.startBullets();
				
				//myBullet.moveBullet();
				AnimationButton.setText("Pause");
			}
		} else if (e.getSource() == scoreButton) {
			JOptionPane.showMessageDialog(null, "Your Score is : "+ bullet.getScore());
			
			Connection conn = null;
			Statement stmt = null;
			
			try {
				//load the DB driver
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:game.db";
				conn = DriverManager.getConnection(dbURL);
				if (conn != null) {
					System.out.println("Connection established");
					
					conn.setAutoCommit(false);
					
					
					stmt = conn.createStatement();
					String sql = "";
					ResultSet rs = null;
					
					//CREATE TABLE IF NOT EXIST
					sql = "CREATE TABLE IF NOT EXISTS game ("+ 
						  "id INTEGER PRIMARY KEY, " +
						  "name TEXT NOT NULL, " + 
						  "score INT NOT NULL, " + 
						  "wins char(50), " +
						  "sa REAL" + ")";
					stmt.executeUpdate(sql);
					conn.commit();
					
					//Insert data
					String name =JOptionPane.showInputDialog(null, "ENTER YOUR NAME");
					int score = bullet.getScore();
					
//					JOptionPane.showMessageDialog(null, "Your Score is : "+ score);
					
					String wins = "medium"; double sa = 0;
					sql = "INSERT INTO game (name, score, wins, sa) VALUES ('" +
					       name + "', " + score + ", '" + 
					       wins + "', " + sa + ")";
					stmt.executeUpdate(sql);
					conn.commit();


					//Retrieve
					sql = "SELECT * FROM game";
					rs = stmt.executeQuery(sql);
					System.out.println("The current data is => ");
					DisplayRecords(rs);
					rs.close();
									
									
					conn.close();
				} else {
					System.out.println("Cannot establish connection");
				}
							
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			} catch (SQLException c) {
				c.printStackTrace();
			} catch (Exception c) {
				c.printStackTrace();
			} finally {
				//cleanup
			}
			
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		System.out.println("Key has been typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("Key has been pressed");
		myCar.moveCar(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		System.out.println("Key has been released");
	}
	
	
	


}

