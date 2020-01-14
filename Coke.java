import java.awt.Rectangle;

public class Coke {
	
	//define data members, attributes, variables
	protected int cokeX, cokeY; //upper left coordinate
	protected String cokeName;
	protected int cokeW, cokeH;
	protected Rectangle r;
	
	//define getters and setters
	public int getCokeX() {
		return cokeX;
	}
	public void setCokeX(int cokeX) {
		this.cokeX = cokeX;
		r.x = this.cokeX;
	}
	public String getCokeName() {
		return cokeName;
	}
	public void setCokeName(String cokeName) {
		this.cokeName = cokeName;
	}
	public int getCokeY() {
		return cokeY;
	}
	public void setCokeY(int cokeY) {
		this.cokeY = cokeY;
		r.y = this.cokeY;
	}
	public int getCokeH() {
		return cokeH;
	}
	public void setCokeH(int cokeH) {
		this.cokeH = cokeH;
		r.height = this.cokeH;
	}
	public int getCokeW() {
		return cokeW;
	}
	public void setCokeW(int cokeW) {
		this.cokeW = cokeW;
		r.width = this.cokeW;
	}
	public Rectangle getRectangle() {
		return r;
	}
	//constructors
	public Coke() {
		super();
		r = new Rectangle(0,0,0,0);
	}
	public Coke(int cokeX, int cokeY, String cokeName, int cokeW, int cokeH) {
		super();
		this.cokeX = cokeX;
		this.cokeY = cokeY;
		this.cokeName = cokeName;
		this.cokeW = cokeW;
		this.cokeH = cokeH;
		r = new Rectangle(cokeX,cokeY,cokeW,cokeH);
	}
	
	//Display function	
	public void Display() {
		System.out.println("X,Y: " + cokeX + "," + cokeY);
	}
	
	
	
	

}

