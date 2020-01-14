
import java.awt.event.KeyEvent;


import javax.swing.JLabel;

public class TheCar extends Coke {
		
	private JLabel CarLabel;
	private JLabel BulletLabel2[];
	private bullet myBullet2[];
	private int currentBullet;
		
	public TheCar() {
		super(0,0,"car.png",100, 125);
		currentBullet=-1;
	}
		
	public void setCarLabel (JLabel ca) {
		CarLabel = ca;
	}
	public void setBulletArray(bullet [] temp) {
		this.myBullet2 = temp;
	}
	
	public void setBulletLabel(JLabel [] temp) {
		this.BulletLabel2 = temp;
	}

	public void moveCar (KeyEvent e) {
		//DoctorLabel.setLocation(0,0);
		int caX = this.cokeX;
		int caY = this.cokeY;
			
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			caY += GameProperties.CHARACTER_STEP;
			if (caY > GameProperties.SCREEN_HEIGHT) {
				caY = -1 * this.cokeH;
			}
		} else 
		if (e.getKeyCode()==KeyEvent.VK_UP) {				
			caY -= GameProperties.CHARACTER_STEP;
			if (caY < -1 * this.cokeH) {
				caY = GameProperties.SCREEN_HEIGHT;
			}
		} else 
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			caX -= GameProperties.CHARACTER_STEP;
			if (caX < -1 * this.cokeW) {
				caX = GameProperties.SCREEN_WIDTH;
			}
		} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			caX += GameProperties.CHARACTER_STEP;
			if (caX > GameProperties.SCREEN_WIDTH) {
				caX = -1 * this.cokeW;
			}
				
		}
		
		this.cokeX = caX;
		r.x = this.cokeX;
		this.cokeY = caY;
		CarLabel.setLocation(this.cokeX, this.cokeY);
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			
			currentBullet++;
			myBullet2[currentBullet].setBulletType(2);
			myBullet2[currentBullet].setCokeX(this.cokeX);
			myBullet2[currentBullet].setCokeY(this.cokeY);
			myBullet2[currentBullet].moveBullet(0);
				
			
				//pause
				try {
					Thread.sleep(100);
				}catch (Exception c) {
					
				}
			}

		} 
	}



