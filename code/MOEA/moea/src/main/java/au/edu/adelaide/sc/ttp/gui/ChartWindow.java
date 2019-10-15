package au.edu.adelaide.sc.ttp.gui;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ChartWindow extends JFrame {

	private static final long serialVersionUID = -7174319065841845209L;
	
	public static final int MAX_SERIES = 20000;
	public static final int MIN_SERIES = 0;
	
	private static final ChartWindow chartsFrame = new ChartWindow();
	
	private TitledBorder border = new TitledBorder(new EmptyBorder(10, 60, 40, 60));

	private MoeaChartPanel totalChart = null;

	private MoeaChartPanel currentChart = null;

	public ChartWindow() {
		initialize();
		
	}
	
	private void initialize() {

		setTitle("Charts");

		setBounds(10, 0, 600, 802);
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		border.setTitlePosition(TitledBorder.BOTTOM);
		
		totalChart = new MoeaChartPanel("Overall", MAX_SERIES);
		totalChart.setBounds(50, 10, 450, 360);
		
		currentChart = new MoeaChartPanel("Current", MIN_SERIES);
		currentChart.setBounds(50, 380, 450, 360);
		
		JPanel contentPanel = new JPanel();
		
		contentPanel.add(totalChart);
		contentPanel.add(currentChart);
		
		contentPanel.setLayout(null);
		contentPanel.setBorder(border);
		contentPanel.setBackground(totalChart.getBackground());
		
		setContentPane(contentPanel);

		this.revalidate();
	}
	

	public void addChartValues(double[] xValues, double[] yValues) {
		totalChart.addValues(xValues, yValues);
		currentChart.addValues(xValues, yValues);
	}


	public static ChartWindow getInstance() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chartsFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return chartsFrame;
	}

	public void setChartTitle(String string) {
		if (string == null) return;
		chartsFrame.setTitle(string);
		chartsFrame.repaint();
		
	}

}

