import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class ClavierListener implements KeyListener{
	public static boolean lUp=false;
	public static boolean lDown=false;
	public static boolean rUp=false;
	public static boolean rDown=false;
    public void keyPressed(KeyEvent event) {
    	//System.out.println("pressed"+event.getKeyCode());
    	if (event.getKeyCode()==16){
    		lUp=true;
    	}
    	if (event.getKeyCode()==17){
    		lDown=true;
    	}
    	if (event.getKeyCode()==38){
    		rUp=true;
    	}
    	if (event.getKeyCode()==40){
    		rDown=true;
    	}
    }
       
  private void pause(){
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
@Override
public void keyReleased(KeyEvent event) {
	// TODO Auto-generated method stub
	//System.out.println("released"+event.getKeyCode());
	if (event.getKeyCode()==16){
		lUp=false;
	}
	if (event.getKeyCode()==17){
		lDown=false;
	}
	if (event.getKeyCode()==38){
		rUp=false;
	}
	if (event.getKeyCode()==40){
		rDown=false;
	}
}
@Override
public void keyTyped(KeyEvent event) {
	// TODO Auto-generated method stub
	
}  

}
