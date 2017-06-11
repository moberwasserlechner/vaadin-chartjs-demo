package com.byteowls.vaadin.chartjs.demo.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

public enum ChartType {
    
    BAR(VaadinIcons.BAR_CHART),
    LINE(VaadinIcons.CHART_LINE),
    PIE(VaadinIcons.PIE_CHART),
    AREA(VaadinIcons.CHART_GRID);
    
    
    FontIcon icon;
    
    ChartType(FontIcon icon) {
        this.icon = icon;
    }

    public FontIcon getIcon() {
        return icon;
    }
    
}
