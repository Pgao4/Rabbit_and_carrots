package rabbit_and_carrot;
import java.io.File;    //文件对象

import java.io.FileWriter;     //文件写入流
import java.io.BufferedWriter; //缓冲写入流
import java.io.FileReader;
import java.io.BufferedReader;
public class Save {
	static File name1 = new File("dat/name.txt");
	static File score1 = new File("dat/score.txt");
	static int  newscoreindex=0;
	static boolean isrank=false;
	public static String[] RanknameRead(){
		
		String str[] =  new String[5];
		
		try {
			FileReader fr = new FileReader(name1);
			BufferedReader br = new BufferedReader(fr);
			for(int i=0;i<5;i++)
			str[i]=br.readLine();
			
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;	
	}
	
public static String[] RankscoreRead(){
		
		String str[] =  new String[5];
		
		try {
			FileReader fr = new FileReader(score1);
			BufferedReader br = new BufferedReader(fr);
			for(int i=0;i<5;i++)
			str[i]=br.readLine();
			
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;	
	}
 	
public static void RankscoreWirte(int newscore){
	try {
		String score[] = RankscoreRead();
		outer:
		for(int i=0;i<5;i++){
			int oldscore =Integer.parseInt(score[i]);
			if(oldscore <= newscore){
				isrank=true;
				newscoreindex =i;
				for(int k=4;k>i;k--){
					   score[k]=score[k-1];
				   }
				score[newscoreindex]=newscore+"";
				   break outer;
			}
		}
		//默认是覆盖写入
		FileWriter scorefw = new FileWriter(score1);   //加入第二参数true,表示追加写入
		BufferedWriter scorebw = new BufferedWriter(scorefw);
	
		//通过使用bw来完成写文件的工作
		for(int i=0;i<5;i++){
		scorebw.write(score[i]);    //写入一条数据
		scorebw.newLine();      //换新行
		}

		scorebw.close();
		scorefw.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public static void RanknameWirte(String namestr){
	String[] name = RanknameRead();
	for(int k=4;k>newscoreindex;k--){
		name[k]=name[k-1];
	   }
	name[newscoreindex]=namestr;
	
	try {		
		//默认是覆盖写入
		FileWriter namefw = new FileWriter(name1);   //加入第二参数true,表示追加写入
		BufferedWriter namebw = new BufferedWriter(namefw);
		
		//通过使用bw来完成写文件的工作
		for(int i=0;i<5;i++){
			System.out.println(name[1]);
			namebw.write(name[i]);    //写入一条数据
			namebw.newLine();      //换新行
		}

		namebw.close();
		namefw.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	isrank=false;
}
}
