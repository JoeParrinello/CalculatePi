/*
*PiThread
*
*Jan-05-2014
*/

import java.util.ArrayList;

public class PiThread extends Thread{
	private double Area;
	private double Start;
	private double Stop;
	private double Increment;
	private PiRunner superClass;
	
	/*
	*	Constructor for when Threads are launched with a GUI
	*	start is the starting number, offset by the number of threads
	*	stop is the ending number, offset by the number of threads
	*	incr is the number each iteration shifts by, also the same number of threads
	*	supr is the PiRunner class, so that people can report back to the GUI
	*/
	public PiThread(double start, double stop, double incr, PiRunner supr){
		Start=start;
		Stop=stop;
		Increment=incr;
		superClass=supr;
	
	}
	/*
	*	Constructor for when Threads are launched without a GUI
	*	start is the starting number, offset by the number of threads
	*	stop is the ending number, offset by the number of threads
	*	incr is the number each iteration shifts by, also the same number of threads
	*/
	public PiThread(double start, double stop, double incr){
		Start=start;
		Stop=stop;
		Increment=incr;
	
	}
	/*
	*	The run method for the thread to launch when it is started
	*/
	public void run(){
		for(double x=Start; x<Stop; x+=Increment){
			Area+=Math.sqrt(1 - Math.pow(x,2)) * Increment * 4;
			if (superClass!=null){
				superClass.increasePercent();
			}
		}

	}
	/*
	*	getArea is a method that returns the calculated area form running the thread
	*	Called remotely from PiRunner
	*/
	public double getArea(){
		return Area;
	}
}