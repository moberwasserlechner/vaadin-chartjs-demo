package com.byteowls.vaadin.chartjs.demo.ui;

import com.byteowls.vaadin.chartjs.data.Dataset;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * This util class provides a few shortcuts.
 */
public abstract class DemoUtils {

    public static final int[] RGB_ARR_RED = {255, 99, 132};
    public static final int[] RGB_ARR_ORANGE = {255, 159, 64};
    public static final int[] RGB_ARR_YELLOW = {255, 205, 86};
    public static final int[] RGB_ARR_GREEN = {75, 192, 192};
    public static final int[] RGB_ARR_BLUE = {54, 162, 235};
    public static final int[] RGB_ARR_PURPLE = {153, 102, 255};
    public static final int[] RGB_ARR_GREY = {201, 203, 20};
    public static final String RGB_RED = "rgb(255, 99, 132)";
    public static final String RGBA_RED = "rgba(255, 99, 132, 0.2)";
    public static final String RGB_ORANGE = "rgb(255, 159, 64)";
    public static final String RGB_YELLOW = "rgb(255, 205, 86)";
    public static final String RGB_GREEN = "rgb(75, 192, 192)";
    public static final String RGB_BLUE = "rgb(54, 162, 235)";
    public static final String RGBA_BLUE = "rgba(54, 162, 235, 0.2)";
    public static final String RGB_PURPLE = "rgb(153, 102, 255)";
    public static final String RGB_GREY = "rgb(231,233,237)";
    public static final String RGB_BLACK = "rgb(0,0,0)";
    public static final String GITHUB_REPO_URL = "https://github.com/moberwasserlechner/vaadin-chartjs-demo/tree/master/src/main/java/";

    public static List<int[]> COLORS;
    static {
        COLORS = new ArrayList<>();
        COLORS.add(RGB_ARR_RED);
        COLORS.add(RGB_ARR_ORANGE);
        COLORS.add(RGB_ARR_YELLOW);
        COLORS.add(RGB_ARR_GREEN);
        COLORS.add(RGB_ARR_BLUE);
        COLORS.add(RGB_ARR_PURPLE);
        COLORS.add(RGB_ARR_GREY);
    }

    public static double randomScalingFactor() {
        return (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100);
    }

    public static void notification(int dataSetIdx, int dataIdx, Dataset<?, ?> dataset) {
        Notification.show("Dataset at Idx:" + dataSetIdx + "; Data at Idx: " + dataIdx + "; Value: " + dataset.getData().get(dataIdx), Type.TRAY_NOTIFICATION);
    }

    public static String getPathToClass(Class<?> clazz) {
        return clazz.getCanonicalName().replaceAll("\\.", "/") + ".java";
    }

    public static String getGithubPath(Class<?> clazz) {
        return GITHUB_REPO_URL + getPathToClass(clazz);
    }
}
