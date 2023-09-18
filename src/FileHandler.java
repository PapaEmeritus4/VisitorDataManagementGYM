import java.util.LinkedList;
import java.io.*;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member member;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();

            while (lineRead != null) {
                splitLine = lineRead.split(", ");

                if (splitLine[0].equals("S")) {
                    member = new SingleClubMember('S', Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                } else {
                    member = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                }
                members.add(member);
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void appendFile(String mem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(mem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteFile(LinkedList<Member> members) {
        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))) {
            for (int i = 0; i <members.size() ; i++) {
                s = members.get(i).toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           File f = new File("members.csv");
           File tf = new File("members.temp");

           f.delete();
           tf.renameTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
