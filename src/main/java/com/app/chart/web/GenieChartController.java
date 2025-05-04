package com.app.chart.web;

import com.app.chart.data.GenieChartDTO;
import com.app.chart.data.MelonChartDTO;
import com.app.chart.service.ChartService;
import com.jpa.entity.chartType.GenieChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charts/genie")
public class GenieChartController {

    private final ChartService chartService;

    @GetMapping("/{type}")
    public ResponseEntity<List<GenieChartDTO>> getGenieCharts(@PathVariable("type") GenieChartType chartType) {
        List<GenieChartDTO> chartList = chartService.getGenieChartsByType(chartType);

        return ResponseEntity.ok(chartList);
    }
}