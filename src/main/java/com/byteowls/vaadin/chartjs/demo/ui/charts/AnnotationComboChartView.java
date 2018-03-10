package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class AnnotationComboChartView extends AbstractChartView {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getChart() {
        BarChartConfig config = new BarChartConfig();
        config
            .data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new LineDataset().type().label("Dataset 1").borderColor(DemoUtils.RGB_BLUE).fill(false).borderWidth(2))
                .addDataset(new BarDataset().type().label("Dataset 2").backgroundColor(DemoUtils.RGB_RED).borderColor("white").borderWidth(2))
                .addDataset(new BarDataset().type().label("Dataset 3").backgroundColor(DemoUtils.RGB_GREEN).borderWidth(2))
                .and();

        config.
            options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Annotation Plugin used in Combo Bar Line Chart")
                    .and()
                .tooltips()
                    .mode(InteractionMode.INDEX)
                    .intersect(true)
                    .and()
                .annotation()
                    .line()
                        .id("hline")
                        .drawTime(DrawTime.afterDatasetsDraw)
                        .scaleID("y-axis-0") // required
                        .horizontal()
                        .value(DemoUtils.randomScalingFactor())
                        .borderColor("black")
                        .borderWidth(5)
                        .label()
                            .backgroundColor(DemoUtils.RGB_RED)
                            .content("Line annotation test label")
                            .and()
                        .and()
                    .box()
                        .drawTime(DrawTime.beforeDatasetsDraw)
                        .xScaleID("x-axis-0")
                        .yScaleID("y-axis-0")
                        .xMin("February")
                        .xMax("April")
                        .yMin(DemoUtils.randomScalingFactor())
                        .yMin(DemoUtils.randomScalingFactor())
                        .backgroundColor("rgba(101, 33, 171, 0.5)")
                        .borderColor("rgb(101, 33, 171)")
                        .borderWidth(1)
                        .and()
                    .and()
               .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                // equals DemoUtils.randomScalingFactor()
                data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }

            if (ds instanceof BarDataset) {
                BarDataset bds = (BarDataset) ds;
                bds.dataAsList(data);
            }

            if (ds instanceof LineDataset) {
                LineDataset lds = (LineDataset) ds;
                lds.dataAsList(data);
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);

        return chart;
    }

}
