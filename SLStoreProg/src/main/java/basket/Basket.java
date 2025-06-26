package basket;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Basket {

private final StringProperty buttonText=new SimpleStringProperty();
private final StringProperty imagePath=new SimpleStringProperty();
private final StringProperty lbDescription=new SimpleStringProperty();
private final StringProperty lbDescriptionPrice=new SimpleStringProperty();
private final StringProperty lbDescriptionAbout=new SimpleStringProperty();
private final StringProperty lbDescriptionColor=new SimpleStringProperty();
private  String[] listImagesPaths;
public String[] getListImagesPaths(){
        return listImagesPaths;
    }
    public void setListImagesPaths(String[] listImagesPaths){
        this.listImagesPaths = listImagesPaths;
    }
public String getImagePath(){
    return imagePath.get();
}
private StringProperty imageProperty(){
    return imagePath;
}
public void setImagePath(String[] imagePath){
    this.imagePath.set(imagePath[0]);
}
public String getLbDescriptionPrice(){return lbDescriptionPrice.get();}
    public StringProperty lbDescriptionPropertyPrice(){return lbDescriptionPrice;}
    public void setLbDescriptionPrice(String lbDescriptionPrice){
        this.lbDescriptionPrice.set(lbDescriptionPrice);
    }

    public String getLbDescription(){
        return lbDescription.get();
    }
    public StringProperty lbDescriptionProperty(){
        return lbDescription;
    }
    public void setLbDescription(String lbDescription){
        this.lbDescription.set(lbDescription);
    }

    public String getLbDescriptionAbout(){
        return lbDescriptionAbout.get();
    }
    public StringProperty lbDescriptionPropertyAbout(){
        return lbDescriptionAbout;
    }
    public void setLbDescriptionAbout(String lbDescriptionAbout){
        this.lbDescriptionAbout.set(lbDescriptionAbout);
    }
    public String getLbDescriptionColor(){
        return lbDescriptionColor.get();
    }
    public StringProperty lbDescriptionPropertyColor(){
        return lbDescriptionColor;
    }
    public void setLbDescriptionColor(String lbDescriptionColor){
        this.lbDescriptionColor.set(lbDescriptionColor);
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
