package client.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import client.model.Parameters;

public class PlotPanel extends JPanel{
	private StatusPanel statusPanel;
	private StepLineChartPanel blickChart;
	private StepLineChartPanel winkLeftChart;
	private StepLineChartPanel winkRightChart;
	private StepLineChartPanel lookDirection;
	private LineChartPanel raiseBrowChart;
	private LineChartPanel furrowBrowChart;
	private LineChartPanel smileChart;
	private LineChartPanel clenchChart;
	private LineChartPanel smirkLeftChart;
	private LineChartPanel smirkRightChart;
	private LineChartPanel laughChart;

	public PlotPanel() {
		setLayout(new GridLayout(12,1));
		statusPanel = new StatusPanel();
		blickChart = new StepLineChartPanel("blink");
		winkLeftChart = new StepLineChartPanel("wink left");
		winkRightChart = new StepLineChartPanel("wink right");
		lookDirection = new StepLineChartPanel("look direction");
		raiseBrowChart = new LineChartPanel("raise brow");
		furrowBrowChart = new LineChartPanel("furrow brow");
		smileChart = new LineChartPanel("smile");
		clenchChart = new LineChartPanel("clench");
		smirkLeftChart = new LineChartPanel("smirk left");
		smirkRightChart = new LineChartPanel("smirk right");
		laughChart = new LineChartPanel("laugh");
		add(statusPanel);
		add(blickChart);
		add(winkLeftChart);
		add(winkRightChart);
		add(lookDirection);
		add(raiseBrowChart);
		add(furrowBrowChart);
		add(smileChart);
		add(clenchChart);
		add(smirkLeftChart);
		add(smirkRightChart);
		add(laughChart);
	}
	
	public void add(Parameters param) {
		blickChart.addData(param.getEye().getBlink(),param.getTime());
		winkLeftChart.addData(param.getEye().getWinkLeft(), param.getTime());
		winkRightChart.addData(param.getEye().getWinkRight(), param.getTime());
		if (param.getEye().getLookLeft()||param.getEye().getLookRight()) {
			lookDirection.addData(true, param.getTime());	
		} else {
			lookDirection.addData(false, param.getTime());
		}
		raiseBrowChart.addData(param.getUpperFace().getRaiseBrow(), "raise brow", Double.toString(param.getTime()));
		furrowBrowChart.addData(param.getUpperFace().getFurrowBrow(), "burrow brow", Double.toString(param.getTime()));
		smileChart.addData(param.getLowerFace().getSmile(), "smile", Double.toString(param.getTime()));
		clenchChart.addData(param.getLowerFace().getClench(), "clench", Double.toString(param.getTime()));
		smirkLeftChart.addData(param.getLowerFace().getSmirkLeft(), "smirk left", Double.toString(param.getTime()));
		smirkRightChart.addData(param.getLowerFace().getSmirkRight(), "smirk right", Double.toString(param.getTime()));
		laughChart.addData(param.getLowerFace().getLaugh(), "laugh", Double.toString(param.getTime()));		
	}
}
