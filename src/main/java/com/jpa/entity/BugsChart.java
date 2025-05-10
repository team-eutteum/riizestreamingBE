package com.jpa.entity;

import com.jpa.entity.chartType.BugsChartType;
import com.jpa.entity.chartType.FloChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bugs_chart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BugsChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    @Column(nullable = false)
    private Integer rank;

    @Column
    private String change;

    @Column(name = "chart_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BugsChartType chartType;

    @Column(name = "crawled_at", nullable = false)
    private Timestamp crawledAt;
}