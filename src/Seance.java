public class Seance {
    int id; // индентификатор
    private String name; // название фильма
    private String time; // время фильма
    private int number; // номер зала
    private boolean[][] reserved; // таблица резерва

    public Seance(String name, String time, Hall hall, int id)
    {
        this.name = name;
        this.time = time;
        this.id = id;
        this.number = hall.getNumber();
        int i, j, k = hall.getTable().length; //инициализция массива все элементы по умолчанию true
        this.reserved = new boolean[k][];
        for (i = 0; i < k; i++) {
            this.reserved[i] = new boolean[hall.getTable()[i].length];
            for (j = 0; j < hall.getTable()[i].length; j++) {
                this.reserved[i][j] = true; //
            }
        }
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return id;
    }

    public void setReserved(int row, int seat){
        this.reserved[row][seat] = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean[][] getReserved(){
        return this.reserved;
    }


    void viewReserve() {
        String Space[] = new String[]{"   ","  "," ",""};
        for (int i = 0; i < reserved.length; i++) {
            System.out.print((i + 1) + ") ");
            if (this.number == 1) System.out.print(Space[i]); // вставка пробелов в начале строки
            if (this.number == 3) System.out.print(Space[Space.length-i-1]);
            for (int j = 0; j < reserved[i].length; j++) {
                if (reserved[i][j]) System.out.print("O ");
                else System.out.print("X ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Зал " + number + ", " +
                "Фильм '" + name + "', " +
                "Время " + time + ", id" + id;
    }

}
