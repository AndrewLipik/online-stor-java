package store;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Store {
    private final StringProperty buttonText=new SimpleStringProperty();
    private final StringProperty imagePath=new SimpleStringProperty();
    public boolean isIsVisible(){
        return isVisible.get();
    }

    public BooleanProperty isVisibleProperty(){
        return isVisible;
    }
    public void setIsVisible(boolean isVisible){
        this.isVisible.set(isVisible);
    }
    private final BooleanProperty isVisible=new SimpleBooleanProperty();

    public String getImagePath(){
        return imagePath.get();
    }
    private StringProperty imageProperty(){
        return imagePath;
    }
    public void setImagePath(String imagePath){
        imagePath = "file:///" + imagePath;
        System.out.println("imagePath "+imagePath);
        this.imagePath.set(imagePath);
    }
    public String getButtonText(){
        return buttonText.get();
    }
    public StringProperty buttonTextProperty(){
        return buttonText;
    }
    public void  setButtonText(String buttonText){
        this.buttonText.set(buttonText);
    }
}
