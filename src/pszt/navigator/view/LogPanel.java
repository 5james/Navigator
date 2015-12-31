package pszt.navigator.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin on 2015-12-28.
 */
public class LogPanel extends JPanel
{
    public static int logPanelPreferredWidth = 160;
    public static int logPanelPreferredHeight = 240;

    public LogPanel()
    {
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Logs"),
                BorderFactory.createEmptyBorder(0,0,0,0)));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(logPanelPreferredWidth, logPanelPreferredHeight);
    }

    public void displayString(String s)
    {
        // TODO: 2015-12-31
    }
}
