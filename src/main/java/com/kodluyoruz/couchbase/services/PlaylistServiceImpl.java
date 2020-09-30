package com.kodluyoruz.couchbase.services;

import com.kodluyoruz.couchbase.dtos.PlaylistRequestDTO;
import com.kodluyoruz.couchbase.dtos.TrackDTO;
import com.kodluyoruz.couchbase.models.Playlist;
import com.kodluyoruz.couchbase.models.Track;
import com.kodluyoruz.couchbase.repos.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class PlaylistServiceImpl implements PlaylistService {


    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createWithUserId(PlaylistRequestDTO playlistDTO) {
        Playlist playlist = Playlist.builder()
                .name(playlistDTO.getName())
                .description(playlistDTO.getDescription())
                .userID(playlistDTO.getUserID()).build();

        playlistRepository.insert(playlist);

        return playlist;

    }

    @Override
    public Optional<Playlist> findById(String playlistID) {
        return playlistRepository.findByIdOptional(playlistID);
    }

    @Override
    public List<Playlist> findAllByUserId(String userID) {
        return playlistRepository.findAllByUserId(userID);
    }

    @Override
    public void deleteById(String playlistID) {
        playlistRepository.delete(playlistID);
    }

    @Override
    public Track addTrackToPlaylist(String playlistID, TrackDTO trackDTO) {
        Optional<Playlist> playlist = playlistRepository.findByIdOptional(playlistID);
        if(!playlist.isPresent()) throw new NotFoundException("Playlist not found.");


        Track track = Track.builder()
                .name(trackDTO.getName())
                .length(trackDTO.getLength())
                .artist(trackDTO.getName()).build();

        playlistRepository.addTrackToPlaylist(playlist.get(), track);

        return track;

    }

    @Override
    public void removeTrackFromPlaylist(String playlistID, String trackID) {
        Optional<Playlist> playlist = playlistRepository.findByIdOptional(playlistID);
        if(!playlist.isPresent()) throw new NotFoundException("Playlist not found.");
        if(!playlistRepository.isTrackInGivenPlaylist(playlistID, trackID)) throw new NotFoundException("Track is not in given Playlist");

        playlistRepository.removeTrackToPlaylist(playlist.get(), trackID);

    }

}
