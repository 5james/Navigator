package pszt.navigator.view;

import pszt.navigator.Graph;
import pszt.navigator.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcin on 2015-12-28.
 */
public class ButtonAction implements ActionListener
{
    private Controller controller;

    public ButtonAction(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton buttonClicked = (JButton) e.getSource();
        String buttonClickedName = buttonClicked.getText();
        switch (buttonClickedName)
        {
            case "Start":
                startButtonAction();
                break;

            case "End":
                endButtonAction();
                break;

            case "Generate!":
                generateButtonAction();
                break;

            case "Solve!":
                solveButtonAction();
                break;

            default:
                System.out.println("Hello");
                break;
        }
    }

    private void startButtonAction()
    {
        controller.setStartEndSwich('S');
    }

    private void endButtonAction()
    {
        controller.setStartEndSwich('E');
    }

    private void generateButtonAction()
    {
        controller.generateNewGraph();
    }

    private void solveButtonAction()
    {
        controller.solveUsingAStar();
    }
}
