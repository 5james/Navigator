package pszt.navigator.view;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.*;

/**
 * Created by Marcin on 2015-12-28.
 */
public class LogPanel extends JPanel
{
    public static int logPanelPreferredWidth = 160;
    public static int logPanelPreferredHeight = 240;

    private JTextPane logsPanel;
    private DefaultStyledDocument doc;
    private StyleContext sc;

    public LogPanel()
    {
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Logs"),
                BorderFactory.createEmptyBorder(0,0,0,0)));

        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        logsPanel = new JTextPane(doc);
        logsPanel.setEditable(false);

        this.setLayout(new GridLayout(0,1));

        JScrollPane scrollPanel = new JScrollPane(logsPanel);
        scrollPanel.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPanel.setPreferredSize(new Dimension(840 , 90));
        //scrollPanel.setMinimumSize(new Dimension(10, 10));

        this.add(scrollPanel);
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
