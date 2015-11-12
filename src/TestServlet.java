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
        Cinema my = (Cinema) req.getServletContext().getAttribute("cinema");
        ArrayList<Seance> found = my.find(film);
        data.put("list", found);
        TemplateUtil.render("/index.vsl", data, resp.getWriter());

    }
}
