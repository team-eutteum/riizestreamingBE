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
            LocalDateTime startOfHour;
            LocalDateTime endOfHour;

            if(now.getMinute() < 1){ //0~1분 사이일 경우: 이전 시간 데이터 제공
                startOfHour = now.minusHours(1).withMinute(1).withSecond(0).withNano(0);
            } else { //1분 이후: 현재 시간 기준 1분부터 1시간 간격
                startOfHour = now.withMinute(1).withSecond(0).withNano(0);
            }

            endOfHour = startOfHour.plusHours(1);

            booleanBuilder.and(floChart.crawledAt.goe(Timestamp.valueOf(startOfHour)));
            booleanBuilder.and(floChart.crawledAt.lt(Timestamp.valueOf(endOfHour)));
        }

        return booleanBuilder;
    }
}
