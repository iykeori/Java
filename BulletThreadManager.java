import javax.swing.JLabel;

public class BulletThreadManager implements Runnable {
	
	private Thread t;
	private warPlane myPlane;
	private JLabel bulletLabel[];
	private JLabel planeLabel;
	private bullet myBullet[];
	private boolean run;
	
	
	//constructor
	public BulletThreadManager(warPlane t1, JLabel t2, JLabel[] t3, bullet[] t4) {
		t = new Thread(this, "Bullet Manager");
		myPlane = t1; planeLabel = t2; bulletLabel = t3; myBullet = t4;
		run=false;
		
	}
	public void setRunning(Boolean temp) {
		run = temp;
	}
	
	public void startBullets() {
		run=true;
		t.start();	
	}
	

	@Override
	public void run() {
		
		int bulletNum=0;
			
			while(run) {
				//show label
				//set position from plane
				int hX = myPlane.getCokeX()+myPlane.getCokeW()/2;
				int hY = myPlane.getCokeY()+myPlane.getCokeH()-50;
				System.out.println("Plane: " + hX + ", " + hY);
				
				myBullet[bulletNum].setCokeX(hX);
				myBullet[bulletNum].setCokeY(hY);
				myBullet[bulletNum].Display();
				bulletLabel[bulletNum].setVisible(true);
				bulletLabel[bulletNum].setLocation(hX, hY);
				//start thread
				//System.out.println(bulletNum + " ~ " + myBullet[bulletNum].getBulletNumber() + " / " + hX + ", " + hY + " / " + myBullet[bulletNum].getCokeX() + ", " + myBullet[bulletNum].getCokeY());
				myBullet[bulletNum].moveBullet(0);
				bulletNum++;
				if (bulletNum > 100) bulletNum = 0;
				try {
					Thread.sleep(1000);
				}catch (Exception e) {
					
				}			
			}
	}

}
