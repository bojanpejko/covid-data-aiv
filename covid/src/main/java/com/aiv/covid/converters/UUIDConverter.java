package com.aiv.covid.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.UUID;

public class UUIDConverter implements Converter<UUID> {

    @Override
    public UUID getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null){
            return null;
        }
        return UUID.fromString(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, UUID uuid) {
        if(uuid == null){
            return "";
        }
        return uuid.toString();
    }
}
