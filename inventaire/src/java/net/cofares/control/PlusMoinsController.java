package net.cofares.control;

import net.cofares.PlusMoins;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.PlusMoinsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("plusMoinsController")
@SessionScoped
public class PlusMoinsController implements Serializable {

    @EJB
    private net.cofares.sb.PlusMoinsFacade ejbFacade;
    private List<PlusMoins> items = null;
    private PlusMoins selected;

    public PlusMoinsController() {
    }

    public PlusMoins getSelected() {
        return selected;
    }

    public void setSelected(PlusMoins selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlusMoinsFacade getFacade() {
        return ejbFacade;
    }

    public PlusMoins prepareCreate() {
        selected = new PlusMoins();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePM").getString("PlusMoinsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePM").getString("PlusMoinsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePM").getString("PlusMoinsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PlusMoins> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePM").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePM").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PlusMoins getPlusMoins(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<PlusMoins> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PlusMoins> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PlusMoins.class)
    public static class PlusMoinsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlusMoinsController controller = (PlusMoinsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "plusMoinsController");
            return controller.getPlusMoins(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PlusMoins) {
                PlusMoins o = (PlusMoins) object;
                return getStringKey(o.getIdPM());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlusMoins.class.getName()});
                return null;
            }
        }

    }

}
