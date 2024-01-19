package com.example.demo.services;


import java.util.List;

import com.example.demo.entities.Playlist;

public interface PlayListService {
     
	public void addPlayList(Playlist playlist);

	public List<Playlist> fetchAllPlayLists();

}
