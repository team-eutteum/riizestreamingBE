package com.app.chart.web;

import com.app.chart.data.BugsChartDTO;
import com.app.chart.service.ChartService;
import com.jpa.entity.chartType.BugsChartType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charts/bugs")
public class BugsChartController {

    private final ChartService chartService;

    @GetMapping("/{type}")
    public ResponseEntity<List<BugsChartDTO>> getBugsCharts(@PathVariable("type") BugsChartType chartType) {
        List<BugsChartDTO> chartList = chartService.getBugsChartsByType(chartType);

        return ResponseEntity.ok(chartList);
    }
}