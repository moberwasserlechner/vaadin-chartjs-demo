package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle.RectangleEdge;
import com.byteowls.vaadin.chartjs.options.zoom.XYMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class ZoomPanBarChartView extends AbstractChartView {

    private static final long serialVersionUID = -3541750814970379914L;

    @Override
    public Component getChart() {
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
            data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().backgroundColor(DemoUtils.RGB_RED).label("Dataset 1"))
                .addDataset(new BarDataset().backgroundColor(DemoUtils.RGB_BLUE).label("Dataset 2").hidden(true))
                .addDataset(new BarDataset().backgroundColor(DemoUtils.RGB_GREEN).label("Dataset 3"))
                .and()
            .options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Zoom/Pan Bar Chart")
                    .and()
                 .elements()
                     .rectangle()
                         .borderWidth(2)
                         .borderColor(DemoUtils.RGB_BLACK)
                         .borderSkipped(RectangleEdge.LEFT)
                         .and()
                     .and()
                 .legend()
                     .fullWidth(false)
                     .position(Position.LEFT)
                     .and()
                .pan()
                    .mode(XYMode.XY)
                    .speed(20)
                    .threshold(10)
                    .rangeMax()
                        .x(150)
                        .and()
                    .rangeMin()
                        .x(-150)
                        .and()
                    .and()
                .zoom()
                    .enabled(true) // if you enter the zoom options its enabled
                    .mode(XYMode.XY)
                    .sensitivity(3)
//                    .drag(true) // I've seen some strange results when using this on desktop browsers
                    .and()
               .done();

        List<String> labels = barConfig.data().getLabels();
        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> DemoUtils.notification(a, b, barConfig.data().getDatasets().get(a)));
        return chart;
    }

}
