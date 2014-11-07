

//public class Balle extends Projectil {
public class Balle extends Projectil2 {
	public static int nbBalle=0;
	private String toPrint;
	
	public Balle(float x, float y, float a, boolean toTheRight, boolean leftPlayer ){
		super(x,y,a,toTheRight,leftPlayer);
		this.toPrint="\033[31mO\033[0m";
		nbBalle ++;
	}
	public String getCaractere(){
		return this.toPrint;
	}
	
	

}
