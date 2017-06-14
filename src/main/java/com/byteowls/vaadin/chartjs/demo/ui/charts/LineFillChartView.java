package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.data.RadarDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.demo.ui.utils.SampleDataConfig;
import com.byteowls.vaadin.chartjs.options.FillMode;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

import java.util.List;

@UIScope
@SpringView
public class LineFillChartView extends AbstractChartView {

    private static final long serialVersionUID = -8575881820534087527L;

    @Override
    public Component getChart() {
        LineChartConfig config = new LineChartConfig();
        config
            .data()
                .labels("January", "February", "March", "April", "May", "June", "July", "August")
                .addDataset(new LineDataset().hidden(true))
                .addDataset(new LineDataset().fill(false, 1))
                .addDataset(new LineDataset().hidden(true).fill(1))
                .addDataset(new LineDataset().fill(false, 1))
                .addDataset(new LineDataset().fill(false, 1))
                .addDataset(new LineDataset().fill(true, 2))
                .addDataset(new LineDataset().fill(false))
                .addDataset(new LineDataset().fill(8))
                .addDataset(new LineDataset().hidden(true).fill(FillMode.END))
                .and();

        config.
            options()
                .maintainAspectRatio(true)
                .elements()
                    .line()
                        .tension(0.00001d)
                        .and()
                    .and()
                .scales()
                    .add(Axis.Y, new LinearScale().stacked(true))
                    .and()
                .title()
                    .display(true)
                    .text("Advanced line fill options")
                    .and()
               .done();

        SampleDataConfig sampleConfig = new SampleDataConfig().min(20).max(80).count(8).decimals(2).continuity(1);
        for (int i = 0; i < config.data().getDatasets().size(); i++) {
            LineDataset lds = (LineDataset) config.data().getDatasetAtIndex(i);
            lds.label("D"+i);
            int[] rgb = DemoUtils.getRgbColor(i);
            lds.borderColor(ColorUtils.toRgb(rgb));
            lds.backgroundColor(ColorUtils.toRgba(rgb, 0.5));
            // generate data
            List<Double> data = DemoUtils.generateSampleData(sampleConfig);
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
