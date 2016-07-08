package com.example.example;

/**
 * Created by AFF22 on 06-Jul-16.
 */
public class GS_Images {
    String xname;
    String image;
    String productxname;
    String xcode;

    public String getProductxname() {
        return productxname;
    }

    public void setProductxname(String productxname) {
        this.productxname = productxname;
    }

    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    public GS_Images() {
    }

    public GS_Images(String xname, String image) {
        this.xname = xname;
        this.image = image;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
