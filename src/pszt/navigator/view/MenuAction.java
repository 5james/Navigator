package pszt.navigator.view;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAction implements ActionListener
{

    private JDialog dialogHelp;
    private boolean dialogHelpVisible = false;
    private JDialog dialogAbout;
    private boolean dialogAboutVisible = false;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem buttonClicked = (JMenuItem) e.getSource();
        String buttonClickedName = new String(buttonClicked.getName());
        switch (buttonClickedName)
        {
            case "Help":
                if(!dialogHelpVisible) {
                    dialogHelpVisible = true;
                    helpButtonAction();
                }
                break;

            case "About":
                if(!dialogAboutVisible)
                {
                    dialogAboutVisible = true;
                    aboutButtonAction();
                }
                break;

            default:
                System.out.println("Hello");
                break;
        }
    }

    private void helpButtonAction()
    {
        String helpInfo = "How to use:\n" +
                "- write in rate and number of nodes\n" +
                "- click 'Generate!' button\n" +
                "- click 'Start' button, then click on one of nodes (to make it starter point)\n" +
                "- click 'End' button, then click on one of nodes (to make it end point)\n" +
                "- click 'Solve!' button\n";

        dialogHelp = new JDialog();
        dialogHelp.setTitle("Help");
        dialogHelp.setLocation(150,150);
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane textInfo = new JTextPane(doc);
        textInfo.setEditable(false);

        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

        try {
            doc.insertString(0,helpInfo,null);
            doc.setParagraphAttributes(0, 1, heading2Style, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogHelp.setVisible(false);
                dialogHelp.dispose();
                dialogHelpVisible = false;
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.add(okButton);

        JScrollPane scrollPane = new JScrollPane(textInfo);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        dialogHelp.add(scrollPane, BorderLayout.CENTER);
        dialogHelp.add(buttonPane, BorderLayout.SOUTH);
        dialogHelp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogHelp.pack();
        dialogHelp.setVisible(true);

    }

    private void aboutButtonAction()
    {
        String aboutInfo = "Basics of Artificial Intelligence - Navigator\n" +
                "authors: Jakub Guzek, Kacper Stachyra, Marcin Jarząbek\n" +
                "tutor: Paweł Wawrzyński, PhD\n" +
                "EiTI 2015";

        dialogAbout = new JDialog();
        dialogAbout.setTitle("About");
        dialogAbout.setLocation(150,150);

        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane textInfo = new JTextPane(doc);
        textInfo.setEditable(false);

        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

        try {
            doc.insertString(0,aboutInfo,null);
            doc.setParagraphAttributes(0, 1, heading2Style, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogAbout.setVisible(false);
                dialogAbout.dispose();
                dialogAboutVisible = false;
            }
        });
        JPanel buttonPane = new JPanel();
        buttonPane.add(okButton);

        dialogAbout.add(textInfo, BorderLayout.CENTER);
        dialogAbout.add(buttonPane, BorderLayout.SOUTH);
        dialogAbout.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogAbout.pack();
        dialogAbout.setVisible(true);
    }

}
