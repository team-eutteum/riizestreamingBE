package com.jpa.entity;

import com.jpa.entity.chartType.BugsChartType;
import com.jpa.entity.chartType.FloChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bugs_chart")
@Getter
@Setter
@NoArgsConstructor
public class BugsChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String artist;
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    private Integer rank;

    @Column(name = "`change`")
    private String change;

    @Enumerated(EnumType.STRING)
    @Column(name = "chart_type")
    private BugsChartType chartType;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt;
}