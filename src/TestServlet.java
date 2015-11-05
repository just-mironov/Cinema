import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {

    static Cinema my;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

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
        my = new Cinema(halls, seances);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        ArrayList<Seance> empty = new ArrayList<>();
        data.put("list", empty);
        TemplateUtil.render("/index.vsl", data, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        String film = req.getParameter("film");
        ArrayList<Seance> found = my.find(film);
        data.put("list", found);
        TemplateUtil.render("/index.vsl", data, resp.getWriter());

    }
}
