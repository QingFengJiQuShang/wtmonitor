package com.jrsoft.fri.cs;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;

public class music {
  
    //������Ƶ�ļ�
    public void Play(){
    	System.out.println("��ʼ����");
        String fileurl = "F:\\��Ӱ\\music.wav";
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
            AudioFormat aif = ais.getFormat();
            SourceDataLine sdl = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
            sdl = (SourceDataLine)AudioSystem.getLine(info);
            sdl.open(aif);
            sdl.start();
           
            //play
            int nByte = 0;
            byte[] buffer = new byte[128];
            while(nByte != -1){
                nByte = ais.read(buffer,0,128);
                if(nByte >= 0){
                    int oByte = sdl.write(buffer, 0, nByte);
                    //System.out.println(oByte);
                }
            }
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
        
        Play();
        
    }
}

