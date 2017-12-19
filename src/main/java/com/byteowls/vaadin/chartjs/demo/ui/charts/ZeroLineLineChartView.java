package com.byteowls.vaadin.chartjs.demo.ui.charts;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.demo.ui.AbstractChartView;
import com.byteowls.vaadin.chartjs.demo.ui.DemoUtils;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringView
public class ZeroLineLineChartView extends AbstractChartView {

    private static final long serialVersionUID = -1977315515493155463L;

    @Override
    public Component getChart() {
        LineChartConfig lineConfig = new LineChartConfig();
        lineConfig.data()
            .labels("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            .addDataset(new LineDataset()
                .label("Primes")
                .borderColor("rgba(220,220,220,1)")
                .backgroundColor("rgba(220,220,220,0.2)")
                .pointBackgroundColor("rgba(220,220,220,1)")
                .fill(true)
                .data(2d, 3d, 5d, 7d, 11d, 13d, 17d, 19d, 23d, 29d)
            )
            .and()
        .options()
            .responsive(true)
            .title()
                .display(true)
                .text("Chart.js Line Chart - Zero Line Options")
                .and()
            .scales()
            .add(Axis.X, new CategoryScale()
                .display(true)
                .gridLines()
                    .drawBorder(false)
                    .zeroLineColor(DemoUtils.RGB_GREEN)
                    .zeroLineWidth(2)
                    .zeroLineBorderDash(10, 10)
                    .zeroLineBorderDashOffset(1)
                    .and()
            )
            .add(Axis.Y, new LinearScale()
                .display(true)
                .gridLines()
                    .drawBorder(false)
                    .zeroLineColor(DemoUtils.RGB_RED)
                    .zeroLineWidth(5)
                    .zeroLineBorderDash(10, 5, 15, 6)
                    .zeroLineBorderDashOffset(5)
                    .and()
            )
            .and()
         .done();

        ChartJs chart = new ChartJs(lineConfig);
        chart.addClickListener((a,b) -> DemoUtils.notification(a, b, lineConfig.data().getDatasets().get(a)));
        chart.setJsLoggingEnabled(true);
        return chart;
    }

}
