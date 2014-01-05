//package com.g1tab.piProgram;

import java.util.ArrayList;
public class PiI extends Thread{
	private double Area;
	private double Sta;
	private double Sto;
	private double Inc;
	private PiRunner superClass;
	public PiI(double start, double stop, double incr, PiRunner supr){
		Sta=start;
		Sto=stop;
		Inc=incr;
		superClass=supr;
	
	}
	public void run(){
		for(double x=Sta; x<Sto; x+=Inc){
			Area+=Math.sqrt(1-Math.pow(x,2))*Inc*4;
			superClass.increasePercent();
		}

	}
	public double getArea(){
		return Area;
	}
}