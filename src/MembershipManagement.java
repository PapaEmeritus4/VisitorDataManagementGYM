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
        System.out.println("1) Club Mercury ");
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
            calculator = (x) -> {
                switch (x) {
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

    //закончил на 254 стр
}
