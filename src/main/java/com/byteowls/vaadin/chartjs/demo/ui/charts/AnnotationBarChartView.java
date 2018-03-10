package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class AnnotationBarChartView extends AbstractChartView {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getChart() {
        BarChartConfig config = new BarChartConfig();
        config
            .data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().type().label("Dataset 1").backgroundColor(DemoUtils.RGBA_RED).borderColor(DemoUtils.RGB_RED).borderWidth(1))
                .addDataset(new BarDataset().type().label("Dataset 2").backgroundColor(DemoUtils.RGBA_BLUE).borderColor(DemoUtils.RGB_BLUE).borderWidth(1))
                .and();

        config.
            options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Annotation Plugin used in Bar Chart")
                    .and()
                .legend()
                    .position(Position.TOP)
                    .and()
                .annotation()
                    .line()
                        .id("hline")
                        .horizontal()
                        .scaleID("y-axis-0") // same as
                        .yAxis0ScaleID()
                        .value(DemoUtils.randomScalingFactor())
                        .borderColor("black")
                        .borderWidth(5)
                        .label()
                            .backgroundColor("red")
                            .content("Test Label")
                            .and()
                        .and()
                    .line()
                        .id("vline")
                        .vertical()
                        .xAxis0ScaleID()
                        .drawTime(DrawTime.beforeDatasetsDraw)
                        .value("May")
                        .borderColor(DemoUtils.RGB_GREEN)
                        .borderWidth(5)
                        .label()
                            .backgroundColor("black")
                            .fontColor("white")
                            .content("Vertical Line")
                            .and()
                        .and()
                    .box()
                        .id("box")
                        .drawTime(DrawTime.afterDatasetsDraw)
                        .xyAxisScaleID()
                        .xMin("February")
                        .xMax("April")
                        .yMin(DemoUtils.randomScalingFactor())
                        .yMax(DemoUtils.randomScalingFactor())
                        .backgroundColor("rgba(101, 33, 171, 0.5)")
                        .borderWidth(1)
                        .borderColor("rgb(101, 33, 171)")
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



