package com.jpa.repository.impl;

import static com.jpa.entity.QMelonChart.melonChart;

import com.jpa.entity.MelonChart;
import com.jpa.entity.chartType.MelonChartType;
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
public class MelonChartRepositoryCustom implements com.jpa.repository.MelonChartRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MelonChart> findCurrentChartsByType(String chartType) {
        BooleanBuilder booleanBuilder = generateQueryCondition(chartType);

        return jpaQueryFactory.select(melonChart)
                .from(melonChart)
                .where(booleanBuilder)
                .orderBy(melonChart.rank.asc())
                .fetch();
    }

    private BooleanBuilder generateQueryCondition(String chartType){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(melonChart.chartType.eq(MelonChartType.valueOf(chartType)));

        switch (chartType) {
            case "hot30", "hot100", "top100" -> { //실시간 차트
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime startOfHour = now.withMinute(0).withSecond(0).withNano(0);
                LocalDateTime endOfHour = startOfHour.plusHours(1);

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
            }
            case "genre100" -> { //일간 차트
                LocalDate today = LocalDate.now();
                LocalDateTime startOfDay = today.atStartOfDay();
                LocalDateTime endOfDay = startOfDay.plusDays(1);

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfDay)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfDay)));
            }
            case "week100" -> { //주간 차트
                LocalDate now = LocalDate.now();
                LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay();
                LocalDateTime endOfWeek = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay();

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfWeek)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfWeek)));
            }
            case "month100" -> { //월간차트
                LocalDate now = LocalDate.now();
                LocalDate startOfMonth = now.withDayOfMonth(1);
                LocalDateTime endOfMonth = now.withDayOfMonth(startOfMonth.lengthOfMonth()).plusDays(1).atStartOfDay();

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfMonth.atStartOfDay())));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfMonth)));
            }
        }

        return booleanBuilder;
    }
}
