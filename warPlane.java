import java.awt.Rectangle;

import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JLabel;

public class warPlane extends Coke implements Runnable {
	
	private Boolean visible;
	private Boolean moving;
	private Thread t;
	private warPlane myPlane;
	private JLabel PlaneLabel;
	private JLabel BulletLabel[];
	private bullet myBullet[];
	
//	
//	private JLabel PlaneLabel;
	private TheCar myCar;
	private JLabel CarLabel;
//	private JButton AnimationButton;
	public warPlane() {
		super(0,0,"plane.png",448,236);
		moving =false;
		visible = true;
	}
	//getters and setters
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	//Display function
	public void Display() {
		System.out.println("X,Y:"+ cokeX +","+cokeY+" / V: " + visible + " / m: " + moving);
	}
	// show function
	public void show() {
		this.visible =true;
	}
	
	//hide function
	public void hide() {
		this.visible = false;
	}
	//stop Plane Function
	public void stopPlane() {
		this.moving = false;
		
	}
	//movePlane function
	public void movePlane() {
		myPlane.moving = true;
		t = new Thread(this, "Plane");
		t.start();	
	}
	//setBulletArray Function
	public void setBulletArray(bullet [] temp) {
		myBullet = temp;
	}
	//setBulletLabel Function
	public void setBulletLabel(JLabel [] temp) {
		BulletLabel = temp;
	}
	
	@Override
	public void run() {
		System.out.println("Plane thread started");
		myPlane.setCokeName("plane.png");
		PlaneLabel.setIcon(new ImageIcon(
			getClass().getResource("plane.png")
				));
//		
//		for (int i =0; i<10000; i++) {
//			myBullet[i].moveBullet(1000*i);
//		}
//		System.out.println("Plane Bullet threads started");
		
		while (moving) {
			//moving code
			int pX = this.cokeX;
			int pY = this.cokeY;
			// Plane moving horizontally						
			pX += GameProperties.CHARACTER_STEP * 2;
			
			if (pX > GameProperties.SCREEN_WIDTH) {
				pX = -1 * this.cokeW;
			} 
						
			this.setCokeX(pX);
			this.setCokeY(pY);
						
			PlaneLabel.setLocation(this.cokeX,this.cokeY);
				
			

			
			//pause
			try {
				Thread.sleep(100);
			}catch (Exception e) {
				
			}
						
		}
		
	
		
	}
	
		public void setPlaneLabel(JLabel temp) {
			this.PlaneLabel = temp;
		}
		public void setPlane(warPlane temp) {
			this.myPlane = temp;
		}
	

}



