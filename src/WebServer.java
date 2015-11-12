import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
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
        ctx.setAttribute("cinema", my);
        ctx.addServlet(TestServlet.class, "/");
        ctx.addServlet(ReserveServlet.class, "/res");
        ctx.addServlet(DefaultServlet.class, "/static/*");
        ctx.setResourceBase(".");
        server.start();
    }
}