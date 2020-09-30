package com.kodluyoruz.couchbase.services;

import com.kodluyoruz.couchbase.dtos.PlaylistRequestDTO;
import com.kodluyoruz.couchbase.dtos.TrackDTO;
import com.kodluyoruz.couchbase.models.Playlist;
import com.kodluyoruz.couchbase.models.Track;

import java.util.List;
import java.util.Optional;


public interface PlaylistService {

    Playlist createWithUserId(PlaylistRequestDTO playlistDTO);
    Optional<Playlist> findById(String playlistID);
    List<Playlist> findAllByUserId(String userID);
    void deleteById(String playlistID);

    Track addTrackToPlaylist(String playlistID, TrackDTO track);
    void removeTrackFromPlaylist(String playlistID, String trackID);



}
