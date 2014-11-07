import java.util.Timer;
import java.util.TimerTask;


public class Settings {
	public static int stepX=1;
	public static int sleepTime=20;	
	public static int topo=50;
	public static int xGrid=100;
	public static int yGrid=50;
	public static float balleX=50;
	public static float balleY=25;
	public static float balleA=(float) 0;
	public static float aLim=(float) 1;
	public static float pStep=(float) 1;
	public static boolean wallBound=true;
	public static int bonusProbability=500;
	public static float stepProjectil= (float) 1;
	
	public static void hightSpeed() {
		if (sleepTime>=20) {
			sleepTime-=5;
			timing(10000);
		}
	
	}
	private static void timing(int delay){
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				sleepTime+=5;
				// TODO Auto-generated method stub
				
			}
		},delay); 

		
		
		
		
	}
	
	
	
}
	


