
public class Plateform extends Surface {
	private float len;

	public Plateform(float lx, float hx, float ly, float hy, boolean vertical, boolean isBound) {
		super(lx, hx, ly, hy, vertical, isBound);
		setLen();
		
		// TODO Auto-generated constructor stub
	}
	public float getLen(){
		return this.len;
	}
	public void setLen() {
		this.len=getHY()-getLY();
	}
	public float trajectoryModifier(float a, float yb){
		float middle = this.getLY() + this.len/2;
		float coef=java.lang.Math.abs(middle-yb)/this.len/2;
		float newA;
		if (yb < middle){
			newA=a-(3*coef);
		}
		else {
			newA=a+(3*coef);
		}
		if (java.lang.Math.abs(newA) < Settings.aLim) {
			return newA;
		}
		else {
			return a;
		}
	}
		
}


