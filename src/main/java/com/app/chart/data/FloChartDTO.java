package com.app.chart.data;

import com.jpa.entity.FloChart;
import com.jpa.entity.GenieChart;
import com.jpa.entity.chartType.FloChartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@Getter
public class FloChartDTO {

    private Integer id;
    private String title;
    private String artist;
    private String album;
    private String albumImageUrl;
    private Integer rank;
    private String change;
    private FloChartType chartType;
    private Timestamp crawledAt;

    /*public static FloChartDTO fromEntity(FloChart chart) {
        return new FloChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }*/

    public static FloChartDTO fromEntity(FloChart chart) {
        return new FloChartDTO(
                chart.getId(),
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getAlbumImageUrl(),
                chart.getRank(),
                chart.getChange(),
                chart.getChartType(),
                chart.getCrawledAt()
        );
    }
}