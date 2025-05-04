package com.jpa.repository;

import com.jpa.entity.FloChart;
import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.FloChartType;
import com.jpa.entity.chartType.MelonChartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface FloChartRepository extends JpaRepository<FloChart, Integer> {
    List<FloChart> findByChartType(FloChartType chartType);

}
