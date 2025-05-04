package com.jpa.entity;

import com.jpa.entity.chartType.FloChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flo_chart")
@Getter
@Setter
@NoArgsConstructor
public class FloChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String artist;
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    @Column(name = "`change`")
    private String change;

    private Integer rank;

    @Enumerated(EnumType.STRING)
    @Column(name = "chart_type")
    private FloChartType chartType;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt;
}