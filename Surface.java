



public class Surface {
	protected boolean isVertical;
	protected boolean isBound;
	protected int actionType;
	protected String toPrint;
	protected boolean isDestructible;
	protected int life;
	private float pos[] = new float[4];
	public Surface(float lx,float hx,float ly, float hy, boolean vertical, boolean isBound){
		this.pos[0]=lx;
		this.pos[1]=hx;
		this.pos[2]=ly;
		this.pos[3]=hy;
		this.isVertical=vertical;
		this.isBound=isBound;
		this.actionType=0;
		this.toPrint="|";
		
	}
	public Surface(float lx,float hx,float ly, float hy, boolean vertical, boolean isBound, int actionType){
		this.pos[0]=lx;
		this.pos[1]=hx;
		this.pos[2]=ly;
		this.pos[3]=hy;
		this.isVertical=vertical;
		this.isBound=isBound;
		this.actionType=actionType;
		if (this.actionType==1) {
			this.toPrint="\033[34m|\033[0m";
		}
			else {
				if (this.actionType==2) {
					this.toPrint="\033[35m|\033[0m";
				}
				
				else {
					if (this.actionType==3) {
						this.toPrint="\033[32m|\033[0m";
					}
					else {
						if (this.actionType==4) {
							this.toPrint="\033[36m|\033[0m";
						}
						else {
							if (this.actionType==5) {
								this.toPrint="\033[33m|\033[0m";
							}
							
							else {
								this.toPrint="|";
							}
				}
				}
				}
			}
	}
	public String getCaractere() {
		return this.toPrint;
	}
	public int getLife() {
		return this.life;
	}
	
	public int getActionType(){
		return this.actionType;
	}
	public float getLX(){
		return this.pos[0];
	}
	public float getHX(){
		return this.pos[1];
	}
	public float getLY(){
		return this.pos[2];
	}
	public float getHY(){
		return this.pos[3];
	}
	public boolean getVertical(){
		return this.isVertical;
	}
	public boolean isBound(){
		return this.isBound;
	}
	public void setBound(boolean isBound){
		this.isBound=isBound;
	}
	public void decreaseLife(int hit) {
		if (this.isDestructible) {
			this.life-=hit;
		}
	}	
	public void setLX(float x){
		this.pos[0]=x;
	}
	public void setHX(float x){
		this.pos[1]=x;
	}
	public void setLY(float y){
		this.pos[2]=y;
	}
	public void setHY(float y){
		this.pos[3]=y;
	}
	public void setVertical(boolean vertical){
		this.isVertical=vertical;
	}
	public float trajectoryModifier(float a, float yb){
		if (this.isVertical){
			return a;
		}
		else {
			return -a;
		}
	}
	public boolean sensModifier(boolean sens){
		if (this.isVertical){
			return !sens;
		}
		else {
			return sens;
		}
	}
	
}
