package com.byteowls.vaadin.chartjs.demo.ui;

import com.byteowls.vaadin.chartjs.demo.ui.charts.*;
import com.vaadin.annotations.Theme;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.util.IllegalConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Theme("chartjs")
@SpringUI
public class ChartJsDemoUI extends UI {

    private static final long serialVersionUID = -33887281222947647L;

    protected static List<MenuItem> MENU_ITEMS;
    static {
        MENU_ITEMS = new ArrayList<>();
        MENU_ITEMS.add(new MenuItem(ChartType.BAR, "Vertical", MultiAxisBarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.BAR, "Horizontal", HorizontalBarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.BAR, "Combo", BarLineComboChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.BAR, "Stacked", StackedBarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.BAR, "Grouped Stacks", GroupedStackedBarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Simple", SimpleLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Stacked", StackedLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Combo", BarLineComboChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "PointSize", PointSizeLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Point Styles", LinePointSytesChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Scatter", ScatterLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "SkipPoints", SkipPointsLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Stepped", SteppedLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "CubicInterpolation", CubicInterpolationLineChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Tooltip Interactions", TooltipInteractionModesChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Tooltip Positions", TooltipPositionModesChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.LINE, "Elements.Line FillMode", ElementLineFillModeChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Pie", SinglePieChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Donut", MultiDonutChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Angled pie", AngledPieChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Gauge donut", GaugeDonutChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Pie with data refresh", PieChartRefreshDataView.class));
        //        MENU_ITEMS.add(new MenuItem(ChartType.PIE, "Download pie", PieChartDownloadView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.AREA, "Bubble", SimpleBubbleChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.AREA, "Polar", PolarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.AREA, "Radar", SimpleRadarChartView.class));
        MENU_ITEMS.add(new MenuItem(ChartType.AREA, "Radar skipped point", SkipDataRadarChartView.class));
    }

    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private Environment env;

    private Label codeLabel;

    private Link codeLink;

    @SuppressWarnings("serial")
    @Override
    protected void init(VaadinRequest request) {
        String title = env.getProperty("addon.title");
        getPage().setTitle(title);
        Responsive.makeResponsive(this);

        Panel content = buildContent();

        Navigator navigator = new Navigator(this, content);
        navigator.addProvider(viewProvider);
        navigator.setErrorProvider(viewProvider);

        VerticalLayout vl = new VerticalLayout();
        vl.setMargin(false);
        vl.setSizeFull();

        Label info = new Label("<strong>" + title + "</strong> "
                + "| Version: <strong>" + env.getProperty("addon.version") + "</strong> "
                + "| "+env.getProperty("addon.jslib.title")+": <strong>" + env.getProperty("addon.jslib.version") + "</strong> "
                + "| Vaadin: <strong>" + env.getProperty("addon.vaadin.version") + "</strong> "
                + "| <a href=\""+env.getProperty("addon.github")+"\">Check it out on Github</a>");
        info.setContentMode(ContentMode.HTML);

        CssLayout infoBar = new CssLayout(info);
        infoBar.setWidth(100, Unit.PERCENTAGE);
        infoBar.addStyleName("addon-info-bar");
        vl.addComponent(infoBar);

        HorizontalSplitPanel splitContentCode = new HorizontalSplitPanel();
        splitContentCode.setSizeFull();
        splitContentCode.setFirstComponent(content);
        splitContentCode.setSecondComponent(buildCode());
        splitContentCode.setSplitPosition(50);

        HorizontalSplitPanel splitMenuContent = new HorizontalSplitPanel();
        splitMenuContent.setSizeFull();
        splitMenuContent.setFirstComponent(buildMenuTree());
        splitMenuContent.setSecondComponent(splitContentCode);
        splitMenuContent.setSplitPosition(15);
        vl.addComponent(splitMenuContent);
        vl.setExpandRatio(splitMenuContent, 1);

        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                ChartView view = (ChartView) event.getNewView();
                codeLink.setResource(new ExternalResource(DemoUtils.getGithubPath(view.getClass())));
                codeLink.setTargetName("_blank");

                String formattedSourceCode = getFormattedSourceCode(view.getSource());
                codeLabel.setValue(formattedSourceCode);
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
        setContent(vl);

        String fragment = Page.getCurrent().getUriFragment();
        if (fragment == null || fragment.equals("")) {
            String viewName = MENU_ITEMS.get(0).getViewName();
            navigator.navigateTo(viewName);
        }
    }

    private Panel buildContent() {
        Panel chartPanel = new Panel();
        chartPanel.setSizeFull();
        chartPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        return chartPanel;
    }

    private Component buildCode() {
        codeLink = new Link();
        codeLink.setCaption("see the full class on Github");

        codeLabel = new Label();
        codeLabel.setContentMode(ContentMode.HTML);

        Panel codePanel = new Panel(codeLabel);
        codePanel.setSizeFull();
        codePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        codePanel.addStyleName("addon-code");

        VerticalLayout codeVl = new VerticalLayout(codePanel, codeLink);
        codeVl.setMargin(false);
        codeVl.setSizeFull();
        codeVl.setExpandRatio(codePanel, 1);
        codeVl.setComponentAlignment(codeLink, Alignment.MIDDLE_CENTER);
        return codeVl;
    }

    @SuppressWarnings("unchecked")
    private Component buildMenuTree() {
        Panel treePanel = new Panel();
        treePanel.setSizeFull();
        treePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        treePanel.addStyleName("addon-menu");

        Tree<MenuItem> tree = new Tree();
        TreeData<MenuItem> treeData = new TreeData<>();

        TreeDataProvider<MenuItem> provider = new TreeDataProvider<>(treeData);
        tree.setDataProvider(provider);


        for (ChartType chartType : ChartType.values()) {
            List<MenuItem> children = new ArrayList<>();
            for (MenuItem i : MENU_ITEMS) {
                if (i.getType() == chartType) {
                    children.add(i);
                }
            }

            MenuItem parentItem = new MenuItem(chartType, chartType.name(), null);
            treeData.addItem(null, parentItem);

            for (MenuItem i : children) {
                treeData.addItem(parentItem, i);
                tree.expand(parentItem);
            }
        }

        tree.addItemClickListener(e -> {
            MenuItem i = e.getItem();
                if (i.getViewName() != null) {
                    getUI().getNavigator().navigateTo(i.getViewName());
                }
        });
        tree.setItemCaptionGenerator(MenuItem::getLabel);
        tree.setItemIconGenerator(item -> {
            if (item.getViewName() == null) {
                return item.getType().getIcon();
            }
            return null;
        });
        treePanel.setContent(tree);
        return treePanel;
    }

    public String getFormattedSourceCode(String sourceCode) {
        if (sourceCode != null) {
            try {
                JavaSource source = new JavaSourceParser().parse(new StringReader(sourceCode));
                JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
                StringWriter writer = new StringWriter();
                JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
                options.setShowLineNumbers(false);
                options.setAddLineAnchors(false);
                converter.convert(source, options, writer);
                return writer.toString();
            } catch (IllegalConfigurationException | IOException ignore) {

            }
        }
        return sourceCode;
    }

}
