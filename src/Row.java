import java.util.ArrayList;

public class Row {
    int RowNum;
    ArrayList<Integer> seats;

    public int getRowNum() {
        return RowNum;
    }

    public ArrayList<Integer> getSeats() {
        return seats;
    }

    public Row(int rowNum, ArrayList<Integer> seats) {

        RowNum = rowNum;
        this.seats = seats;
    }
}
