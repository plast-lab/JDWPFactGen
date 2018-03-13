import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JDWPClient client = new JDWPClient("localhost", 5005, false);
            client.depthLim = 20;
            Runtime.getRuntime().addShutdownHook(new Thread(() -> client.close()));
//            System.out.println("Dumping all threads... ");
//            client.vm.allThreads().stream()
//                    .filter(thread -> thread.name().contains("alfresco"))
//                    .forEach(thread -> {
//                        thread.suspend();
//                        client.dumpThread(thread);
//                        thread.resume();
//                    });
//            System.out.println("Thread dump complete, listening for events");
            client.breakOnMethod("org.alfresco.repo.web.scripts.comments.CommentsPost", "executeImpl", false);
            client.handleEvents();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalConnectorArgumentsException e) {
            e.printStackTrace();
        }
    }
}
