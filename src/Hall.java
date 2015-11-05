public class Hall {
    private int number; //номер зала
    private int table[][];

    public Hall(int number, int seats[]) {
        this.number = number;
        int i,j;
        this.table = new int [seats.length][];
        for (i = 0; i<seats.length; i++) {
            this.table[i] = new int[seats[i]];
            for (j = 0; j < seats[i]; j++) {
                this.table[i][j]=j+1;
            }
        }
    }

    public int[][] getTable() {
        return table;
    }

    public int getNumber() {
        return number;
    }

    public void printHall(){
        System.out.println("Hall "+this.number);
        String Space[] = new String[]{"   ","  "," ",""};
        for(int i = 0;i<table.length;i++){
            System.out.print((i + 1) + ") ");
            if (this.number == 1) System.out.print(Space[i]);
            if (this.number == 3) System.out.print(Space[Space.length-i-1]);
            for (int j = 0;j<table[i].length;j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}