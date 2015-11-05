import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Cinema {
    private Hall[] halls;
    private Seance[] seances;

    public Hall[] getHalls() {
        return halls;
    }

    public Seance[] getSeances() {
        return seances;
    }


    Cinema(Hall[] halls, Seance[] seances) {
        this.halls = halls;
        this.seances = seances;
    }

    Hall getHall(int number) {
        return halls[number - 1];
    }

    void print() {
        for (Seance s : seances) {
            System.out.println(s);
        }
        for (Hall h : halls) {
            h.printHall();
        }
    }

    ArrayList<Seance> find(String str) {
        ArrayList<Seance> found = new ArrayList<>();
        for (Seance s : seances) {
            if (s.getName().toLowerCase().contains(str.toLowerCase())) {
                found.add(s);
            }
        }
        return found;
    }

    Seance findSeanceById(int num) {
        for (Seance s : seances) {
            if (s.getId() == num) {
                return s;
            }
        }
        return null;
    }

    boolean addReserve (Seance s, int row, int seat) {
        row--;
        seat--;
        boolean [][] temptable = s.getReserved();
        if (row >= 0 && seat >= 0 && row < temptable.length && seat < temptable[row].length) {
            if (temptable[row][seat]) {
                s.setReserved(row, seat);
                return true;
            } else {
                System.out.println("Место занято");
                return false;
            }
        }
        System.out.println("Введены некорректные данные");
        return false;
    }

    public static void main(String[] args) {
        Hall[] halls = {
                new Hall(1, new int[]{3, 4, 5, 6}),
                new Hall(2, new int[]{4, 4, 4, 4}),
                new Hall(1, new int[]{6, 5, 4, 3})
        };
        Seance[] seances = {
                new Seance("Hulk", "10:30", halls[0], 1),
                new Seance("Rain man", "13:30", halls[1], 2),
                new Seance("Pretty Woman", "12:40", halls[2], 3),
                new Seance("Pretty Woman", "17:50", halls[0], 4),
        };
        Cinema my = new Cinema(halls, seances);
        Scanner sc = new Scanner(System.in);
        String help = "Список доступных команд:\n"+
                "print - показать все сеансы и залы\n" +
                "addR  - добавить резерв (требуется ввод id/ряда/места)\n" +
                "findF - поиск фильма по названию\n" +
                "showR - показать занятые места (требуется id сеанса)" +
                "help  - выводит список команд\n" +
                "exit  - завершить работу";
        System.out.println(help +"\n"+"Input command");
        boolean flag = true;
        while (flag) {
            switch (sc.nextLine().toLowerCase()) {
                case "exit":
                    flag = false;
                    break;
                case "findf":
                    System.out.println("Введите название фильма");
                    int i = 0;
                    for (Seance found : my.find(sc.nextLine())) {
                        i++;
                        System.out.println(i+") "+found);
                    }
                    if (i==0) System.out.println("Ничего не найдено");
                    break;
                case "print":
                    my.print();
                    break;
                case "addr":
                    int num;
                    Scanner scan = new Scanner(System.in);
                    try {
                        System.out.print("Введи id сеанса: ");
                        num = scan.nextInt();
                    } catch (Exception exc) {
                        System.out.println("Ошибка: введено не число");
                        break;
                    }
                    if (my.findSeanceById(num) == null) {
                        System.out.println("Сеанс с таким id не найден");
                        break;
                    } else
                        try {
                            Seance seance = my.findSeanceById(num);
                            seance.viewReserve();
                            System.out.print("Введите номер ряда: ");
                            int row = scan.nextInt();
                            System.out.print("Введите номер места: ");
                            int seat = scan.nextInt();
                            if (my.addReserve(seance, row, seat))
                                System.out.println("Резерв успешно добавлен");
                        } catch (InputMismatchException exc) {
                            System.out.println("Ошибка: введено не число");
                        }
                    break;

                case "showr":
                    Scanner p = new Scanner(System.in);
                    int n;
                    System.out.print("Введите id сеанса: ");
                    if(p.hasNextInt() ){
                        n = p.nextInt();
                        if (my.findSeanceById(n)==null) System.out.println("Сеанс с таким id не найден");
                        else my.findSeanceById(n).viewReserve();
                    } else System.out.println("Вы ввели не целое число");
                    break;
                case "help":
                    System.out.println(help);
                    break;
                default:
                    System.out.println("Invalid command, type help or try again");
                    break;
            }
        }
    }
}