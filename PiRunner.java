//package com.g1tab.piProgram;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.util.ArrayList;

public class PiRunner{
	private JProgressBar jimmy;
	private JFrame steve;

	public PiRunner(){
		
	}
	public PiRunner(long Slices, double Increments, int Threads){
		steve= new JFrame();
		steve.setSize(500,125);
		jimmy= new JProgressBar(0,(int)Slices);
		jimmy.setString("Start!");
		jimmy.setStringPainted(true);
		steve.add(jimmy);
		steve.setVisible(true);
		steve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<PiI> threading= new ArrayList<PiI>();
		for (int threads=0; threads<Threads; threads++){
			threading.add(new PiI(threads*Increments*Slices/Threads,(threads+1)*Increments*Slices/Threads, Increments, this));
			threading.get(threads).start();
		}
		try{
			for (int threads=0; threads<Threads; threads++){
				threading.get(threads).join();
			}
		}
		catch(InterruptedException e){
		
		}
		double TotArea=0.0;
		for(int threads=0; threads<Threads; threads++){
			TotArea+=threading.get(threads).getArea();
		}
		System.out.println(TotArea);
		jimmy.setString("DONE!     "+TotArea);
	}
	public synchronized void increasePercent(){
		jimmy.setValue(jimmy.getValue()+1);
		jimmy.setString("Slices: "+jimmy.getValue());
	}
	public static void main(String[] args){
		new PiRunner(Long.parseLong(args[0]), Math.pow(Long.parseLong(args[0]),-1),Integer.parseInt(args[1]));
	}
}