package com.jpa.repository;

import com.jpa.entity.FloChart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FloChartRepositoryCustom {

    List<FloChart> findCurrentChartsByType(@Param("chartType") String chartType);
}
