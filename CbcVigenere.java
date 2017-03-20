// Sebastian Patino
// CIS3360 HW1

import java.io.*;
import java.util.*;

public class CbcVigenere
{
	public static void main(String [] args) throws Exception
	{
		// Inserts Person objects
		Scanner in = new Scanner(new File(args[0]));
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> letters = new ArrayList<String>();
		ArrayList<String> keyword = new ArrayList<String>();
		ArrayList<String> vector = new ArrayList<String>();
		ArrayList<String> holder = new ArrayList<String>();
		ArrayList<String> padcount = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext()){
			list.add(in.next());
		}
		//Sets up the parameters needed for the output file
		System.out.println("CBC Vigenere by Sebastian Patino");
		System.out.println("Plaintext file name: "+args[0]);
		System.out.println("Vigenere keyword: "+args[1]);
		System.out.println("Initialization vector: "+args[2]);
		System.out.println();
		System.out.println("Clean Plaintext:");
		System.out.println();
		letters=populate(list);
		int x,y,z;
		int pad=0;
		String key;
		String iv;
		for(y=0;y<args[1].length();y++){
			key="";
			char a=args[1].charAt(y);
			key=key+a;
			keyword.add(key);
		}
		for(z=0;z<args[2].length();z++){
			iv="";
			char a=args[2].charAt(z);
			iv=iv+a;
			vector.add(iv);
		}
		int p;
		int blocksize=keyword.size();
		int blocknumber;
		String[] block=new String[blocksize];
		int front,back,finish, zerg;
		String part1, part2,insert;
		char first, second;
		for(zerg=0;zerg<blocksize;zerg++){
			holder.add(Integer.toString(zerg));
		}
		if(letters.size()%blocksize==0){
			blocknumber=blocksize-1;
		}else{
			blocknumber=blocksize;
		}
		for(x=0;x<(letters.size()+blocknumber)/blocksize;x++){
			//System.out.println(x);
			for(p=0;p<blocksize;p++){
				//System.out.println(p);
				insert="";
				if(letters.size()-1<(x*blocksize+p)){
					first='x';
				}else{
					part1=letters.get(x*blocksize+p);
					first=part1.charAt(0);
				}
				part2=vector.get(p);
				//System.out.printf(part2);
				second=part2.charAt(0);
				finish=((first-'a'))+((second-'a'));
				finish=finish%26;
				insert=insert+(char)(finish+'a');
				holder.set(p,insert);
				
				insert="";
				part1=keyword.get(p);
				part2=holder.get(p);
				first=part1.charAt(0);
				second=part2.charAt(0);
				finish=((first-'a'))+((second-'a'));
				finish=finish%26;
				insert=insert+(char)(finish+'a');
				vector.set(p,insert);
				if(letters.size()-1<(x*blocksize+p)){
					padcount.add(insert);
				}else{
					letters.set(x*blocksize+p,insert);
				}

			}
			
		}
		System.out.println();
		System.out.println("Cipher Text:");
		System.out.println();
		int counter;
		int charsize=letters.size();
		for(counter=0;counter<padcount.size();counter++){
			letters.add(padcount.get(counter));
		}
		letters=populate(letters);
		System.out.println();
		System.out.println("Number of characters in clean plaintext file: "+charsize);
		System.out.println("Block Size = "+vector.size());
		System.out.println("Number of pad characters added: "+padcount.size());
	}
	public static ArrayList<String> populate(ArrayList<String> lines){
		ArrayList<String> characters = new ArrayList<String>();
		Set<String> topdiag = new HashSet<String>();
		Set<String> bottomdiag = new HashSet<String>();
		//Strings initialized as empty
		String word="";
		String number="";
		String current,top,bot;
		int i,j,m,n,q,bottomdiagonal;
		int count=0;
		q=0;
		for(j=0;j<lines.size();j++){
			current=lines.get(j);
			for(i=0;i<current.length();i++){
				word="";
				char a=current.charAt(i);
				if(Character.isLowerCase(a)){
					word=word+a;
					characters.add(word);
					System.out.printf(word);
					count++;
					if(count%80==0)
						System.out.println();
				}else if(Character.isUpperCase(a)){
					word=word+a;
					word= word.toLowerCase();
					characters.add(word);
					System.out.printf(word);
					count++;
					if(count%80==0)
						System.out.println();
				}
			}
		}
		System.out.println();
		return characters;
	}
}
