package Model;

public class songDTO {

	String singer;
	String engSinger;
	String song;
	String engSing;
	String hintSinger;
	String hintSong;
	String musicPath;
	
	public songDTO(String singer, String engSinger, String song, String engSing, String hintSinger, String hintSong) {
		this.singer = singer;
		this.engSinger = engSinger;
		this.song = song;
		this.engSing = engSing;
		this.hintSinger = hintSinger;
		this.hintSong = hintSong;
		this.musicPath = musicPath;
	}

	public String getSinger() {
		return singer;
	}

	public String getEngSinger() {
		return engSinger;
	}

	public String getSong() {
		return song;
	}

	public String getEngSing() {
		return engSing;
	}

	public String getHintSinger() {
		return hintSinger;
	}

	public String getHintSong() {
		return hintSong;
	}
	
	public String getMusicPath() {
		return musicPath;
	}
	
}

