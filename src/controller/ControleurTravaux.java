package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dao.TravauxDAO;
import model.BienImmobilier;
import view.PanelTravaux;


public class ControleurTravaux implements ActionListener{

    private PanelTravaux view;
    private TravauxDAO travauxDAO;
    private BienImmobilier bien;

    public ControleurTravaux(PanelTravaux view, BienImmobilier bien) {
        this.view = view;
        this.bien = bien;
        this.travauxDAO = new TravauxDAO();
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        
    }
    
}
