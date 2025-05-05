package com.app.chart.data;

import com.jpa.entity.FloChart;
import com.jpa.entity.GenieChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class FloChartDTO {

    private String title;
    private String artist;
    private String album;
    private Integer rank;

    public static FloChartDTO fromEntity(FloChart chart) {
        return new FloChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }
}