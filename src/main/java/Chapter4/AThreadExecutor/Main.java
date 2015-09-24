package Chapter4.AThreadExecutor;

/**
 * Created by centling on 2015/9/11.
 */
public class Main {

    public static void main(String[] args){
        Server server = new Server();
        for(int i=0;i<100;i++){
            Task task = new Task("Task"+i);
            server.executeTask(task);
        }
        server.endServer();

    }
}
