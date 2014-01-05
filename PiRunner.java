import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.util.ArrayList;

public class PiRunner{
	private JProgressBar sliceProgressBar;
	private JFrame progressBarFrame;

	public PiRunner(){
		
	}
	public PiRunner(long Slices, double Increments, int Threads){
		progressBarFrame= new JFrame();
		progressBarFrame.setSize(500,125);
		sliceProgressBar= new JProgressBar(0,(int)Slices);
		sliceProgressBar.setString("Start!");
		sliceProgressBar.setStringPainted(true);
		progressBarFrame.add(sliceProgressBar);
		progressBarFrame.setVisible(true);
		progressBarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<PiThread> threading= new ArrayList<PiThread>();
		for (int threads=0; threads<Threads; threads++){
			threading.add(new PiThread(threads*Increments*Slices/Threads,(threads+1)*Increments*Slices/Threads, Increments, this));
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
		sliceProgressBar.setString("DONE!     "+TotArea);
	}
	public synchronized void increasePercent(){
		sliceProgressBar.setValue(sliceProgressBar.getValue()+1);
		sliceProgressBar.setString("Slices: "+sliceProgressBar.getValue());
	}
	public static void main(String[] args){
		new PiRunner(Long.parseLong(args[0]), Math.pow(Long.parseLong(args[0]),-1),Integer.parseInt(args[1]));
	}
}