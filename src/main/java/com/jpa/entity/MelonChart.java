package com.jpa.entity;

import com.jpa.entity.chartType.MelonChartType;
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

import java.time.LocalDateTime;

@Entity
@Table(name = "melon_chart")
@Getter
@Setter
@NoArgsConstructor
public class MelonChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "song_id")
    private Long songId;

    private String title;
    private String artist;
    private String album;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    private Integer rank;

    @Column(name = "`change`")
    private String change;

    @Column(name = "detail_url")
    private String detailUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "chart_type")
    private MelonChartType chartType;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt;
}
