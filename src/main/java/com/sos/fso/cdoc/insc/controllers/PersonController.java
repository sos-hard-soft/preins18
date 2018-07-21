/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Filiere;
import com.sos.fso.cdoc.insc.entities.Person;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import com.sos.fso.cdoc.insc.services.FiliereFacade;
import com.sos.fso.cdoc.insc.services.PersonFacade;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author master05
 */
@Named(value = "personController")
@SessionScoped
public class PersonController implements Serializable{
   // ======================================
    // = Attributes =
    // ======================================
    private static final Logger logger = Logger.getLogger(EtudiantController.class.getName());
    
    @Inject
    private PersonFacade personService;
    private Person newPerson;
    private Person current;
    private List<Person> personnes;
    
    @Inject
    private FiliereFacade filiereFacade;
    private Filiere filiereProf;
    
    @Inject
    private EtudiantFacade etudiantService;
    private Etudiant student;
    private List<Object[]> maliste;
    
    private boolean visibled = false;
    private boolean visible = false;
    private boolean fileExist = false;
    private String fileName;
    private Path folder;
    private File uploaded;
    
    /**
     * Creates a new instance of MasterController
     * @return 
     */
    public String showEdit() {
        //this.current = item;
        return "/index?faces-redirect=true";
    }

    public String showDetails(Person selected) {
        this.current = selected;
        return "/filiere/list?faces-redirect=true";
    }

    public String showCreatePerson() {
        newPerson = new Person();
        return "/filiere/newPerson?faces-redirect=true";
    }
    
    public List<Person> getAllPersons() {
        return personService.findAll();
    }
    
    
    public String doCreatePerson(){
        //Creation du répertoire spécifique a l'étudiant
        String personFolder = newPerson.getCin();
        folder = Paths.get("C:\\inscmast\\persons\\" + personFolder + "");
        try {
            Files.createDirectories(folder);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Procedure de creation " + newPerson.getCin());
        personService.create(newPerson);
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "/index?faces-redirect=true";
    }
    
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        System.out.println("Start file upload procedure");
        UploadedFile file = event.getFile();
        System.out.println(file.getFileName());

        byte[] foto = IOUtils.toByteArray(file.getInputstream());
        System.out.println(foto);

        newPerson.setPhoto(foto);
        //application code
    }

    public void handleUploadToDisk(FileUploadEvent event) throws IOException {
        System.out.println("Start scan upload procedure");
        UploadedFile uploadedFile = event.getFile();
        InputStream input = uploadedFile.getInputstream();
        System.out.println(uploadedFile.getFileName());

        folder = Paths.get("C:\\inscmast\\persons\\" + newPerson.getCin() + "");
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);
        Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        //byte[] scan = IOUtils.toByteArray(file.getInputstream());
        //System.out.println(scan);
        //current.setPhoto(foto);
        //application code
        System.out.println("Uploaded file successfully saved in " + file);
        
        this.fileName = file.toString();
    }

    public DefaultStreamedContent byteToImage(byte[] imgBytes) throws IOException {
        ByteArrayInputStream img = new ByteArrayInputStream(imgBytes);
        return new DefaultStreamedContent(img, "image/jpg");
    }
    
    
    
    public PersonController() {
               
    }

    public List<Object[]> getMaliste() {
        String intitule = filiereFacade.findByResponsable(current).getIntitule();
        System.out.println("L'intitulé reporté est : " + intitule);
        System.out.println("recuperation de la liste des etudiants par la requete natif sql*************");
        System.out.println("**************************************************************************");
        maliste = etudiantService.getPreinscrit(intitule);
        System.out.println("List recuperer avec suces !!!!!");
        System.out.println("first element " + maliste.get(0));
        return maliste;
    }

    public void setMaliste(List<Object[]> maliste) {
        this.maliste = maliste;
    }
    
    
    
    
    public boolean isVisibled() {
        return visibled;
    }

    public void setVisibled(boolean visibled) {
        this.visibled = visibled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Person> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Person> personnes) {
        this.personnes = personnes;
    }

    public Filiere getFiliereProf() {
        filiereProf = filiereFacade.findByResponsable(current);
        return filiereProf;
    }

    public void setFiliereProf(Filiere filiereProf) {
        this.filiereProf = filiereProf;
    }

    public boolean isFileExist() {
        return fileExist;
    }

    public void setFileExist(boolean fileExist) {
        this.fileExist = fileExist;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getFolder() {
        return folder;
    }

    public void setFolder(Path folder) {
        this.folder = folder;
    }

    public File getUploaded() {
        return uploaded;
    }

    public void setUploaded(File uploaded) {
        this.uploaded = uploaded;
    }
    
    
    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

    public Person getCurrent() {
        return current;
    }

    public void setCurrent(Person current) {
        this.current = current;
    }

    public Person getPerson(java.lang.Integer id) {
        return personService.find(id);
    }
    
    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personController");
            return controller.getPerson(getKey(value));
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
            if (object instanceof Person) {
                Person o = (Person) object;
                return getStringKey(o.getIdPerson());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Person.class.getName());
            }
        }
    
} 
}
