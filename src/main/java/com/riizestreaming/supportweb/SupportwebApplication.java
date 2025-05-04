package com.riizestreaming.supportweb;

import com.app.chart.service.ChartService;
import com.jpa.entity.chartType.BugsChartType;
import com.jpa.entity.chartType.FloChartType;
import com.jpa.entity.chartType.GenieChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.app", "com.jpa", "com.riizestreaming"})
@EnableJpaRepositories(basePackages = "com.jpa.repository")
@EntityScan(basePackages = "com.jpa.entity")
@RequiredArgsConstructor
@Slf4j
public class SupportwebApplication implements CommandLineRunner {

	private final ChartService chartService;

	public static void main(String[] args) {
		SpringApplication.run(SupportwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("**************************테스트데이터출력******************************");

		//멜론
		for (MelonChartType type : MelonChartType.values()) {
			var charts = chartService.getMelonChartsByType(type);
			log.info("멜론차트타입>>>>>>>>>>>: {}", type);
			log.info("몇개조회?: {}", charts.size());
			charts.forEach(chart -> log.info("{}", chart));
			log.info("****************");
		}

		//지니
		for (GenieChartType type : GenieChartType.values()) {
			var charts = chartService.getGenieChartsByType(type);
			log.info("지니차트타입>>>>>>>>>>>>>>: {}", type);
			log.info("몇개조회?: {}", charts.size());
			charts.forEach(chart -> log.info("{}", chart));
			log.info("*************");
		}

		//플로
		for (FloChartType type : FloChartType.values()) {
			var charts = chartService.getFloChartsByType(type);
			log.info("플로차트타입>>>>>>>>>>>>>>: {}", type);
			log.info("몇개조회: {}", charts.size());
			charts.forEach(chart -> log.info("{}", chart));
			log.info("********************");
		}

		//벅스
		for (BugsChartType type : BugsChartType.values()) {
			var charts = chartService.getBugsChartsByType(type);
			log.info("벅스차트타입>>>>>>>>>: {}", type);
			log.info("몇개조회: {}", charts.size());
			charts.forEach(chart -> log.info("{}", chart));
			log.info("*****************");
		}

		log.info("출력끝~~~~~~~~~");
	}
}