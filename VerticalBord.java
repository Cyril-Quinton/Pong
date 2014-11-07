
public class VerticalBord extends Surface {
	private boolean isLeftWall;
	public VerticalBord(float lx,float hx,float ly, float hy, boolean vertical, boolean isBound ,boolean isLeftWall){
		super(lx,hx,ly,hy,vertical,isBound);
		this.isLeftWall=isLeftWall;
	}
	public boolean isLeftWall(){
		return this.isLeftWall;
	}

}
