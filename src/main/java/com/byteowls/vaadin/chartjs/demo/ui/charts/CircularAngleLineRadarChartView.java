package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.RadarChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.RadarDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.RadialLinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class CircularAngleLineRadarChartView extends AbstractChartView {

    private static final long serialVersionUID = -8575881820534087527L;

    @Override
    public Component getChart() {
        RadarChartConfig config = new RadarChartConfig();
        config
            .data()
                .labels("Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running")
                .addDataset(new RadarDataset().label("My First dataset")
                    .backgroundColor(ColorUtils.toRgba(DemoUtils.RGB_ARR_RED, 0.2))
                    .borderColor(ColorUtils.toRgb(DemoUtils.RGB_ARR_RED))
                    .pointBackgroundColor(ColorUtils.toRgb(DemoUtils.RGB_ARR_RED)))
                .addDataset(new RadarDataset().label("My Second  dataset")
                    .backgroundColor(ColorUtils.toRgba(DemoUtils.RGB_ARR_BLUE, 0.2))
                    .borderColor(ColorUtils.toRgb(DemoUtils.RGB_ARR_BLUE))
                    .pointBackgroundColor(ColorUtils.toRgb(DemoUtils.RGB_ARR_BLUE)))
                .and();

        config.
            options()
                .legend()
                    .position(Position.TOP)
                    .and()
                .title()
                    .display(true)
                    .text("Chart.js Radar chart with circular grid lines")
                    .and()
                .scale(new RadialLinearScale()
                    .ticks()
                        .beginAtZero(true)
                        .and()
                    .gridLines()
                        .circular(true)
                        .and()
                    .pointLabels()
                        .display(true)
                        .and()
                    .angleLines()
                        .display(true)
                        .and()
                )
               .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            RadarDataset lds = (RadarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
            }
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);

        chart.addClickListener((a,b) -> {
            RadarDataset dataset = (RadarDataset) config.data().getDatasets().get(a);
            DemoUtils.notification(a, b, dataset);
        });
        return chart;
    }

}
