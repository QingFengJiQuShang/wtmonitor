package com.jrsoft.fri.cs;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Music {
	
    public  SourceDataLine sdl = null;
    public AudioData ad=null;
    public ContinuousAudioDataStream cads =null;
	//������Ƶ�ļ�
    public void Play(){
		    	System.out.println("��ʼ����");
		    	String musicurl=System.getProperty("user.dir");
		  //  	System.out.println(musicurl);
		    	musicurl=musicurl.replace("\\bin", "");  
		    	musicurl+="\\webapps\\wtmonitor";  
		  //  	System.out.println(musicurl);
		        String fileurl = musicurl+"\\music\\music.wav";
		        File url=new File(fileurl);
		        try{
		            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
		            AudioFormat aif = ais.getFormat();
		            InputStream inputStream=new FileInputStream(url);
		            AudioStream as =new AudioStream(inputStream);
		            ad = as.getData();
		            DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
		            sdl = (SourceDataLine)AudioSystem.getLine(info);
		            sdl.open(aif);
		            sdl.start();
		            sdl.stop();
		        }catch(UnsupportedAudioFileException e){
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO �Զ����� catch ����
		            e.printStackTrace();
		        } catch (LineUnavailableException e) {
		            // TODO �Զ����� catch ����
		            e.printStackTrace();
		        }
        
    }
    private static Object getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	public void start (){
    	Play();
    	cads = new ContinuousAudioDataStream(ad);
    	//ѭ�����ſ�ʼŶ
		AudioPlayer.player.start(cads);
    }
    public void stop(ContinuousAudioDataStream cads ){
    	//ѭ������ֹͣ
		AudioPlayer.player.stop(cads);
    }

}

