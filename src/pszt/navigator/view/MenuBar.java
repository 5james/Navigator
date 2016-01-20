package pszt.navigator.view;

import javax.swing.*;

/**
 * Created by Marcin on 2015-12-28.
 */
public class MenuBar extends JMenuBar
{
    private JMenu menu;
    private JMenuItem helpMenu;
    private JMenuItem aboutMenu;

    private MenuAction menuAction;

    public MenuBar()
    {

        menu = new JMenu("Menu");
        menuAction = new MenuAction();

        helpMenu = new JMenuItem("Help");
        helpMenu.addActionListener(menuAction);
        helpMenu.setName("Help");
        menu.add(helpMenu);

        aboutMenu = new JMenuItem("About");
        aboutMenu.setName("About");
        aboutMenu.addActionListener(menuAction);
        menu.add(aboutMenu);

        this.add(menu);
    }

    // TODO: 2015-12-31 dodanie helpa i info o programie 
}
