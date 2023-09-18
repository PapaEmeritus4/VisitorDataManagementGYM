import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {
        String member;

        MembershipManagement membershipManagement = new MembershipManagement();
        FileHandler fileHandler = new FileHandler();

        LinkedList<Member> members = fileHandler.readFile();
        int choice = membershipManagement.getChoice();

        while (choice != -1) {
            switch (choice) {
                case 1:
                     member = membershipManagement.addMembers(members);
                    fileHandler.appendFile(member);
                    break;
                case 2:
                    membershipManagement.removeMember(members);
                    fileHandler.overwriteFile(members);
                    break;
                case 3:
                    membershipManagement.printMemberInfo(members);
                    break;
                default:
                    System.out.println("You have chosen an invalid option");
                    break;
            }
            choice = membershipManagement.getChoice();
        }
        System.out.println("\nGood Bye!");
    }
}
