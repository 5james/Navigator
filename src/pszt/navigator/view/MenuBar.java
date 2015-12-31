package pszt.navigator.view;

import javax.swing.*;

/**
 * Created by Marcin on 2015-12-28.
 */
public class MenuBar extends JMenuBar
{
    private JMenu navigator;
    private JMenuItem helpMenu;
    private JMenuItem aboutMenu;

    private MenuAction menuAction;

    public MenuBar()
    {

        navigator = new JMenu("Menu");

        helpMenu = new JMenuItem("Help");
        helpMenu.addActionListener(menuAction);
        helpMenu.setName("Help");
        navigator.add(helpMenu);

        aboutMenu = new JMenuItem("About");
        aboutMenu.setName("About");
        aboutMenu.addActionListener(menuAction);
        navigator.add(aboutMenu);

        this.add(navigator);
    }

    // TODO: 2015-12-31 dodanie helpa i info o programie 
}
