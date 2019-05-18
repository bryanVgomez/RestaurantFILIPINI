package com.entity.todo;

import com.entity.todo.util.JsfUtil;
import com.entity.todo.util.PaginationHelper;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("registroLoginController")
@SessionScoped
public class RegistroLoginController implements Serializable {

    private RegistroLogin current;
    private DataModel items = null;
    @EJB
    private com.entity.todo.RegistroLoginFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private String nombre;
    private String clave;
    private boolean logeado = false;
    private String msg;

    public RegistroLoginController() {
    }

    
    public String logout(){
        
        logeado = false;
             try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("denegado.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RegistroLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "";
        
    }
    
    public String permitido(){
        
        if(logeado != true){
            try {
               
                FacesContext.getCurrentInstance().getExternalContext().redirect("denegado.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RegistroLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        return "";
    }
    
     public String login() {
         
         List<RegistroLogin> lista = ejbFacade.findAll();
         String password="";
         for (RegistroLogin usuario : lista) {
             String nombre2 = usuario.getUsername();
             if (nombre.equals(nombre2)){
                 password = usuario.getPass();
                 break;
             }
         }
         
          if (nombre != null  && clave != null  && clave.equals(password)) {
            logeado = true;
                msg = "Bienvenid@ " + nombre;
             
                     try {
                //return "denegado.xhtml";
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RegistroLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 return "index.xhtml";
          } else {
            logeado = false;
                 msg = "Login Error: " + "Credenciales no válidas";
                 return "denegado.xhtml";

          }
            
     }    
    
    public RegistroLogin getSelected() {
        if (current == null) {
            current = new RegistroLogin();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RegistroLoginFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (RegistroLogin) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new RegistroLogin();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroLoginCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (RegistroLogin) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroLoginUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (RegistroLogin) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroLoginDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public RegistroLogin getRegistroLogin(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = RegistroLogin.class)
    public static class RegistroLoginControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RegistroLoginController controller = (RegistroLoginController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "registroLoginController");
            return controller.getRegistroLogin(getKey(value));
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
            if (object instanceof RegistroLogin) {
                RegistroLogin o = (RegistroLogin) object;
                return getStringKey(o.getUsername());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RegistroLogin.class.getName());
            }
        }

    }

    public RegistroLogin getCurrent() {
        return current;
    }

    public void setCurrent(RegistroLogin current) {
        this.current = current;
    }

    public RegistroLoginFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RegistroLoginFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
