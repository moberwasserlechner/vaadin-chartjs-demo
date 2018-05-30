package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.TimeLineDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScaleOptions;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UIScope
@SpringView
public class TimeScaleChartView extends AbstractChartView {

    private static final long serialVersionUID = -4668420742225695694L;

    @Override
    public Component getChart() {

    	LocalDateTime t = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);

        LineChartConfig config = new LineChartConfig();
        config.data()
	        .addDataset(new TimeLineDataset().label("My First dataset").fill(false))
	        .addDataset(new TimeLineDataset().label("My Second dataset").fill(false))
	        .addDataset(new TimeLineDataset().label("Hidden dataset").hidden(true))
            .and()
        .options()
            .responsive(true)
            .title()
            .display(true)
            .text("Chart.js Line Chart")
            .and()
        .tooltips()
            .mode(InteractionMode.INDEX)
            .intersect(false)
            .and()
        .hover()
            .mode(InteractionMode.NEAREST)
            .intersect(true)
            .and()
        .scales()
		    .add(Axis.X, new TimeScale()
		  	.time()
		  		.min(t.minusHours(9))
		  		.max(t)
		  		.stepSize(2)
		  		.unit(TimeScaleOptions.Unit.HOUR)
		  		.displayFormats()
		  			.hour("DD.MM HH:mm") // german date/time format
		  			.and()
		  	.and()
	    )
        .add(Axis.Y, new LinearScale()
                .display(true)
                .scaleLabel()
                    .display(true)
                    .labelString("Value")
                    .and()
                .ticks()
                    .suggestedMin(-10)
                    .suggestedMax(250)
                    .and()
                .position(Position.RIGHT))
        .and()
        .done();

        // add random data for demo
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
        	TimeLineDataset lds = (TimeLineDataset) ds;
            lds.borderColor(ColorUtils.randomColor(.4));
            lds.backgroundColor(ColorUtils.randomColor(.1));
            lds.pointBorderColor(ColorUtils.randomColor(.7));
            lds.pointBackgroundColor(ColorUtils.randomColor(.5));
            lds.pointBorderWidth(1);
            for (int i = 0; i < 10; i++) {
                lds.addData(t.minusHours(10).plusHours(i), DemoUtils.randomScalingFactor());
            }
            t = t.plusMinutes(15);
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) ->
            DemoUtils.notification(a, b, config.data().getDatasets().get(a)));

        return chart;
    }

}
