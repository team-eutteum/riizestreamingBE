package com.app.chart.web;

import com.app.chart.data.FloChartDTO;
import com.app.chart.data.MelonChartDTO;
import com.app.chart.service.ChartService;
import com.jpa.entity.chartType.FloChartType;
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
@RequestMapping("/api/charts/flo")
public class FloChartController {

    private final ChartService chartService;

    @GetMapping("/{type}")
    public ResponseEntity<List<FloChartDTO>> getFloCharts(@PathVariable("type") FloChartType chartType) {
        List<FloChartDTO> chartList = chartService.getFloChartsByType(chartType);

        return ResponseEntity.ok(chartList);
    }
}