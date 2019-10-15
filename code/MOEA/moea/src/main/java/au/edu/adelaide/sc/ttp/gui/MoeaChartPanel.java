package au.edu.adelaide.sc.ttp.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MoeaChartPanel extends ChartPanel {
	private static final long serialVersionUID = -2870510036427059747L;

	private int maxSeries = 10;
	
	private XYSeriesCollection xyDataset = null;
	
	private int seriesCount = 0;
	
	public MoeaChartPanel(String title, int maxSeries){
		this(title, null, null, new XYSeriesCollection());
		this.maxSeries = maxSeries;
		XYPlot plot = (XYPlot)this.getChart().getPlot();
		xyDataset = (XYSeriesCollection)plot.getDataset();
	}

	public MoeaChartPanel(String title, String xAxisLabel, String yAxisLabel,
			XYDataset dataset) {
		super(ChartFactory.createScatterPlot(title, xAxisLabel, yAxisLabel,
				dataset, PlotOrientation.HORIZONTAL, false, false, false));

	}


	public void addValues(double[] xValues, double[] yValues) {
		XYSeries series = new XYSeries(seriesCount++);
		for (int i = 0; i < yValues.length; i++) {
			series.add(xValues[i], yValues[i]);
		}
		if (xyDataset.getSeriesCount() > maxSeries){
			xyDataset.removeSeries(0);
		}
		xyDataset.addSeries(series);
	}

	
}
