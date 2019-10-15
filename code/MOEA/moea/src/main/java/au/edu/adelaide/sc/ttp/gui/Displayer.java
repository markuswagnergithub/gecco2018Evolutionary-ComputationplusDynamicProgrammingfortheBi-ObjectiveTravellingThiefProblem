package au.edu.adelaide.sc.ttp.gui;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.uma.jmetal.solution.Solution;

public class Displayer{
	
	public static void main(String[] args){
		Random r = new Random();
		
		double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
		double[] yValues = new double[]{71, 61, 51, 41, 31, 21, 11};
		
		for (int i=1;i<100;i++){
		
			for (int j=0;j<xValues.length;j++){
				xValues[j] = xValues[j] + r.nextInt(100);
			}
			Displayer.show(xValues, yValues, i);
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static <S extends Solution<?>> void show(List<S> values, int generation){
		
		double[] xValues = new double[values.size()];
		double[] yValues = new double[values.size()];
		
		for (int i=0;i<values.size();i++){
			xValues[i] = values.get(i).getObjective(1);
			yValues[i] = values.get(i).getObjective(0);
		}
		
		show(xValues, yValues, generation);
	}
	
	public static void show(double[] xValues, double[] yValues, int generation){
		ChartWindow chartWindow = ChartWindow.getInstance();
		chartWindow.addChartValues(xValues, yValues);
		chartWindow.setChartTitle("Current generation:"+generation);
	}

}
