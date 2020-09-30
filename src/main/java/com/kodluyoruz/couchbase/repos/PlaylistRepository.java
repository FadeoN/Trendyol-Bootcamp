package com.kodluyoruz.couchbase.repos;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.couchbase.models.Playlist;
import com.kodluyoruz.couchbase.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    @Autowired
    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }


    public void insert(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
    }

    public void delete(String playlistID){
        playlistCollection.remove(playlistID);
    }

    public void addTrackToPlaylist(Playlist playlist, Track track){
        playlist.getTracks().add(track);
        playlistCollection.replace(playlist.getId(), playlist);
    }

    public void removeTrackToPlaylist(Playlist playlist, String trackID){
        String statement = String.format("UPDATE playlist p SET p.tracks = ARRAY track FOR track IN tracks WHEN track.id <> '%s' END WHERE p.id = '%s';", trackID, playlist.getId());
        couchbaseCluster.query(statement);
    }

    public boolean isTrackInGivenPlaylist(String playlistID, String trackID){
        String statement = String.format("SELECT playlist.id  FROM playlist WHERE playlist.id = '%s' AND ANY track IN tracks SATISFIES track.id = '%s' END;", playlistID, trackID);
        QueryResult query = couchbaseCluster.query(statement);
        return !query.rowsAs(Playlist.class).isEmpty();

    }

    public Optional<Playlist> findByIdOptional(String playlistId) {
        try {
            GetResult getResult = playlistCollection.get(playlistId);
            Playlist playlist = getResult.contentAs(Playlist.class);
            return Optional.of(playlist);

        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    public List<Playlist> findAllByUserId (String userID) {
        String statement = String.format("Select id, name, description, followersCount, tracks, userID" +
                " from playlist WHERE playlist.userID = '%s';", userID);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }


}
