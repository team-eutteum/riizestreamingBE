package com.jpa.repository;

import com.jpa.entity.chartType.MelonChartType;
import com.jpa.entity.MelonChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface MelonChartRepository extends JpaRepository<MelonChart, Integer> {
    List<MelonChart> findByChartType(MelonChartType chartType);

}
