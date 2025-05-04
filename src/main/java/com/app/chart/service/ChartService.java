package com.app.chart.service;

import com.app.chart.data.BugsChartDTO;
import com.app.chart.data.FloChartDTO;
import com.app.chart.data.GenieChartDTO;
import com.app.chart.data.MelonChartDTO;
import com.jpa.entity.chartType.*;

import java.util.List;

public interface ChartService {
    List<MelonChartDTO> getMelonChartsByType(MelonChartType chartType);
    List<GenieChartDTO> getGenieChartsByType(GenieChartType chartType);
    List<FloChartDTO> getFloChartsByType(FloChartType chartType);
    List<BugsChartDTO> getBugsChartsByType(BugsChartType chartType);

    // List<FloChartDTO> getChartsByPlatformAndType(String platform, String chartType);
}