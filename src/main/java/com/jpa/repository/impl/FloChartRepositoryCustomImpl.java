package com.jpa.repository.impl;

import static com.jpa.entity.QFloChart.floChart;

import com.jpa.entity.FloChart;
import com.jpa.entity.chartType.FloChartType;
import com.jpa.repository.FloChartRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FloChartRepositoryCustomImpl implements FloChartRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FloChart> findCurrentChartsByType(String chartType) {
        BooleanBuilder booleanBuilder = generateQueryCondition(chartType);

        return jpaQueryFactory.select(floChart)
                .from(floChart)
                .where(booleanBuilder)
                .orderBy(floChart.rank.asc())
                .fetch();
    }

    private BooleanBuilder generateQueryCondition(String chartType){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(floChart.chartType.eq(FloChartType.valueOf(chartType)));

        if (chartType.equals("realtime")) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfHour = now.withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endOfHour = startOfHour.plusHours(1);

            booleanBuilder.and(floChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
            booleanBuilder.and(floChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
        }

        return booleanBuilder;
    }
}
