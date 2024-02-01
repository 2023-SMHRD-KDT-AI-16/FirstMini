package Model;

public class songDTO {

	String singer;
	String engSinger;
	String song;
	String engSong;
	String hintSinger;
	String hintSong;
	String folder;
	
	public songDTO(String singer, String engSinger, String song, String engSong, String hintSinger, String hintSong, String folder) {
		this.singer = singer;
		this.engSinger = engSinger;
		this.song = song;
		this.engSong = engSong;
		this.hintSinger = hintSinger;
		this.hintSong = hintSong;
		this.folder = folder;
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

	public String getEngSong() {
		return engSong;
	}

	public String getHintSinger() {
		return hintSinger;
	}

	public String getHintSong() {
		return hintSong;
	}
	
	public String getFolder() {
		return folder;
	}
	
}

