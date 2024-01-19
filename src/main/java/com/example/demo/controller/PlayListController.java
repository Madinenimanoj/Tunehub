package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PlayListController {
	@Autowired
	SongService songService;
	@Autowired
	PlayListService playListService;
	@GetMapping("/createPlayList")
	  public String createPlayList(Model model) {
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createPlayList";
		  
	  }
	@PostMapping("/addPlayList")
	public String addPlayList(@ModelAttribute Playlist playlist) {
		//updating playlist table
		 playListService.addPlayList(playlist);
		 
		 //updating song table
		 List<Song> songList = playlist.getSongs();
		 for(Song s:songList) {
			 s.getPlaylists().add(playlist);
			 songService.updateSong(s);
		 }
		return "adminHome";
		
	}
	@GetMapping("/viewPlayLists")
	public String viewPlayLists(Model model) {
		List<Playlist> allPlaylists = playListService.fetchAllPlayLists();
		model.addAttribute("allPlayLists", allPlaylists);
		return "displayPlayLists";
	}
	

}
