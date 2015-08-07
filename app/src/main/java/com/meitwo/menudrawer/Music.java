package com.meitwo.menudrawer;


public class Music {
	

	private String Title;

	private String artist;
	private String location;
	

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getData() {
		return location;
	}
	public void setData(String location) {
		this.location = location;
	}

	
	
//	{MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME, 
//		MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DURATION,
//		MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DATA
}
