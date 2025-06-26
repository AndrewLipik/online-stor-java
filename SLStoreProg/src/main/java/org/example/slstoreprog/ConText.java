package org.example.slstoreprog;

public class ConText {
    public static ConText getInstance(){
        if(instance==null){
            instance=new ConText();
        }
        return instance;
    }
    private static ConText instance=null;

    private ConText(){
    }
    public HelloController getHelloController(){
        return helloController;
    }
    public void setHelloController(HelloController helloController){
        this.helloController=helloController;
    }
    HelloController helloController;

}
