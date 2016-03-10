/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.tools.teste;

import br.uece.lotus.Component;
import br.uece.lotus.viewer.ComponentViewImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Amanda Souza
 */
public class TestFXMLController implements Initializable {

    private ComponentViewImpl mViewer;
    private ScrollPane mScrollPane;
    private TabPane mTabPane;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL location, ResourceBundle resources){

        mViewer = new ComponentViewImpl();
        mScrollPane.setContent(mViewer);
        
        Component component = (Component) resources.getObject("component");
        mViewer.setComponent(component);
        
        mTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
        
        
   } 
    
}
