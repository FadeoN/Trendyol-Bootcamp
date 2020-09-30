package com.kodluyoruz.couchbase.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Playlist {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;

    @Builder.Default
    private Integer followersCount = 0;
    @Builder.Default
    private Set<Track> tracks = new HashSet<>();

    // Transient
    private Integer trackCount;
    private String userID;


    public Integer getTrackCount() {
        return tracks.size();
    }

}
