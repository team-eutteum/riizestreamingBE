package com.jpa.entity;

import com.jpa.entity.chartType.MelonChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "melon_chart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MelonChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "song_id")
    private Long songId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    @Column(nullable = false)
    private Integer rank;

    @Column(name = "`change`")
    private String change;

    @Column(name = "detail_url")
    private String detailUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "chart_type")
    private MelonChartType chartType;

    @Column(name = "crawled_at")
    private Timestamp crawledAt;
}
