
public class Projectil2 {
	private float coordx;
	private float coordy;
	private float a;
	private boolean toTheRight;
	private boolean leftPlayer;
	protected String toPrint;
	protected int hit;
	protected boolean isBoundy;
	
	public Projectil2(float x, float y, float a, boolean toTheRight, boolean leftPlayer ){
		this.coordx=x;
		this.coordy=y;
		this.a=a;
		this.toTheRight=toTheRight;
		this.leftPlayer=leftPlayer;
		this.toPrint="\033[31mO\033[0m";
	}
	public boolean isBoundy() {
		return this.isBoundy;
	}
	public int getHit() {
		return this.hit;
	}
	public String getCaractere(){
		return this.toPrint;
	}
	public float getx(){
		return this.coordx;
	}
	public float gety(){
		return this.coordy;
	}
	public boolean getSens(){
		return this.toTheRight;
	}
	public void setx(float x){
		this.coordx=x;
	}
	public void sety(float y){
		this.coordy=y;
	}
	public float geta(){
		return this.a;
	}
	public boolean isLeftPlayer(){
		return this.leftPlayer;
	}
	public void seta(float x){
		if (Math.abs(x)<Settings.aLim) {
		
		this.a=x;
		}
	}
	public void setSens(boolean sens){
		this.toTheRight=sens;
	}
	public void setPlayer(boolean isPlayerLeft){
		this.leftPlayer=isPlayerLeft;
	}
	public void move(){
		float stepX= Settings.stepProjectil*(float) Math.cos(Math.atan((float)this.a));
		if (this.toTheRight){
			this.coordx+= stepX;
			
		}
		else {
			this.coordx-=stepX;
		}
		this.coordy+=Settings.stepProjectil*this.a;
		
	}


}


