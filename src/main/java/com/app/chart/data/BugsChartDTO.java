package com.app.chart.data;

import com.jpa.entity.BugsChart;
import com.jpa.entity.FloChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class BugsChartDTO {

    private String title;
    private String artist;
    private String album;
    private Integer rank;

    public static BugsChartDTO fromEntity(BugsChart chart) {
        return new BugsChartDTO(
                chart.getTitle(),
                chart.getArtist(),
                chart.getAlbum(),
                chart.getRank()
        );
    }
}