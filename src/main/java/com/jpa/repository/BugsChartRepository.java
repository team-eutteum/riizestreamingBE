package com.jpa.repository;

import com.jpa.entity.BugsChart;
import com.jpa.entity.chartType.BugsChartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories("com.jpa.repository")
@Repository
public interface BugsChartRepository extends JpaRepository<BugsChart, Integer>, BugsChartRepositoryCustom {

/*    @Query(value = "WITH latest_hour AS (" +
            "SELECT DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') AS hour_group " +
            "FROM bugs_chart " +
            "WHERE chart_type = :chartType " +
            "GROUP BY DATE_FORMAT(crawled_at, '%Y-%m-%d %H:00:00') " +
            "ORDER BY hour_group DESC " +
            "LIMIT 1) " +
            "SELECT bc.* FROM bugs_chart bc " +
            "JOIN latest_hour lh ON DATE_FORMAT(bc.crawled_at, '%Y-%m-%d %H:00:00') = lh.hour_group " +
            "WHERE bc.chart_type = :chartType " +
            "ORDER BY bc.rank ASC", nativeQuery = true)
    List<BugsChart> findLatestChartsByType(@Param("chartType") String chartType);*/

}
