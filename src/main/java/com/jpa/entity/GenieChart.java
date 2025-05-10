package com.jpa.entity;

import com.jpa.entity.chartType.GenieChartType;
import com.jpa.entity.chartType.MelonChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "genie_chart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GenieChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    private Integer rank;

    @Column(name = "`change`")
    private String change;

    @Column(name = "chart_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenieChartType chartType;

    @Column(name = "crawled_at", nullable = false)
    private Timestamp crawledAt;
}