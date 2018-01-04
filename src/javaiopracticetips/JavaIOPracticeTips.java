
package javaiopracticetips;
import java.io.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JavaIOPracticeTips {
//1:Merging two Text files Using IO-Packages. One after Other.
//2:Merging two files Line By Line using java.io package.
//3:Using mark() and reset() method.
//4:Copying one file to Other using try-with-resources technique.
//5:Copying one file to Other using modified form of BufferedInputStream and BufferedOutputStream reader & writer Respectively.
//6:Creating readFile and writeFile method to copy data from one file and save it in List object before copying it to another file.
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        mergingTwoFiles();
        mergingTwoFilesLineByLine();
        markingAStream();
        CopyFileSample();
        CopyFileSample1();
        //Benfits: Of Using Below techniques: 1: Instead of immediately copying data to output file we stored in Object file so that we can modify 
        //and display the data. 2: In the previous method we had to copy code one byte or as array of bytes at a time but in this we can call entire
        //String at a time.
        readFile();
        List<String> data=readFile();
        writeFile(data);
        
    }
    public static void mergingTwoFiles() throws FileNotFoundException, IOException{
        PrintWriter pw=new PrintWriter("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file3.txt");
        BufferedReader br1=new BufferedReader(new FileReader("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file2.txt"));
        String line1=br1.readLine();
        while(line1 !=null){
            pw.println(line1);
            line1=br1.readLine();
        }
        BufferedReader br2=new BufferedReader(new FileReader("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file1.txt"));
        String line2=br2.readLine();
        pw.println("------------------------------------------------------");
        while(line2 !=null){
            pw.println(line2);
            line2=br2.readLine();
        }
        pw.flush();
        br1.close();
        br2.close();
        pw.close();
    }
    public static void mergingTwoFilesLineByLine() throws FileNotFoundException, IOException{
        PrintWriter pw1=new PrintWriter("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file6.txt");
        BufferedReader br4=new BufferedReader(new FileReader("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file4.txt"));
        BufferedReader br5=new BufferedReader(new FileReader("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file5.txt"));
        String line4=br4.readLine();
        String line5=br5.readLine();
        while((line4 !=null) || (line5 != null)){
            if(line4!= null){
                pw1.println(line4);
                line4=br4.readLine();
            }
            if(line5!= null){
                pw1.println(line5);
                line5=br5.readLine();
            }
        }
        pw1.flush();
        br4.close();
        br5.close();
        pw1.close();
    }
    public static void markingAStream() throws FileNotFoundException, IOException{
        BufferedInputStream bif=
                new BufferedInputStream(new FileInputStream("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file7.txt"));
        System.out.println((char) bif.read());
        if(bif.markSupported()){
            bif.mark(100); // limit the read of characters in given file
            System.out.println((char)bif.read());
            System.out.println((char)bif.read());
            bif.reset();    //will read again the previous statements with-in if.
        }
        System.out.println((char)bif.read());
        System.out.println((char)bif.read());
        System.out.println((char)bif.read());
    }
    public static void CopyFileSample() throws FileNotFoundException, IOException{    // Reads One file and copy to the destination file.
        //using InputStream.
            try(InputStream in=new FileInputStream("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file8.txt");
            OutputStream out =new FileOutputStream("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file9.txt")){
            int line=in.read();
                while((line=in.read()) != -1){
                    out.write(line);
                }
            }
    } 
    public static void CopyFileSample1() throws FileNotFoundException, IOException{
        // A modified form of CopyFileSample as it uses BufferrInputStream
        try(InputStream in1=new BufferedInputStream(new FileInputStream("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file10.txt"));
        OutputStream out1=new BufferedOutputStream(new FileOutputStream("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file11.txt"))){
            byte[] buffer=new byte[1024];
            int lengthRead;
                while((lengthRead =in1.read(buffer))>0){
                    out1.write(buffer, 0, lengthRead);
                    out1.flush();
                }
        }
    }
    public static List<String> readFile() throws FileNotFoundException, IOException{
        List<String> data=new ArrayList<String>();
        try(BufferedReader bf=new BufferedReader(new FileReader("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file10.txt"));){
            String s;
                while((s=bf.readLine()) != null ){
                    data.add(s);
                }
        }
        return data;
    }
    public static void writeFile(List<String> data) throws IOException,FileNotFoundException{
        try(BufferedWriter bf=new BufferedWriter(new FileWriter("C:\\Users\\Beenish Sajjad\\Documents\\JavaProgram\\pack1\\file11.txt"));){
            for(String s: data){
                String g=s.replaceAll("Lorem Ipsum", "B");
                bf.write(g);
                bf.newLine();
            }
        }
    }
}
