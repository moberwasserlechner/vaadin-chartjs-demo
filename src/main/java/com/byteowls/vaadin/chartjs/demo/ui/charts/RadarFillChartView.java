package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.RadarChartConfig;
import com.byteowls.vaadin.chartjs.data.RadarDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.elements.Line;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView
public class RadarFillChartView extends AbstractChartView {

    private static final long serialVersionUID = -8575881820534087527L;

    @Override
    public Component getChart() {
        RadarChartConfig config = new RadarChartConfig();
        config
            .data()
                .labels("January", "February", "March", "April", "May", "June", "July", "August")
                .addDataset(new RadarDataset().fill(false))
                .addDataset(new RadarDataset().fillTo(true, 2))
                .addDataset(new RadarDataset().fill(false))
                .addDataset(new RadarDataset().fill(false))
                .addDataset(new RadarDataset().fill(Line.FillMode.START))
                .addDataset(new RadarDataset().hidden(true).fillTo(1))
                .and();

        config.
            options()
                .maintainAspectRatio(true)
                .elements()
                    .line()
                        .tension(0.000001d)
                        .and()
                    .and()
                .title()
                    .display(true)
                    .text("Chart.js Radar Chart Fill options")
                    .and()
               .done();

        List<String> labels = config.data().getLabels();
        for (int i = 0; i < config.data().getDatasets().size(); i++) {
            RadarDataset lds = (RadarDataset) config.data().getDatasetAtIndex(i);
            lds.label("D"+i);
            int[] rgb = DemoUtils.COLORS.get(i);
            lds.borderColor(rgb);
            lds.backgroundColor(rgb, 0.5);
            List<Double> data = new ArrayList<>();
            for (int i1 = 0; i1 < labels.size(); i1++) {
                data.add((i + 1) * 5d);
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
