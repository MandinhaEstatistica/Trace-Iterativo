/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.tools.teste;

import br.uece.lotus.tools.teste.Test;
import br.uece.lotus.Component;
import br.uece.lotus.project.ProjectExplorer;
import static br.uece.lotus.tools.modeloComportamental.modelPlugin.crunchifyCSVtoArrayList;
import br.uece.seed.ext.Plugin;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import br.uece.seed.app.UserInterface;
import br.uece.seed.ext.ExtensionManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JFileChooser;

/**
 *
 * @author Amanda Souza
 */
public class TestPlugin extends Plugin implements Test {

    private UserInterface mUserInterface;
    private ProjectExplorer mProjectExplorer;

    @Override
    public void show(Component c, boolean editable) {
        
        URL location;
        location = getClass().getResource("tese/Test.fxml"); //Adicionar o path do fxml
        FXMLLoader loader = new FXMLLoader();
        
        ResourceBundle bundle = new ResourceBundle(){
            Component mComponent = c;
            
            @Override
            protected Object handleGetObject(String key) {
                return mComponent;
            }

            @Override
            public Enumeration<String> getKeys() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }    
        };
        
        try {
            loader.setClassLoader(getClass().getClassLoader());
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setResources(bundle);
            Parent root = (Parent) loader.load(location.openStream());
            
            int id = mUserInterface.getCenterPanel().newTab(c.getName() + "- [BMS]", root, true);
            mUserInterface.getCenterPanel().showTab(id);
        }catch(IOException e){
            e.printStackTrace();
        }   
    }
    
    private Runnable mBMS = () -> {
       
       BufferedReader crunchifyBuffer = null;{
	
        JFileChooser chooser = new JFileChooser();//A
    
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	
            try {
                String crunchifyLine;
		crunchifyBuffer = new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));
		
		// How to read file in java line by line?
		while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
                    System.out.println("Raw CSV data: " + crunchifyLine);
                    System.out.println("Converted ArrayList data: " + crunchifyCSVtoArrayList(crunchifyLine) + "\n");
		}
            } catch (IOException e) {
		e.printStackTrace();
            } finally {
		try {
                    if (crunchifyBuffer != null) crunchifyBuffer.close();
		} catch (IOException crunchifyException) {
                    crunchifyException.printStackTrace();
                }
            }
        }
    }   
    };
    
    @Override
    public void onStart(ExtensionManager extensionManager) throws Exception{
        
        super.onStart(extensionManager);
        mUserInterface = extensionManager.get(UserInterface.class);
        mProjectExplorer = extensionManager.get(ProjectExplorer.class);
        
        mUserInterface.getMainMenu().newItem("Model/BMS1")//para criar o botão
                .setWeight(Integer.MAX_VALUE)
                .setAction(mBMS) // criar o botão
                .create();        
    }
}
