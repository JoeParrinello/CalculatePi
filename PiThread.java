import java.util.ArrayList;

public class PiThread extends Thread{
	private double Area;
	private double Start;
	private double Stop;
	private double Increment;
	private PiRunner superClass;
	public PiThread(double start, double stop, double incr, PiRunner supr){
		Start=start;
		Stop=stop;
		Increment=incr;
		superClass=supr;
	
	}
	public void run(){
		for(double x=Start; x<Stop; x+=Increment){
			Area+=Math.sqrt(1-Math.pow(x,2))*Increment*4;
			superClass.increasePercent();
		}

	}
	public double getArea(){
		return Area;
	}
}