
package br.upf.musictrackr.converter;

import br.upf.musictrackr.entity.MusicaEntity;
import br.upf.musictrackr.facade.MusicaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named("musicaConverter")
@RequestScoped
@FacesConverter(value = "musicaConverter", managed = true)
public class MusicaConverter implements Converter<MusicaEntity> {

    @EJB
    private MusicaFacade musicaFacade;

    @Override
    public MusicaEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return musicaFacade.find(Integer.valueOf(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, MusicaEntity musica) {
        if (musica != null && musica.getId() != null) {
            return String.valueOf(musica.getId());
        }
        return "";
    }
}
