package com.jpa.repository;

import com.jpa.entity.BugsChart;
import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.BugsChartType;
import com.jpa.entity.chartType.MelonChartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface BugsChartRepository extends JpaRepository<BugsChart, Integer> {
    List<BugsChart> findByChartType(BugsChartType chartType);

}
