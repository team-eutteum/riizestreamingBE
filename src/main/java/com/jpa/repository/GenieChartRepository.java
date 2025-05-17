package com.jpa.repository;

import com.jpa.entity.GenieChart;
import com.jpa.entity.chartType.GenieChartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface GenieChartRepository extends JpaRepository<GenieChart, Integer>, GenieChartRepositoryCustom {

/*    @Query(value = "WITH latest_hour AS (" +
            "SELECT DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') AS hour_group " +
            "FROM genie_chart " +
            "WHERE chart_type = :chartType " +
            "GROUP BY DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') " +
            "ORDER BY hour_group DESC " +
            "LIMIT 1) " +
            "SELECT gc.* FROM genie_chart gc " +
            "JOIN latest_hour lh ON DATE_FORMAT(gc.crawled_at, '%Y-%m-%d %H:00:00') = lh.hour_group " +
            "WHERE gc.chart_type = :chartType " +
            "ORDER BY gc.rank ASC", nativeQuery = true)
    List<GenieChart> findLatestChartsByType(@Param("chartType") String chartType);*/

}
