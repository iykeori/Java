import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class bullet extends Coke implements Runnable {
	
	private Boolean visible;
	private Boolean moving;
	private int delay;
	private int bulletType;
	private int bulletNumber;
	private static int score;
	
	private Thread t;
	
	private JLabel BulletLabel;
	private TheCar myCar;
	private warPlane myPlane;
	private JLabel CarLabel;
//	private JLabel PlaneLabel;
	//private JButton AnimationButton;

	//constructor
	public bullet() {
		super(0,0,"bullet.png",10,20);
		moving =false;
		visible = false;
		bulletType=0;
		score =0;
	}
	// set bullet no func
	public void setBulletNumber(int temp) {
		bulletNumber=temp;
	}
	//function get bullet no
	public int getBulletNumber() {
		return bulletNumber;
	}

	public void setBulletType(int temp) {
		bulletType=temp;
	}
	//getters and setters
	public Boolean getVisible() {
		return visible;
	}
	public static int getScore() {
		return score;
	}
	//setVisible Function
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	//getMoving function
	public Boolean getMoving() {
		return moving;
	}
	//setMoving Function
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	//Display function
	public void Display() {
		System.out.println("X,Y:"+ cokeX +","+cokeY+" / V: " + visible + " / m: " + moving);
	}
	//show function
	public void show() {
		this.visible =true;
	}
	//hide function
	public void hide() {
		this.visible = false;
	}
	//stop bullet function
	public void stopBullet() {
		this.moving = false;
		
	}
	//move bullet function
	public void moveBullet(int delay) {
		this.delay = delay;
		this.moving = true;
		t = new Thread(this, "Bullet");
		t.start();		
	}

	@Override
	public void run() {
		if (bulletType==1) {
			moveBullet1();			
		}
		if (bulletType==2) {
			moveBullet2();			
		}		
	}
	
//	public void setPlaneLabel(JLabel temp) {
//		this.PlaneLabel = temp;
//	}
	public void setCarLabel(JLabel temp) {
		this.CarLabel = temp;
	}
	public void setBulletLabel(JLabel temp) {
		this.BulletLabel = temp;
	}
	public void setCar(TheCar temp) {
		this.myCar = temp;
	}
	public void setPlane(warPlane temp) {
		this.myPlane = temp;
	}
//	public void setAnimationButton(JButton temp) {
//		this.AnimationButton = temp;
//	}
	
	private void moveBullet1() {
		//this function is primarily for the bullet falling from the plane to the car
		this.setCokeName("bullet.png");
		myCar.setCokeName("car.png");
	
		CarLabel.setIcon(new ImageIcon(
				getClass().getResource("car.png")
				));
		
		BulletLabel.setIcon(new ImageIcon(
				getClass().getResource("bullet.png")
				));
		//AnimationButton.setText("Run");
		BulletLabel.setVisible(true);
		try {
			Thread.sleep(delay);
		}catch (Exception e) {
			
		}

		BulletLabel.setVisible(true);
		
		while (moving) {
			//moving code
			delay = 0;
			
			int bX = this.getCokeX();
			int bY = this.getCokeY();
			
			// bullet from top to down
			bY += GameProperties.CHARACTER_STEP * 2;
			
			if (bY > GameProperties.SCREEN_HEIGHT) {
				this.moving = false;
			}			
			
			this.setCokeX(bX);
			this.setCokeY(bY);	
			BulletLabel.setLocation(this.cokeX,this.cokeY);

			//detect bullet collision with car
			Rectangle rCar = myCar.getRectangle();
			Rectangle rBullet = this.r;
			
			if (rBullet.intersects(rCar)) {
				System.out.println("jam");
				this.setCokeName("boom1.png");
				BulletLabel.setIcon(new ImageIcon(
						getClass().getResource("boom1.png")
						));
				
				try {
					Thread.sleep(400);
				}catch (Exception e) {
					
				}
				
				score++;
				CarLabel.setLocation(500, 470);
//				CarLabel.resetKeyboardActions();			
			}
			
			//pause
			try {
				Thread.sleep(100);
			}catch (Exception e) {
				
			}
		}

	}

	private void moveBullet2() {
		this.setCokeName("bullet2.png");
		myCar.setCokeName("car.png");

		
		CarLabel.setIcon(new ImageIcon(
				getClass().getResource("car.png")
				));
		
		BulletLabel.setIcon(new ImageIcon(
				getClass().getResource("bullet2.png")
				));
		//AnimationButton.setText("Run");
		BulletLabel.setVisible(true);
		try {
			Thread.sleep(delay);
		}catch (Exception e) {
			
		}

		BulletLabel.setVisible(true);
		
		while (moving) {
			//moving code
			delay = 0;
			
			int bX = this.cokeX;
			int bY = this.cokeY;
			System.out.println("bullet 2["+this.bulletNumber+"] is at:" + this.cokeX + ", "+ this.cokeY);
			

			bY -= 2* GameProperties.CHARACTER_STEP;
			
			if (bY < 0) {
				this.moving = false;
			}			
			
			this.setCokeX(bX);
			r.x = this.cokeX;
			this.setCokeY(bY);
			r.y = this.cokeY;
			
			BulletLabel.setVisible(true);
			BulletLabel.setLocation(this.cokeX,this.cokeY);
					
			//detect bullet collision with car
			Rectangle rPlane = myPlane.getRectangle();
			Rectangle rBullet = this.r;
			
			if (rBullet.intersects(rPlane)) {
				System.out.println("jam2");
				this.setCokeName("boom1.png");
				BulletLabel.setIcon(new ImageIcon(
						getClass().getResource("boom1.png")
						));
				//pause
				try {
					Thread.sleep(400);
				}catch (Exception e) {
					
				}
				this.setCokeName("bullet2.png");
				BulletLabel.setIcon(new ImageIcon(
						getClass().getResource("bullet2.png")
						));
				
				//score++;

			}
			//pause
			try {
				Thread.sleep(100);
			}catch (Exception e) {
				
			}
		}
		
		this.visible=false;
		BulletLabel.setVisible(false);
		
	}
}




