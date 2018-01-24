package com.byteowls.vaadin.chartjs.demo.ui.charts;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.ScatterChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.ScatterDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScaleOptions;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class ScatterTimeChartView extends AbstractChartView {

    private static final long serialVersionUID = -4668420742225695694L;

    @Override
    public Component getChart() {
    	
    	LocalDateTime t = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
    	
        ScatterChartConfig config = new ScatterChartConfig();
        config
            .data()
            .addDataset(new ScatterDataset().label("My First dataset").xAxisID("x-axis-1").yAxisID("y-axis-1"))
            .and();
        config.
            options()
                .title()
                    .display(true)
                    .text("Chart.js Scatter Time Chart")
                    .and()
                .scales()
                    .add(Axis.X, new TimeScale()
                		.time()
                			.min(t.minusHours(9))
                			.max(t)
                			.stepSize(2)
                			.unit(TimeScaleOptions.Unit.HOUR)
                			.displayFormats()
                				.hour("DD.MM HH:mm")
                				.and()
                		.and()
                    )
                    .add(Axis.Y, new LinearScale().display(true).position(Position.LEFT).id("y-axis-1"))
                    .and()
               .zoom().enabled(true).and()
               .elements()
               		.point().radius(0).and()
               		.and()
               .legend().display(false).and()
               .done();
        
        System.out.println( config.buildJson() );

        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            ScatterDataset lds = (ScatterDataset) ds;
            lds.borderColor(ColorUtils.randomColor(.4));
            lds.backgroundColor(ColorUtils.randomColor(.1));
            lds.pointBorderColor(ColorUtils.randomColor(.7));
            lds.pointBackgroundColor(ColorUtils.randomColor(.5));
            lds.pointBorderWidth(1);
            for (int i = 0; i < 10; i++) {
                lds.addData(DemoUtils.toMillis(t.minusHours(i)), DemoUtils.randomScalingFactor());
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) ->
            DemoUtils.notification(a, b, config.data().getDatasets().get(a)));

        return chart;
    }

}
