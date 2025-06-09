package br.upf.musictrackr.converter;

import br.upf.musictrackr.entity.ArtistaEntity;
import br.upf.musictrackr.facade.ArtistaFacade;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "artistaConverter", managed = true)
public class ArtistaConverter implements Converter<ArtistaEntity> {

    @EJB
    private ArtistaFacade artistaFacade;

    @Override
    public ArtistaEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        return artistaFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ArtistaEntity value) {
        if (value == null || value.getId() == null) return "";
        return String.valueOf(value.getId());
    }
}
