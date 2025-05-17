package com.jpa.repository;

import com.jpa.entity.GenieChart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenieChartRepositoryCustom {

    List<GenieChart> findCurrentChartsByType(@Param("chartType") String chartType);
}
