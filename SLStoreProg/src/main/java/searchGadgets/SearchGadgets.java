package searchGadgets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class SearchGadgets {
    private final StringProperty buttonText=new SimpleStringProperty();
    private final StringProperty imagePath=new SimpleStringProperty();
    private final StringProperty lbDescription=new SimpleStringProperty();
    private final StringProperty lbDescriptionPrice=new SimpleStringProperty();
    private final StringProperty lbDescriptionAbout=new SimpleStringProperty();
    private final StringProperty lbDescriptionColor=new SimpleStringProperty();
    private final StringProperty lbDescriptionSellersNumber = new SimpleStringProperty();
    private final StringProperty lbDescriptionCount = new SimpleStringProperty();

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

  /*  public void setImagePath(String imagePath){
        this.imagePath.set(imagePath);
    }
   */


  public void setImagePath(String[] imagePath){
      this.imagePath.set(imagePath[0]);
  }



    public String getLbDescriptionColor(){return lbDescriptionColor.get();}
    public StringProperty lbDescriptionColor(){
        return lbDescriptionColor;
    }
    public void setLbDescriptionColor(String lbDescriptionColor){this.lbDescriptionColor.set(lbDescriptionColor);}
    public String getLbDescriptionAbout(){
        return lbDescriptionAbout.get();
    }
    public StringProperty lbDescriptionPropertyAbout(){
        return lbDescriptionAbout;
    }
    public void setLbDescriptionAbout(String lbDescriptionAbout){this.lbDescriptionAbout.set(lbDescriptionAbout);}
    public String getLbDescription(){
        return lbDescription.get();
    }
    public StringProperty lbDescriptionProperty(){
        return lbDescription;
    }
    public void setLbDescription(String lbDescription){
        this.lbDescription.set(lbDescription);
    }
    public String getLbDescriptionPrice(){
        return lbDescriptionPrice.get();
    }
    public StringProperty lbDescriptionPropertyPrice(){
        return lbDescriptionPrice;
    }
    public void setLbDescriptionPrice(String lbDescriptionPrice){
        this.lbDescriptionPrice.set(lbDescriptionPrice);
    }
    public String getButtonText(){
        return buttonText.get();
    }
    public StringProperty buttonTextProperty(){
        return buttonText;
    }
    public void setButtonText(String buttonText){
        this.buttonText.set(buttonText);
    }
    public  String getLbDescriptionSellersNumber(){
        return lbDescriptionSellersNumber.get();
    }
    public StringProperty lbDescriptionSellersNumber(String sellersNumber2){
        return lbDescriptionSellersNumber;
    }
    public void setLbDescriptionSellersNumber(String lbDescriptionSellersNumber){
        this.lbDescriptionSellersNumber.set(lbDescriptionSellersNumber);
    }
    public  String getLbDescriptionCount(){
        return lbDescriptionCount.get();
    }
    public StringProperty lbDescriptionCount(String countDb2){
        return lbDescriptionCount;
    }
    public void setLbDescriptionCount(String lbDescriptionCount){
        this.lbDescriptionCount.set(lbDescriptionCount);
    }

}
