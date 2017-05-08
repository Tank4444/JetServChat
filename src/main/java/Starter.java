import Servlets.AdminServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static int port=8080;
    public static void main(String[] args) {
        Server server =new Server(port);
        ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
        context.setContextPath( "/" );
        context.addServlet(new ServletHolder( new AdminServlet( ) ),"/admin");
        //HandlerList
        HandlerList handlers = new HandlerList( );
        handlers.setHandlers( new Handler[] { context } );
        //ResourceHandler
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("src/main/resources/web/view/");
        handlers.addHandler(resource_handler);

        server.setHandler( handlers );
        try {
            server.start();
            System.out.println("Listening port : " + port );
            server.join();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
}
