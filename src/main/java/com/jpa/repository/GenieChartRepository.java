package com.jpa.repository;

import com.jpa.entity.GenieChart;
import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.GenieChartType;
import com.jpa.entity.chartType.MelonChartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface GenieChartRepository extends JpaRepository<GenieChart, Integer> {
    List<GenieChart> findByChartType(GenieChartType chartType);

}
