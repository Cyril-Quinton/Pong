

//public class Missile extends Projectil {
public class Missile extends Projectil2 {

	public Missile(float x, float y, float a, boolean toTheRight,
			boolean leftPlayer) {
		super(x, y, a, toTheRight, leftPlayer);
		this.toPrint="\u2015";
	}


}
