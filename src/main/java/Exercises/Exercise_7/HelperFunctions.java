/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_7;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 *
 * @author original
 */
public class HelperFunctions {

    public HelperFunctions() {
    }
    
    public void graphCategoryChart_PassengerAges(List<TitanicPassenger> passengerList) {
        List<Float> ages = passengerList.stream ().map (TitanicPassenger::getAge).limit (5).collect (Collectors.toList ());
        List<String> names = passengerList.stream ().map (TitanicPassenger::getName).limit (5).collect (Collectors.toList ());
        
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder ().width (700).height (700).title ("Age Histogram").xAxisTitle ("Names").yAxisTitle ("Age").build ();
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        chart.addSeries ("Passenger's Ages", names, ages);
        new SwingWrapper (chart).displayChart ();
    }
    
    public void scatter_PassengerAgesAndFares(List<TitanicPassenger> passengerList)
    {
        List<Float> ages = passengerList.stream ().map (TitanicPassenger::getAge).collect (Collectors.toList());
        List<Float> fares = passengerList.stream ().map (TitanicPassenger::getFare).collect (Collectors.toList ());
        
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
 
        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.addSeries("Passenger's Ages for Fares",  ages,fares);
        new SwingWrapper(chart).displayChart();

    }
    
    public void PieChart_PassengerAgesAndFares(List<TitanicPassenger> passengerList)
    {
        List<Float> ages = passengerList.stream ().map (TitanicPassenger::getAge).distinct().collect (Collectors.toList());
        List<String> tickets = passengerList.stream ().map (TitanicPassenger::getTicket).distinct().collect (Collectors.toList());
        
        // Create Chart
        PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

        // Customize Chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(AnnotationType.Label);
        chart.getStyler().setAnnotationDistance(.82);
        chart.getStyler().setPlotContentSize(.9);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
        
        for(int i =0 ; i<10;i++)
        {
            chart.addSeries(tickets.get(i),ages.get(i));

        }

        new SwingWrapper(chart).displayChart();

    }
    
}
