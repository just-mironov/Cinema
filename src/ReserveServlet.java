import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReserveServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        String id = req.getParameter("id");
        Cinema my = (Cinema) req.getServletContext().getAttribute("cinema");
        Seance seanceById = my.findSeanceById(Integer.parseInt(id));
        int hallNumber = seanceById.getNumber();
        Hall hall = my.getHall(hallNumber);

        data.put("id", id);
        ArrayList<Row> rows = new ArrayList<>();
        int[][] table = hall.getTable();
        boolean[][] reserved = seanceById.getReserved();
        for (int i = 0; i < table.length; i++) {
            int[] row = table[i];
            ArrayList<Integer> seats = new ArrayList<>();
            for (int j = 0; j < row.length; j++) {
                boolean resij = reserved[i][j];
                if (!resij) {
                    seats.add(0);
                } else {
                    int seatNumber = row[j];
                    seats.add(seatNumber);
                }
            }
            rows.add(new Row(i + 1, seats));
        }
        data.put("rows", rows);
     // data.put("message", "Привет!");
        TemplateUtil.render("/ReserveServlet.vsl", data, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        resp.setCharacterEncoding("UTF-8");
        String rowSeat = req.getParameter("seat");
        int p = rowSeat.indexOf(",");
        int row = Integer.parseInt(rowSeat.substring(0, p));
        int seat = Integer.parseInt(rowSeat.substring(p+1));
        String id = req.getParameter("id");
        Cinema my = (Cinema) req.getServletContext().getAttribute("cinema");
        Seance seanceById = my.findSeanceById(Integer.parseInt(id));
        String message;
        if (my.addReserve(seanceById, row, seat)) {
            message = "OK";
        } else {
            message = "Fail";
        }
        data.put("message", message);
        TemplateUtil.render("/result.vsl", data, resp.getWriter());
    }
}
