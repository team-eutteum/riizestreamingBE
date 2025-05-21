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
public class MelonChartRepositoryCustomImpl implements com.jpa.repository.MelonChartRepositoryCustom {

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
                LocalDateTime startOfHour;
                LocalDateTime endOfHour;

                //hot100(30,100): 02~06시 차트 업데이트 안됨 -> 1시 데이터 제공
                if((chartType.equals("hot30") || chartType.equals("hot100")) && now.getHour() >= 2 && now.getHour() < 7){
                    startOfHour = now.withHour(1).withMinute(1).withSecond(0).withNano(0);
                    endOfHour = startOfHour.plusHours(1);

                } else { //1분 이후: 현재 시간 기준 1분부터 1시간 간격(0~1분 공백)
                    startOfHour = now.withMinute(1).withSecond(0).withNano(0);
                    endOfHour = startOfHour.plusHours(1);
                }

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
            }
            case "genre100" -> { //일간 차트
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime startOfDay;

                if(today.getHour() < 12){
                    startOfDay = today.minusDays(1).withHour(12).withMinute(0).withSecond(0).withNano(0);
                } else {
                    startOfDay = today.withHour(12).withMinute(0).withSecond(0).withNano(0);
                }

                LocalDateTime endOfDay = startOfDay.plusDays(1);

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfDay)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfDay)));
            }
            case "week100" -> { //주간 차트
                LocalDate now = LocalDate.now();
                LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay().withMinute(1);
                LocalDateTime endOfWeek = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay().withMinute(1);

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfWeek)));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfWeek)));
            }
            case "month100" -> { //월간차트
                LocalDate now = LocalDate.now();
                LocalDate startOfMonth = now.withDayOfMonth(1);
                LocalDateTime endOfMonth = now.withDayOfMonth(startOfMonth.lengthOfMonth()).plusDays(1).atStartOfDay().withMinute(1);

                booleanBuilder.and(melonChart.crawledAt.goe(Timestamp.valueOf(startOfMonth.atStartOfDay().withMinute(1))));
                booleanBuilder.and(melonChart.crawledAt.lt(Timestamp.valueOf(endOfMonth)));
            }
        }

        return booleanBuilder;
    }
}
