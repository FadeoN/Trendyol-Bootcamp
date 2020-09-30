package com.kodluyoruz.couchbase.controllers;

import com.kodluyoruz.couchbase.dtos.TrackDTO;
import com.kodluyoruz.couchbase.models.Track;
import com.kodluyoruz.couchbase.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/playlists")
public class TrackController {


    private final PlaylistService playlistService;

    @Autowired
    public TrackController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    @PostMapping("/{playlistID}/tracks")
    public ResponseEntity<Void> addTrackToPlaylist(@RequestParam String playlistID, @RequestBody TrackDTO trackDTO){
        Track track = playlistService.addTrackToPlaylist(playlistID, trackDTO);

        URI trackURI = URI.create(String.format("/v1/playlists/%s/tracks/%s", playlistID, track.getId()));

        return ResponseEntity.created(trackURI).build();
    }

    @DeleteMapping("/{playlistID}/tracks/{trackID}")
    public ResponseEntity<Void> addTrackToPlaylist(@RequestParam String playlistID, @RequestParam String track){
        playlistService.removeTrackFromPlaylist(playlistID, track);
        return ResponseEntity.noContent().build();
    }


}
