package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.RadarChartConfig;
import com.byteowls.vaadin.chartjs.data.RadarDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.demo.ui.utils.SampleDataConfig;
import com.byteowls.vaadin.chartjs.options.FillMode;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

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
                .addDataset(new RadarDataset().fill(true, 2))
                .addDataset(new RadarDataset().fill(false))
                .addDataset(new RadarDataset().fill(false))
                .addDataset(new RadarDataset().fill(FillMode.START))
                .addDataset(new RadarDataset().hidden(true).fill(1))
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

        SampleDataConfig sampleConfig = new SampleDataConfig().min(8).max(16).count(8).decimals(2).continuity(1);
        for (int i = 0; i < config.data().getDatasets().size(); i++) {
            RadarDataset lds = (RadarDataset) config.data().getDatasetAtIndex(i);
            lds.label("D"+i);
            int[] rgb = DemoUtils.getRgbColor(i);
            lds.borderColor(ColorUtils.toRgb(rgb));
            lds.backgroundColor(ColorUtils.toRgba(rgb, 0.5));
            // generate data
            List<Double> data = DemoUtils.generateSampleData(sampleConfig);
            sampleConfig.from(data);
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) ->
            DemoUtils.notification(a, b, config.data().getDatasets().get(a)));
        return chart;
    }

}
