package rabbit_and_carrot;
import java.io.File;    //�ļ�����

import java.io.FileWriter;     //�ļ�д����
import java.io.BufferedWriter; //����д����
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
		//Ĭ���Ǹ���д��
		FileWriter scorefw = new FileWriter(score1);   //����ڶ�����true,��ʾ׷��д��
		BufferedWriter scorebw = new BufferedWriter(scorefw);
	
		//ͨ��ʹ��bw�����д�ļ��Ĺ���
		for(int i=0;i<5;i++){
		scorebw.write(score[i]);    //д��һ������
		scorebw.newLine();      //������
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
		//Ĭ���Ǹ���д��
		FileWriter namefw = new FileWriter(name1);   //����ڶ�����true,��ʾ׷��д��
		BufferedWriter namebw = new BufferedWriter(namefw);
		
		//ͨ��ʹ��bw�����д�ļ��Ĺ���
		for(int i=0;i<5;i++){
			System.out.println(name[1]);
			namebw.write(name[i]);    //д��һ������
			namebw.newLine();      //������
		}

		namebw.close();
		namefw.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	isrank=false;
}
}
