package com.vote.service;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChartPicture {
	public static void main(String[] args) {
		PieDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D(" ��Ŀ���ȷֲ�", // chart
																	// title
				dataset,// data
				true,// include legend
				true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// ͼƬ����ʾ�ٷֱ�:Ĭ�Ϸ�ʽ
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));
		// ���ñ���ɫΪ��ɫ
		chart.setBackgroundPaint(Color.white);
		// ָ��ͼƬ��͸����(0.0-1.0)
		plot.setForegroundAlpha(1.0f);
		// ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)
		plot.setCircular(true);
		// ���������ʽ
		// Font font = new Font("����", Font.BOLDITALIC, 12);
		Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE, 20);
		TextTitle title = new TextTitle(" ��Ŀ״̬�ֲ�");
		title.setFont(font);
		// ��������,�ǳ��ؼ���Ȼ����������,�·�������
		chart.getLegend().setItemFont(font);
		// ����ͼƬ�ĵ�ַPieͼ������
		plot.setLabelFont(font);
		chart.setTitle(title);
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("c:\\AA.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 640, 480, null);
			fos_jpg.close();
		} catch (Exception e) {
		}
	}

	private static PieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(" �г�ǰ��", new Double(10));
		dataset.setValue(" ����", new Double(15));
		dataset.setValue(" �ƻ�", new Double(10));
		dataset.setValue(" ���������", new Double(10));
		dataset.setValue(" ִ�п���", new Double(35));
		dataset.setValue(" ��β", new Double(10));
		dataset.setValue(" ��ά", new Double(10));
		return dataset;
	}
}