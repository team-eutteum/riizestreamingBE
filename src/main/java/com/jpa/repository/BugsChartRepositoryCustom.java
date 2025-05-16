package com.jpa.repository;

import com.jpa.entity.BugsChart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BugsChartRepositoryCustom {

    List<BugsChart> findCurrentChartsByType(@Param("chartType") String chartType);
}
