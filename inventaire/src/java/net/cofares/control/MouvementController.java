package net.cofares.control;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import net.cofares.Mouvement;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.MouvementFacade;

@Named("mouvementController")
@SessionScoped
public class MouvementController implements Serializable {

    @EJB
    private net.cofares.sb.MouvementFacade ejbFacade;
    private List<Mouvement> items = null;
    private Mouvement selected;

    public MouvementController() {
    }

    public Mouvement getSelected() {
        return selected;
    }

    public void setSelected(Mouvement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MouvementFacade getFacade() {
        return ejbFacade;
    }

    public Mouvement prepareCreate() {
        selected = new Mouvement();
        initializeEmbeddableKey();
        selected.setMouvementcol(new Date());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MouvementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MouvementUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MouvementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    @Inject
    ArticlesController ac;

    public List<Mouvement> getItems() {
        if (ac.getSelected() == null) {
            items = getFacade().findAll();
        } else {
            items = getFacade().findByArticle(ac.getSelected());
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Mouvement getMouvement(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Mouvement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Mouvement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Mouvement.class)
    public static class MouvementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MouvementController controller = (MouvementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mouvementController");
            return controller.getMouvement(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Mouvement) {
                Mouvement o = (Mouvement) object;
                return getStringKey(o.getIdMouvement());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mouvement.class.getName()});
                return null;
            }
        }

    }

}
