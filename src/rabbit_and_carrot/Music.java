package rabbit_and_carrot;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Music {  
    private AudioStream as; // 单次播放声音用
    ContinuousAudioDataStream cas;// 循环播放声音  
    // 构造函数  
    public Music(String path) {  
        try {  
        	InputStream in=new FileInputStream(path);
            // 打开一个声音文件流作为输入  
            as = new AudioStream(in);  
        } catch(FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch(IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    // 一次播放开始 
    public void start() {  
        if(as == null) {  
            System.out.println("AudioStream object is not created!");  
            return;  
        } else {  
            AudioPlayer.player.start(as);  
        }  
    }  
  
    // 一次播放停止  
    public void stop() {  
        if(as == null) {  
            System.out.println("AudioStream object is not created!");  
            return;  
        } else {  
            AudioPlayer.player.stop(as);  
        }  
    }  
  
    // 循环播放开始 
    public void continuousStart() {  
        // Create AudioData source.  
        AudioData data = null;  
        try {  
            data = as.getData();  
        } catch(IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
        // Create ContinuousAudioDataStream.  
        cas = new ContinuousAudioDataStream(data);  
  
        // Play audio.  
        AudioPlayer.player.start(cas);  
    }  
  
    // 循环播放 停止  
    public void continuousStop() {  
        if(cas != null) {  
            AudioPlayer.player.stop(cas);  
        }  
    }  
  
}  
