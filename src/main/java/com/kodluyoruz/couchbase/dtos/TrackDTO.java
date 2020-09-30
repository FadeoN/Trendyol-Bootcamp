package com.kodluyoruz.couchbase.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class TrackDTO {


    @NonNull private String name;
    @NonNull private String length;
    @NonNull private String artist;

}
