package com.jpa.entity;

import com.jpa.entity.chartType.GenieChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "genie_chart")
@Getter
@Setter
@NoArgsConstructor
public class GenieChart {

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
    private GenieChartType chartType;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt;
}