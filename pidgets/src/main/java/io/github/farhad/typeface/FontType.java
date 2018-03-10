package io.github.farhad.typeface;

/**
 * Created by farhad on 12/18/16.
 */

public enum FontType {

    REGULAR     (0) ,
    SEMI_BOLD   (1) ,
    BOLD        (2) ,
    ITALIC      (3) ;

    int value ;

    FontType(int value) {
        this.value = value ;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static FontType getType(int type) {

        for(FontType item : values()){
            if(item.value == type)
                return item ;
        }

        return REGULAR ;
    }

}
