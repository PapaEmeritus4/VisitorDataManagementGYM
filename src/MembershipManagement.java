import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    private final Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;

        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0) throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();

                System.out.println("ERROR: INVALID INPUT. Please try again: ");
            }
        }
        return choice;
    }

    private void printClubOptipons() {
        System.out.println("\n1) Club Mercury ");
        System.out.println("2) Club Neptune ");
        System.out.println("3) Club Jupiter ");
        System.out.println("4) Multi Clubs ");
    }

    public int getChoice() {
        int choice;

        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("===================================");
        System.out.println("1) Add Member ");
        System.out.println("2) Remove Member ");
        System.out.println("3) Display Member Information ");

        System.out.print("Please select an option (or Enter -1 to quit): ");
        choice = getIntInput();

        return choice;
    }

    public String addMembers(LinkedList<Member> members) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member member;
        Calculator<Integer> calculator;

        System.out.print("Enter Member`s name: ");
        name = reader.nextLine();

        printClubOptipons();
        System.out.println("Please enter the member`s club ID: ");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.print("The entered value is not valid, try again: ");
            club = getIntInput();
        }

        if (members.size() > 0) memberID = members.getLast().getMemberID() + 1;
        else memberID = 1;

        if (club != 4) {
            calculator = (n) -> {
              switch (n) {
                  case 1:
                      return 900;
                  case 2:
                      return 950;
                  case 3:
                      return  1000;
                  default:
                      return -1;
              }
            };
            fees = calculator.calculateFees(club);

            member = new SingleClubMember('S', memberID, name, fees, club);
            members.add(member);

            mem = member.toString();

            System.out.println("\nSTATUS: Single Club Member added!\n");
        } else {
            calculator = (n) -> {
                switch (n) {
                    case 4:
                        return  1200;
                    default:
                        return -1;
                }
            };
            fees = calculator.calculateFees(club);

            member = new MultiClubMember('M', memberID, name, fees, 100);
            members.add(member);

            mem = member.toString();

            System.out.println("\nSTATUS: Multi Club Member added!\n");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> members) {
        int memberID;

        System.out.println("Enter ID Member: ");
        memberID = getIntInput();

        for (int i = 0; i < members.size() ; i++) {
            if (members.get(i).getMemberID() == memberID) {
                members.remove(i);
                System.out.println("The Member has been successfully deleted");
                return;
            }
        }
        System.out.println("Member ID not found");
    }

    public void printMemberInfo(LinkedList<Member> members) {
        int memberID;

        System.out.println("Enter ID Member to display information: ");
        memberID = getIntInput();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberID() == memberID) {
                String[] memberInfo = members.get(i).toString().split(", ");
                System.out.println("\n\nMember type = " + memberInfo[0]);
                System.out.println("Member ID = " + Integer.parseInt(memberInfo[1]));
                System.out.println("Member Name = " + memberInfo[2]);
                System.out.println("Member Fees = " + Double.parseDouble(memberInfo[3]));
                if (memberInfo[0].equals("S")) {
                    System.out.println("Club ID = " + Integer.parseInt(memberInfo[4]));
                } else {
                    System.out.println("Membership Points = " + Integer.parseInt(memberInfo[4]));
                }
                return;
            }
        }
        System.out.println("Member ID not found");
    }
}