import java.util.LinkedList;
import java.io.*;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();
        String lineRead;
        String[] splitLine = new String[0];
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
}
