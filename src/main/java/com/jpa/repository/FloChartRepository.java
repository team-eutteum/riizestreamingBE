package com.jpa.repository;

import com.jpa.entity.FloChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface FloChartRepository extends JpaRepository<FloChart, Integer>, FloChartRepositoryCustom {

/*    @Query(value = "WITH latest_hour AS (" +
            "SELECT DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') AS hour_group " +
            "FROM flo_chart " +
            "WHERE chart_type = :chartType " +
            "GROUP BY DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') " +
            "ORDER BY hour_group DESC " +
            "LIMIT 1) " +
            "SELECT fc.* FROM flo_chart fc " +
            "JOIN latest_hour lh ON DATE_FORMAT(fc.crawled_at, '%Y-%m-%d %H:00:00') = lh.hour_group " +
            "WHERE fc.chart_type = :chartType " +
            "ORDER BY fc.rank ASC", nativeQuery = true)
    List<FloChart> findLatestChartsByType(@Param("chartType") String chartType);*/
}
