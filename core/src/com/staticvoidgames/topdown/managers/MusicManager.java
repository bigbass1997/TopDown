package com.staticvoidgames.topdown.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
	
	private Music[] musics= new Music[1];
	int current;
	public MusicManager() {
		musics[0]=Gdx.audio.newMusic(Gdx.files.internal("DeviolsDanceForLoop.wav"));
		musics[0].setLooping(true);
	}
	public void play(int musicId){
		musics[current].pause();
		musics[musicId].play();
		current = musicId;
	}
}
