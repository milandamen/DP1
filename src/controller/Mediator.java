package controller;

public class Mediator {
    private static Mediator instance = null;
    private Logger logger;
    
    protected Mediator() {
        logger = new Logger();
    }
    
    public static Mediator getInstance() {
        if(instance == null) {
            instance = new Mediator();
        }
        return instance;
    }
    
    public void log(String data){
        System.out.println(data);
    }
}
