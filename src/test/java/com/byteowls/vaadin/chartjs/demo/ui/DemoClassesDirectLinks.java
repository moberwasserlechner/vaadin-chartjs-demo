package com.byteowls.vaadin.chartjs.demo.ui;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author michael@team-conductor.com
 */
@RunWith(Parameterized.class)
public class DemoClassesDirectLinks {

    @Parameterized.Parameters
    public static Iterable<?> data() {
        final List<Class<? extends ChartView>> data = new ArrayList<>();
        for (MenuItem menuItem : ChartJsDemoUI.MENU_ITEMS) {
            data.add(menuItem.getViewClass());
        }
        return data;
    }

    private Class<? extends ChartView> chartViewClazz;

    public DemoClassesDirectLinks(Class<? extends ChartView> chartViewClazz) {
        this.chartViewClazz = chartViewClazz;
    }

    @Test
    public void exists() throws IOException {
        String githubPath = DemoUtils.getGithubPath(this.chartViewClazz);
        URL u = new URL(githubPath);
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        huc.setRequestMethod("HEAD");
        Assert.assertTrue("Url '"+githubPath+"' does not exist. Response code: "+huc.getResponseCode(), huc.getResponseCode() == HttpURLConnection.HTTP_OK);
    }

}
