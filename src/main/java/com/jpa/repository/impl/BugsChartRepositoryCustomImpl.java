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
                LocalDateTime startOfHour;
                LocalDateTime endOfHour;

                if(now.getHour() >= 2 && now.getHour() < 7){ //02시~ 06시 차트 운영 X -> 1시 차트 데이터 제공
                    startOfHour = now.withHour(1).withMinute(1).withSecond(0).withNano(0);

                } else { //1분 이후: 현재 시간 기준 1분부터 1시간 간격 (0~1분 사이 공백)
                    startOfHour = now.withMinute(1).withSecond(0).withNano(0);
                }

                endOfHour = startOfHour.plusHours(1);

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
            }
            case "day" -> {
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime startOfDay;

                if(today.getHour() < 12){
                    startOfDay = today.minusDays(1).withHour(12).withMinute(0).withSecond(0).withNano(0);
                } else {
                    startOfDay = today.withHour(12).withMinute(0).withSecond(0).withNano(0);
                }

                LocalDateTime endOfDay = startOfDay.plusDays(1);

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfDay)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfDay)));
            }
            case "week" -> {
                LocalDate now = LocalDate.now();
                LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay().withMinute(1);
                LocalDateTime endOfWeek = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay().withMinute(1);

                booleanBuilder.and(bugsChart.crawledAt.goe(Timestamp.valueOf(startOfWeek)));
                booleanBuilder.and(bugsChart.crawledAt.lt(Timestamp.valueOf(endOfWeek)));
            }
        }

        return booleanBuilder;
    }
}
