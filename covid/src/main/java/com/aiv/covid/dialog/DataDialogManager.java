package com.aiv.covid.dialog;

import org.primefaces.PrimeFaces;

public class DataDialogManager {
    private PrimeFaces current;
    private static DataDialogManager instance;

    private DataDialogManager(){ current = PrimeFaces.current(); }

    public static DataDialogManager getInstance(){
        if(instance == null){
            instance = new DataDialogManager();
        }
        return instance;
    }

    public void generateDialog(String name, boolean open){
        String statement = "PF('" + name + "')";

        if(open){
            statement += ".show();";
        }else{
            statement += ".hide();";
        }

        current.executeScript(statement);
    }
}
