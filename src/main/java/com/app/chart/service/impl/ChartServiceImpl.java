package com.app.chart.service.impl;

import com.app.chart.data.BugsChartDTO;
import com.app.chart.data.FloChartDTO;
import com.app.chart.data.GenieChartDTO;
import com.app.chart.data.MelonChartDTO;
import com.app.chart.service.ChartService;
import com.jpa.entity.*;
import com.jpa.entity.chartType.*;
import com.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService {

    private final MelonChartRepository melonChartRepository;
    private final GenieChartRepository genieChartRepository;
    private final FloChartRepository floChartRepository;
    private final BugsChartRepository bugsChartRepository;

    @Override
    public List<MelonChartDTO> getMelonChartsByType(MelonChartType chartType) {
        //List<MelonChart> charts = melonChartRepository.findByChartType(chartType);

        String chartTypeStr = chartType.name();
        List<MelonChart> charts = melonChartRepository.findLatestChartsByType(chartTypeStr);

        return charts.stream()
                .map(MelonChartDTO::fromEntity)
                .collect(Collectors.toList());

        /*return charts.stream()
                .map(MelonChartDTO::fromEntity)
                .collect(Collectors.toList());*/
    }

    @Override
    public List<GenieChartDTO> getGenieChartsByType(GenieChartType chartType) {
        /*List<GenieChart> charts = genieChartRepository.findByChartType(chartType);
        return charts.stream()
                .map(GenieChartDTO::fromEntity)
                .collect(Collectors.toList());*/

        String chartTypeStr = chartType.name();
        List<GenieChart> charts = genieChartRepository.findLatestChartsByType(chartTypeStr);

        return charts.stream()
                .map(GenieChartDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FloChartDTO> getFloChartsByType(FloChartType chartType) {
        /*List<FloChart> charts = floChartRepository.findByChartType(chartType);

        return charts.stream()
                .map(FloChartDTO::fromEntity)
                .collect(Collectors.toList());*/

        String chartTypeStr = chartType.name();
        List<FloChart> charts = floChartRepository.findLatestChartsByType(chartTypeStr);

        return charts.stream()
                .map(FloChartDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BugsChartDTO> getBugsChartsByType(BugsChartType chartType) {
        //List<BugsChart> charts = bugsChartRepository.findByChartType(chartType);

        /*return charts.stream()
                .map(BugsChartDTO::fromEntity)
                .collect(Collectors.toList());*/

        String chartTypeStr = chartType.name();
        List<BugsChart> charts = bugsChartRepository.findLatestChartsByType(chartTypeStr);

        return charts.stream()
                .map(BugsChartDTO::fromEntity)
                .collect(Collectors.toList());


    }

}