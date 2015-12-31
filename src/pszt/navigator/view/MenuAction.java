package pszt.navigator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcin on 2015-12-28.
 */
public class MenuAction implements ActionListener
{
    // TODO: 2015-12-31 klasa do przebudowy, by obsługiwała zdarzenia z paska menu
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem buttonClicked = (JMenuItem) e.getSource();
        String buttonClickedName = new String(buttonClicked.getName());
        switch (buttonClickedName)
        {
            case "Help":
                helpButtonAction();
                break;

            case "About":
                aboutButtonAction();
                break;

            default:
                System.out.println("Hello");
                break;
        }
    }

    private void helpButtonAction()
    {
        System.out.println("Help");
    }

    private void aboutButtonAction()
    {
        System.out.println("About");
    }

}
