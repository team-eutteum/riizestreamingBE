package com.jpa.repository;

import com.jpa.entity.chartType.MelonChartType;
import com.jpa.entity.MelonChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface MelonChartRepository extends JpaRepository<MelonChart, Integer>, MelonChartRepositoryCustom {

  /*  @Query(value = "WITH latest_hour AS (" +
            "SELECT DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') AS hour_group " +
            "FROM melon_chart " +
            "WHERE chart_type = :chartType " +
            "GROUP BY DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') " +
            "ORDER BY hour_group DESC " +
            "LIMIT 1) " +
            "SELECT mc.* FROM melon_chart mc " +
            "JOIN latest_hour lh ON DATE_FORMAT(mc.crawled_at, '%Y-%m-%d %H:00:00') = lh.hour_group " +
            "WHERE mc.chart_type = :chartType " +
            "ORDER BY mc.rank ASC", nativeQuery = true)
    List<MelonChart> findLatestChartsByType(@Param("chartType") String chartType);*/
}
