package com.kodluyoruz.couchbase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Track {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String length;
    private String artist;

}
