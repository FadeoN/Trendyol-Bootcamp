package com.kodluyoruz.couchbase.dtos;


import lombok.Data;
import lombok.NonNull;


@Data
public class PlaylistRequestDTO {

    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String userID;

}
