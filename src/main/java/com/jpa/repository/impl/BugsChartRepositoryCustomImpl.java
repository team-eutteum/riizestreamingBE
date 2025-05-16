package com.jpa.repository.impl;

import static com.jpa.entity.QBugsChart.bugsChart;

import com.jpa.entity.BugsChart;
import com.jpa.entity.chartType.BugsChartType;
import com.jpa.repository.BugsChartRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BugsChartRepositoryCustomImpl implements BugsChartRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BugsChart> findCurrentChartsByType(String chartType) {
        BooleanBuilder booleanBuilder = generateQueryCondition(chartType);

        return jpaQueryFactory.select(bugsChart)
                .from(bugsChart)
                .where(booleanBuilder)
                .orderBy(bugsChart.rank.asc())
                .fetch();
    }

    private BooleanBuilder generateQueryCondition(String chartType){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(bugsChart.chartType.eq(BugsChartType.valueOf(chartType)));

        switch (chartType) {
            case "realtime" -> {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime startOfHour = now.withMinute(0).withSecond(0).withNano(0);
                LocalDateTime endOfHour = startOfHour.plusHours(1);

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
            }
            case "day" -> {
                LocalDate today = LocalDate.now();
                LocalDateTime startOfDay = today.atStartOfDay();
                LocalDateTime endOfDay = startOfDay.plusDays(1);

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfDay)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfDay)));
            }
            case "week" -> {
                LocalDate now = LocalDate.now();
                LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay();
                LocalDateTime endOfWeek = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay();

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfWeek)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfWeek)));
            }
        }

        return booleanBuilder;
    }
}
