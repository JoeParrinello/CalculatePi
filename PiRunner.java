/*
*PiRunner
*
*Jan-05-2014
*/

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.util.ArrayList;

public class PiRunner{
	private JProgressBar sliceProgressBar;
	private JFrame progressBarFrame;

	public PiRunner(){
		
	}
	/*
	*	Constructor for no GUI launching of program
	*	Threads is the number of PiThreads to be launched
	*	
	*/
	public PiRunner(int totalThreads, long Slices, double Increments){
		ArrayList<PiThread> threading= new ArrayList<PiThread>();
		for (int threads=0; threads<totalThreads; threads++){
			threading.add(new PiThread(threads * Increments * Slices / totalThreads, (threads + 1) * Increments * Slices / totalThreads, Increments));
			threading.get(threads).start();
		}
		try{
			for (int threads=0; threads<totalThreads; threads++){
				threading.get(threads).join();
			}
		}
		catch(InterruptedException e){
		
		}
		double TotArea=0.0;
		for(int threads=0; threads<totalThreads; threads++){
			TotArea+=threading.get(threads).getArea();
		}
		System.out.println(TotArea);
	}
	
	public PiRunner(long Slices, double Increments, int totalThreads){
		progressBarFrame= new JFrame();
		progressBarFrame.setSize(500,125);
		sliceProgressBar= new JProgressBar(0,(int)Slices);
		sliceProgressBar.setString("Start!");
		sliceProgressBar.setStringPainted(true);
		progressBarFrame.add(sliceProgressBar);
		progressBarFrame.setVisible(true);
		progressBarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<PiThread> threading= new ArrayList<PiThread>();
		for (int threads=0; threads<totalThreads; threads++){
			threading.add(new PiThread(threads * Increments * Slices / totalThreads, (threads + 1) * Increments * Slices / totalThreads, Increments, this));
			threading.get(threads).start();
		}
		try{
			for (int threads=0; threads<totalThreads; threads++){
				threading.get(threads).join();
			}
		}
		catch(InterruptedException e){
		
		}
		double TotArea=0.0;
		for(int threads=0; threads<totalThreads; threads++){
			TotArea+=threading.get(threads).getArea();
		}
		System.out.println(TotArea);
		sliceProgressBar.setString("DONE!     "+TotArea);
	}
	
	public synchronized void increasePercent(){
		sliceProgressBar.setValue(sliceProgressBar.getValue()+1);
		sliceProgressBar.setString("Slices: "+sliceProgressBar.getValue());
	}
	
	public static void main(String[] args){
		if (args.length!=0){
			if(args[0].compareTo("-h")==0){
				System.out.println("PiRunner [options] Slices [threads]");
				System.out.println("-nogui         Allows execution without the gui interface");
			}
			else if(args[0].compareTo("-nogui")==0){
				if (args.length==3){
					new PiRunner(Integer.parseInt(args[2]),Long.parseLong(args[1]), Math.pow(Long.parseLong(args[1]),-1));
				}
				else if (args.length==2){
					new PiRunner(1, Long.parseLong(args[1]), Math.pow(Long.parseLong(args[1]),-1));
				}
				else{
					System.out.println("Invalid Input, check -h for format");
				}
			}
			else if (args.length==2){
					new PiRunner(Long.parseLong(args[0]), Math.pow(Long.parseLong(args[0]),-1),Integer.parseInt(args[1]));
			}
			else if (args.length==1){
					new PiRunner(Long.parseLong(args[0]), Math.pow(Long.parseLong(args[0]),-1),1);
			}
			else{
				System.out.println("Invalid Input, check -h for format");
			}
		}
		else{
			System.out.println("No Input variables...");
			System.out.println("Use -h option for input options");
		}
	}
}