package com.kodluyoruz.couchbase.controllers;

import com.kodluyoruz.couchbase.dtos.PlaylistRequestDTO;
import com.kodluyoruz.couchbase.models.Playlist;
import com.kodluyoruz.couchbase.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> createWithUserID(@RequestBody PlaylistRequestDTO playlistRequestDTO){
        Playlist playlist = playlistService.createWithUserId(playlistRequestDTO);
        URI playlistURI = URI.create(String.format("/v1/playlists/%s", playlist.getId()));
        return ResponseEntity.created(playlistURI).build();
    }


    @GetMapping("/userId={userID}")
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam String userID){
        List<Playlist> playlists = playlistService.findAllByUserId(userID);
        return ResponseEntity.ok(playlists);
    }


    @GetMapping("/{playlistID}")
    public ResponseEntity<Playlist> findByPlaylistId(@RequestParam String playlistID){
        Optional<Playlist> playlist = playlistService.findById(playlistID);
        return ResponseEntity.ok(playlist.get());
    }


    @DeleteMapping("/{playlistID}")
    public ResponseEntity<Void> deleteByPlaylistId(@RequestParam String playlistID){
        playlistService.deleteById(playlistID);
        return ResponseEntity.noContent().build();
    }
}
