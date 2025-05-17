package com.jpa.repository;

import com.jpa.entity.MelonChart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MelonChartRepositoryCustom {

    List<MelonChart> findCurrentChartsByType(@Param("chartType") String chartType);
}
