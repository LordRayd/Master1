
//-----------------------------------------------------------------------
//      Les bibliotheques       /
//-----------------------------------------------------------------------

//***************************************************
/*                                                  *
 *             Programme Réalisé Par 
 *               
 *		          Ben Rached Marwen                 *                                               *
 *                                                  *
 *                                                  *
 ****************************************************/
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.awt.print.*;
import java.awt.Dimension.*;
import java.awt.GridLayout.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JScrollPane.*;
import javax.swing.JTable.*;
import javax.swing.JComponent.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import java.lang.Object.*;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

class perceptron extends JFrame implements ActionListener {

    // ******************************************************************************
    public double alpha = 0.5;

    /// *****************************************************************************

    double p1;
    double p2;
    double p3;
    double p4;
    double p5;
    double p6;
    double p7;
    double p8;
    double p9;

    int fncta = 1;
    Font ftt = new Font("Times New Roman", Font.BOLD, 18);

    Container cont;// donner le nom cont au container

    JTextField ea1 = new JTextField("1");
    JTextField ea2 = new JTextField("0");
    JTextField ea3 = new JTextField("1");
    JTextField ea4 = new JTextField("0");

    JTextField eb1 = new JTextField("1");
    JTextField eb2 = new JTextField("1");
    JTextField eb3 = new JTextField("0");
    JTextField eb4 = new JTextField("0");

    JTextField s1 = new JTextField("");
    JTextField s2 = new JTextField("");
    JTextField s3 = new JTextField("");
    JTextField s4 = new JTextField("");

    JTextField ea12 = new JTextField("1");
    JTextField ea22 = new JTextField("0");
    JTextField ea32 = new JTextField("1");
    JTextField ea42 = new JTextField("0");

    JTextField eb12 = new JTextField("1");
    JTextField eb22 = new JTextField("1");
    JTextField eb32 = new JTextField("0");
    JTextField eb42 = new JTextField("0");

    JTextField s12 = new JTextField("");
    JTextField s22 = new JTextField("");
    JTextField s32 = new JTextField("");
    JTextField s42 = new JTextField("");

    JTextField ss12 = new JTextField("");
    JTextField ss22 = new JTextField("");
    JTextField ss32 = new JTextField("");
    JTextField ss42 = new JTextField("");

    JLabel ega1 = new JLabel("=");
    JLabel ega2 = new JLabel("=");
    JLabel ega3 = new JLabel("=");
    JLabel ega4 = new JLabel("=");

    JLabel lb1 = new JLabel("Entrer 1");
    JLabel lb2 = new JLabel("Entrer 2");
    JLabel lb3 = new JLabel("Sortie");

    JLabel lb11 = new JLabel("Entrer 1");
    JLabel lb22 = new JLabel("Entrer 2");
    JLabel lb33 = new JLabel("X");
    JLabel lb44 = new JLabel("Résultat");

    JButton and = new JButton("AND");
    JButton or = new JButton("OR");
    JButton xor = new JButton("XOR");
    JButton zor = new JButton("ZOR");

    JButton init = new JButton("Initialiser Les Poids");
    JButton entr = new JButton("Entrainer Le RNA");
    JLabel typ = new JLabel("");
    JLabel typx = new JLabel("- Fonction d'activation   :Logistique");
    JButton test = new JButton("Tester le RNA");

    JButton sepxarr = new JButton("");
    JButton sepxarrx = new JButton("");
    JButton sepxar = new JButton("");
    JButton sepx = new JButton("");

    JLabel tmp = new JLabel("- Temps d'aprentissage :");

    // *****************************************************************************
    // *****************************************************************************
    ImageIcon img = new ImageIcon("img.jpg");
    JLabel ima = new JLabel(img);
    // -------------------- Choix de la Fonction d'activation-----------------------
    ImageIcon imga = new ImageIcon("a.jpg");
    ImageIcon imgb = new ImageIcon("b.jpg");
    ImageIcon imgc = new ImageIcon("c.jpg");
    ImageIcon imgd = new ImageIcon("RNA.jpg");

    JButton bta = new JButton(imga);
    JButton btb = new JButton(imgb);
    JButton rna = new JButton(imgd);

    // ******************************************************************************
    // ******************************************************************************
    // ******************************************************************************
    // ---------------------------- Intialisation des Valeurs Des Poids-------------
    JTextField po1 = new JTextField("");
    JTextField po2 = new JTextField("");
    JTextField po3 = new JTextField("");
    JTextField po4 = new JTextField("");

    JTextField po5 = new JTextField("");
    JTextField po6 = new JTextField("");
    JTextField po7 = new JTextField("");
    JTextField po8 = new JTextField("");
    JTextField po9 = new JTextField("");

    // *****************************************************************************

    JLabel llb1 = new JLabel("-  Option de Réglages :");
    JLabel llb11 = new JLabel("=========================");
    JLabel llb2 = new JLabel("ALPHA :");

    JLabel llb1x = new JLabel("- Fonction d'activation : :");
    JLabel llb11x = new JLabel("=========================");

    JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
    JProgressBar pbm = new JProgressBar(0, 100);

    // *****************************************************************************
    Calendar m_start = new GregorianCalendar();
    Calendar m_stop = new GregorianCalendar();

    // ******************************************************************************
    // ******************************************************************************

    public perceptron()

    {

        // -------------------------------------------------------- /
        // / ------------Fenetre--------------- //
        // -------------------------------------------------------- /

        this.setTitle("....::RNA avec Rétropropagation::.......  ");// nom de la fenetre
        this.setSize(this.getToolkit().getScreenSize());
        getContentPane().setBackground(SystemColor.white);

        cont = this.getContentPane();// appeGRIDl de la methode getContentPane()
        cont.setLayout(null);

        // ****************************************************************************
        // ****************************************************************************
        // ------------------------- Séparateur---------------------------------------
        cont.add(sepxar);
        sepxar.setBackground(Color.white);
        sepxar.setForeground(Color.black);
        sepxar.setBounds(320, 0, 3, 220);

        cont.add(sepxarr);
        sepxarr.setBackground(Color.white);
        sepxarr.setForeground(Color.black);
        sepxarr.setBounds(0, 220, 700, 3);

        cont.add(sepxarrx);
        sepxarrx.setBackground(Color.white);
        sepxarrx.setForeground(Color.black);
        sepxarrx.setBounds(0, 270, 700, 3);

        cont.add(sepxar);
        sepxar.setBackground(Color.white);
        sepxar.setForeground(Color.black);
        sepxar.setBounds(700, 0, 3, 860);

        cont.add(sepx);
        sepx.setBackground(Color.white);
        sepx.setForeground(Color.black);
        sepx.setBounds(0, 570, 700, 3);

        // ****************************************************************************
        // ****************************************************************************
        // -----------------------1 Zone ---------------------------------------------
        cont.add(ea1);
        ea1.setBackground(Color.white);
        ea1.setForeground(Color.BLACK);
        ea1.addActionListener(this);
        ea1.setBounds(30, 100, 40, 20);
        ea1.setEditable(false);

        cont.add(ea2);
        ea2.setBackground(Color.white);
        ea2.setForeground(Color.BLACK);
        ea2.addActionListener(this);
        ea2.setBounds(30, 130, 40, 20);
        ea2.setEditable(false);

        cont.add(ea3);
        ea3.setBackground(Color.white);
        ea3.setForeground(Color.BLACK);
        ea3.addActionListener(this);
        ea3.setBounds(30, 160, 40, 20);
        ea3.setEditable(false);

        cont.add(ea4);
        ea4.setBackground(Color.white);
        ea4.setForeground(Color.BLACK);
        ea4.addActionListener(this);
        ea4.setBounds(30, 190, 40, 20);
        ea4.setEditable(false);

        cont.add(eb1);
        eb1.setBackground(Color.white);
        eb1.setForeground(Color.BLACK);
        eb1.addActionListener(this);
        eb1.setBounds(90, 100, 40, 20);
        eb1.setEditable(false);

        cont.add(eb2);
        eb2.setBackground(Color.white);
        eb2.setForeground(Color.BLACK);
        eb2.addActionListener(this);
        eb2.setBounds(90, 130, 40, 20);
        eb2.setEditable(false);

        cont.add(eb3);
        eb3.setBackground(Color.white);
        eb3.setForeground(Color.BLACK);
        eb3.addActionListener(this);
        eb3.setBounds(90, 160, 40, 20);
        eb3.setEditable(false);

        cont.add(eb4);
        eb4.setBackground(Color.white);
        eb4.setForeground(Color.BLACK);
        eb4.addActionListener(this);
        eb4.setBounds(90, 190, 40, 20);
        eb4.setEditable(false);

        cont.add(s1);
        s1.setBackground(Color.white);
        s1.setForeground(Color.BLACK);
        s1.addActionListener(this);
        s1.setBounds(140, 100, 60, 20);
        s1.setEditable(false);

        cont.add(s2);
        s2.setBackground(Color.white);
        s2.setForeground(Color.BLACK);
        s2.addActionListener(this);
        s2.setBounds(140, 130, 60, 20);
        s2.setEditable(false);

        cont.add(s3);
        s3.setBackground(Color.white);
        s3.setForeground(Color.BLACK);
        s3.addActionListener(this);
        s3.setBounds(140, 160, 60, 20);
        s3.setEditable(false);

        cont.add(s4);
        s4.setBackground(Color.white);
        s4.setForeground(Color.BLACK);
        s4.addActionListener(this);
        s4.setBounds(140, 190, 60, 20);
        s4.setEditable(false);

        // ******************************************************************************

        // ****************************************************************************
        // ****************************************************************************
        // ----------------------- Zone 2 ---------------------------------------------
        cont.add(ea12);
        ea12.setBackground(Color.white);
        ea12.setForeground(Color.BLACK);
        ea12.addActionListener(this);
        ea12.setBounds(350, 100, 40, 20);

        cont.add(ea22);
        ea22.setBackground(Color.white);
        ea22.setForeground(Color.BLACK);
        ea22.addActionListener(this);
        ea22.setBounds(350, 130, 40, 20);

        cont.add(ea32);
        ea32.setBackground(Color.white);
        ea32.setForeground(Color.BLACK);
        ea32.addActionListener(this);
        ea32.setBounds(350, 160, 40, 20);

        cont.add(ea42);
        ea42.setBackground(Color.white);
        ea42.setForeground(Color.BLACK);
        ea42.addActionListener(this);
        ea42.setBounds(350, 190, 40, 20);

        cont.add(eb12);
        eb12.setBackground(Color.white);
        eb12.setForeground(Color.BLACK);
        eb12.addActionListener(this);
        eb12.setBounds(410, 100, 40, 20);

        cont.add(eb22);
        eb22.setBackground(Color.white);
        eb22.setForeground(Color.BLACK);
        eb22.addActionListener(this);
        eb22.setBounds(410, 130, 40, 20);

        cont.add(eb32);
        eb32.setBackground(Color.white);
        eb32.setForeground(Color.BLACK);
        eb32.addActionListener(this);
        eb32.setBounds(410, 160, 40, 20);

        cont.add(eb42);
        eb42.setBackground(Color.white);
        eb42.setForeground(Color.BLACK);
        eb42.addActionListener(this);
        eb42.setBounds(410, 190, 40, 20);

        cont.add(s12);
        s12.setBackground(Color.white);
        s12.setForeground(Color.BLACK);
        s12.addActionListener(this);
        s12.setBounds(460, 100, 60, 20);

        cont.add(s22);
        s22.setBackground(Color.white);
        s22.setForeground(Color.BLACK);
        s22.addActionListener(this);
        s22.setBounds(460, 130, 60, 20);

        cont.add(s32);
        s32.setBackground(Color.white);
        s32.setForeground(Color.BLACK);
        s32.addActionListener(this);
        s32.setBounds(460, 160, 60, 20);

        cont.add(s42);
        s42.setBackground(Color.white);
        s42.setForeground(Color.BLACK);
        s42.addActionListener(this);
        s42.setBounds(460, 190, 60, 20);

        cont.add(ss12);
        ss12.setBackground(Color.white);
        ss12.setForeground(Color.BLACK);
        ss12.addActionListener(this);
        ss12.setBounds(560, 100, 60, 20);

        cont.add(ss22);
        ss22.setBackground(Color.white);
        ss22.setForeground(Color.BLACK);
        ss22.addActionListener(this);
        ss22.setBounds(560, 130, 60, 20);

        cont.add(ss32);
        ss32.setBackground(Color.white);
        ss32.setForeground(Color.BLACK);
        ss32.addActionListener(this);
        ss32.setBounds(560, 160, 60, 20);

        cont.add(ss42);
        ss42.setBackground(Color.white);
        ss42.setForeground(Color.BLACK);
        ss42.addActionListener(this);
        ss42.setBounds(560, 190, 60, 20);

        // ******************************************************************************
        // ******************************************************************************

        // ******************************************************************************
        // ******************************************************************************
        // ------------------------ Bouton des fonctions bianire
        cont.add(and);
        and.setBackground(Color.white);
        and.setForeground(Color.BLACK);
        and.setBounds(230, 100, 60, 20);
        and.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                s1.setText("1");
                s2.setText("0");
                s3.setText("0");
                s4.setText("0");
                typ.setText("- Fonction Logique :   AND");
                poids_aleatoire();
                affiche_poids();
            }

        });

        cont.add(or);
        or.setBackground(Color.white);
        or.setForeground(Color.BLACK);
        or.setBounds(230, 130, 60, 20);
        or.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                s1.setText("1");
                s2.setText("1");
                s3.setText("1");
                s4.setText("0");
                typ.setText("- Fonction Logique : OR");

                poids_aleatoire();
                affiche_poids();
            }

        });

        cont.add(xor);
        xor.setBackground(Color.white);
        xor.setForeground(Color.BLACK);
        xor.addActionListener(this);
        xor.setBounds(230, 160, 60, 20);
        xor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                s1.setText("0");
                s2.setText("1");
                s3.setText("1");
                s4.setText("0");
                typ.setText("- Fonction Logique : XOR");
                poids_aleatoire();
                affiche_poids();
            }

        });

        cont.add(zor);
        zor.setBackground(Color.white);
        zor.setForeground(Color.BLACK);
        zor.setBounds(230, 190, 60, 20);
        zor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                s1.setText("1");
                s2.setText("0");
                s3.setText("0");
                s4.setText("1");
                typ.setText("- Fonction Logique : ZOR");
                poids_aleatoire();
                affiche_poids();
            }

        });

        // ******************************************************************************
        // *****************************************************************************
        // ------------------------------ Label d'affichage-----------------------------
        cont.add(lb1);
        lb1.setBackground(Color.white);
        lb1.setForeground(Color.BLACK);
        lb1.setBounds(20, 70, 50, 20);

        cont.add(lb2);
        lb2.setBackground(Color.white);
        lb2.setForeground(Color.BLACK);
        lb2.setBounds(80, 70, 50, 20);

        cont.add(lb3);
        lb3.setBackground(Color.white);
        lb3.setForeground(Color.BLACK);
        lb3.setBounds(140, 70, 50, 20);

        cont.add(lb11);
        lb11.setBackground(Color.white);
        lb11.setForeground(Color.BLACK);
        lb11.setBounds(340, 70, 50, 20);

        cont.add(lb22);
        lb22.setBackground(Color.white);
        lb22.setForeground(Color.BLACK);
        lb22.setBounds(400, 70, 50, 20);

        cont.add(lb33);
        lb33.setBackground(Color.white);
        lb33.setForeground(Color.BLACK);
        lb33.setBounds(475, 70, 50, 20);

        cont.add(lb44);
        lb44.setBackground(Color.white);
        lb44.setForeground(Color.BLACK);
        lb44.setBounds(560, 70, 50, 20);

        cont.add(ega1);
        ega1.setBackground(Color.white);
        ega1.setForeground(Color.BLACK);
        ega1.setBounds(540, 100, 20, 20);

        cont.add(ega2);
        ega2.setBackground(Color.white);
        ega2.setForeground(Color.BLACK);
        ega2.setBounds(540, 130, 20, 20);

        cont.add(ega3);
        ega3.setBackground(Color.white);
        ega3.setForeground(Color.BLACK);
        ega3.setBounds(540, 160, 20, 20);

        cont.add(ega4);
        ega4.setBackground(Color.white);
        ega4.setForeground(Color.BLACK);
        ega4.setBounds(540, 190, 20, 20);

        ega1.setFont(ftt);
        ega2.setFont(ftt);
        ega3.setFont(ftt);
        ega4.setFont(ftt);

        // *****************************************************************************
        // *****************************************************************************
        // -------------------------Partie Commande-------------------------------------

        // Action Boutton d'intilisation des Poids

        cont.add(init);
        init.setBackground(Color.white);
        init.setForeground(Color.BLACK);
        init.setBounds(50, 230, 160, 30);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                poids_aleatoire();
                affiche_poids();
            }

        });

        // Action Bouton d'entrainer le réseaux

        cont.add(entr);
        entr.setBackground(Color.white);
        entr.setForeground(Color.BLACK);
        entr.setBounds(220, 230, 160, 30);
        entr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                System.gc();

                // Nombre d'itération Pour l'apprentissage

                String reponse = JOptionPane.showInputDialog(null,
                        "Entrer Le Nombre d'itération Pour entrainer votre Réseau ", 1000);

                int m = Integer.parseInt(reponse);
                // Phase d'apprentissage

                if (fncta == 1) {
                    btb.setVisible(false);
                }

                if (fncta == 2) {
                    bta.setVisible(false);
                }

                start();// Pour declencher le Compteur d'aprentissage

                double a1 = Double.parseDouble(ea1.getText());
                double a2 = Double.parseDouble(ea2.getText());
                double a3 = Double.parseDouble(ea3.getText());
                double a4 = Double.parseDouble(ea4.getText());

                double b1 = Double.parseDouble(eb1.getText());
                double b2 = Double.parseDouble(eb2.getText());
                double b3 = Double.parseDouble(eb3.getText());
                double b4 = Double.parseDouble(eb4.getText());

                double c1 = Double.parseDouble(s1.getText());
                double c2 = Double.parseDouble(s2.getText());
                double c3 = Double.parseDouble(s3.getText());
                double c4 = Double.parseDouble(s4.getText());

                for (int i = 1; i <= m; i++) {

                    aprentissage(a1, b1, c1);
                    aprentissage(a2, b2, c2);
                    aprentissage(a3, b3, c3);
                    aprentissage(a4, b4, c4);

                }

                System.gc();

                JOptionPane.showMessageDialog(null, "L'aprentissage est Terminé avec Succés", "Information",
                        JOptionPane.INFORMATION_MESSAGE);

                stop();
                getMilliSec();
                printMilliSec();

                if (fncta == 1) {
                    btb.setVisible(true);
                }

                if (fncta == 2) {
                    bta.setVisible(true);
                }

            }

        });

        cont.add(rna);
        rna.setBackground(Color.white);
        rna.setForeground(Color.BLACK);
        rna.setBounds(100, 00, 520, 70);

        cont.add(typ);
        typ.setBackground(Color.white);
        typ.setForeground(Color.BLACK);
        typ.setBounds(10, 580, 200, 30);

        cont.add(typx);
        typx.setBackground(Color.white);
        typx.setForeground(Color.BLACK);
        typx.setBounds(10, 620, 300, 40);

        cont.add(tmp);
        tmp.setBackground(Color.white);
        tmp.setForeground(Color.BLACK);
        tmp.setBounds(300, 580, 300, 30);

        // Action de Boutton de test de Réseaux
        cont.add(test);
        test.setBackground(Color.white);
        test.setForeground(Color.BLACK);
        test.setBounds(460, 230, 160, 30);
        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.gc();
                DecimalFormat df = new DecimalFormat("0.0000"); // Laisser 4 chiffres après la virgule

                double a1 = Double.parseDouble(ea1.getText());
                double a2 = Double.parseDouble(ea2.getText());
                double a3 = Double.parseDouble(ea3.getText());
                double a4 = Double.parseDouble(ea4.getText());

                double b1 = Double.parseDouble(eb1.getText());
                double b2 = Double.parseDouble(eb2.getText());
                double b3 = Double.parseDouble(eb3.getText());
                double b4 = Double.parseDouble(eb4.getText());

                double v1 = test_rx(a1, b1);
                double v2 = test_rx(a2, b2);
                double v3 = test_rx(a3, b3);
                double v4 = test_rx(a4, b4);

                s12.setText("" + df.format(v1));
                s22.setText("" + df.format(v2));
                s32.setText("" + df.format(v3));
                s42.setText("" + df.format(v4));

                if (v1 > alpha)
                    ss12.setText("1");
                else
                    ss12.setText("0");

                if (v2 > alpha)
                    ss22.setText("1");
                else
                    ss22.setText("0");

                if (v3 > alpha)
                    ss32.setText("1");
                else
                    ss32.setText("0");

                if (v4 > alpha)
                    ss42.setText("1");
                else
                    ss42.setText("0");

            }

        });

        // *****************************************************************************
        // ---------------Action Bouton de choix de la fonction
        // d'activation-------------
        cont.add(bta);
        bta.setBackground(Color.white);
        bta.setForeground(Color.BLACK);
        bta.setBounds(705, 180, 320, 200);
        bta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                fncta = 1;
                typx.setText("- Fonction d'activation   :Logistique");

            }

        });

        cont.add(btb);
        btb.setBackground(Color.white);
        btb.setForeground(Color.BLACK);
        btb.setBounds(705, 440, 320, 200);
        btb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                fncta = 2;
                typx.setText("- Fonction d'activation   :Hyperbolique");

            }

        });

        // *******************************************************************************
        // ******************************************************************************
        cont.add(po1);
        po1.setBackground(Color.white);
        po1.setForeground(Color.BLACK);
        po1.setEditable(false);
        po1.setBounds(160, 325, 50, 20);

        cont.add(po2);
        po2.setBackground(Color.white);
        po2.setForeground(Color.BLACK);
        po2.setEditable(false);
        po2.setBounds(260, 325, 50, 20);

        cont.add(po3);
        po3.setBackground(Color.white);
        po3.setForeground(Color.BLACK);
        po3.setEditable(false);
        po3.setBounds(160, 525, 50, 20);

        cont.add(po4);
        po4.setBackground(Color.white);
        po4.setForeground(Color.BLACK);
        po4.setEditable(false);
        po4.setBounds(260, 525, 50, 20);

        cont.add(po5);
        po5.setBackground(Color.white);
        po5.setForeground(Color.BLACK);
        po5.setEditable(false);
        po5.setBounds(160, 390, 50, 20);

        cont.add(po6);
        po6.setBackground(Color.white);
        po6.setForeground(Color.BLACK);
        po6.setEditable(false);
        po6.setBounds(160, 450, 50, 20);

        cont.add(po7);
        po7.setBackground(Color.white);
        po7.setForeground(Color.BLACK);
        po7.setEditable(false);
        po7.setBounds(460, 370, 50, 20);

        cont.add(po8);
        po8.setBackground(Color.white);
        po8.setForeground(Color.BLACK);
        po8.setEditable(false);
        po8.setBounds(460, 460, 50, 20);

        cont.add(po9);
        po9.setBackground(Color.white);
        po9.setForeground(Color.BLACK);
        po9.setEditable(false);
        po9.setBounds(585, 370, 50, 20);
        // *****************************************************************************
        // Intialisation du valeur initaile

        s1.setText("0");
        s2.setText("1");
        s3.setText("1");
        s4.setText("0");
        typ.setText("- Fonction Logique : XOR");

        cont.add(ima);
        ima.setBackground(Color.white);
        ima.setForeground(Color.BLACK);
        ima.setBounds(00, 310, 640, 240);

        // ******************************************************************************

        cont.add(slider);
        slider.setBounds(860, 60, 150, 30);
        slider.setBackground(Color.white);
        slider.setForeground(Color.GRAY);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent er) {

                int x = slider.getValue();
                if (x <= 10)
                    x = 10;

                if (x == 100)
                    x = 99;

                double y = (x / 10);
                alpha = y / 10;
                llb2.setText(" ALPHA = " + y / 10);

            }

        }

        );

        cont.add(pbm);
        pbm.setValue(0);
        pbm.setStringPainted(true);
        pbm.setBounds(10, 660, 680, 30);
        pbm.setBackground(Color.white);
        pbm.setForeground(Color.red);

        cont.add(llb1x);
        llb1x.setBackground(Color.white);
        llb1x.setForeground(Color.BLACK);
        llb1x.setBounds(720, 120, 320, 40);
        llb1x.setFont(ftt);

        cont.add(llb11x);
        llb11x.setBackground(Color.white);
        llb11x.setForeground(Color.BLACK);
        llb11x.setBounds(720, 135, 300, 40);
        llb11x.setFont(ftt);

        // ******************************************************************************

        poids_aleatoire();
        affiche_poids();

        // *****************************************************************************

    }

    // ******************************************************************************
    // ------------------------------
    // ActionPerformed--------------------------------

    public void actionPerformed(ActionEvent e) {
    }

    /// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // ***************** Les Différents Méthodes de l'application*******************

    // -------------------Mettre Les Différnete Poids avec des Variables
    // Alatoires-------
    // ----------------------------------------------------------------------------------

    public void poids_aleatoire() {

        p1 = (Math.random());
        p2 = (Math.random());
        p3 = (Math.random());
        p4 = (Math.random());
        p5 = (Math.random());
        p6 = (Math.random());
        p7 = (Math.random());
        p8 = (Math.random());
        p9 = (Math.random());

        /*
         * po1.setText("1"); po2.setText("2"); po3.setText("3"); po4.setText("4");
         * po5.setText("5"); po6.setText("6"); po7.setText("7"); po8.setText("8");
         * po9.setText("9");
         */

    }

    // *****************************************************************************
    // --------------------Affichage
    // Poids-------------------------------------------

    public void affiche_poids() {

        DecimalFormat df = new DecimalFormat("0.0000"); // Laisser 4 chiffres après la virgule

        /*
         * po1.setText(""+p1); po2.setText(""+p2); po3.setText(""+p3);
         * po4.setText(""+p4); po5.setText(""+p5); po6.setText(""+p6);
         * po7.setText(""+p7); po8.setText(""+p8); po9.setText(""+p9);
         * 
         * /*
         */

        po1.setText(df.format(p1));
        po2.setText(df.format(p2));
        po3.setText(df.format(p3));
        po4.setText(df.format(p4));
        po5.setText(df.format(p5));
        po6.setText(df.format(p6));
        po7.setText(df.format(p7));
        po8.setText(df.format(p8));
        po9.setText(df.format(p9));

    }

    // ******************************************************************************

    // ------------ Intialiser la Fontion
    // d'activation-------------------------------

    public double activation(double val) {
        double xval = 0;

        if (fncta == 1) {

            xval = (1 / (1 + Math.exp(val * -1)));
        }

        if (fncta == 2) {

            xval = (Math.exp(val) - Math.exp(-val)) / ((Math.exp(val) + Math.exp(-val)));
        }

        return xval;
    }

    // -----------------------------------------------------------------------------
    // ******************************************************************************

    // ----------------------Fonction d'aprentissage--------------------------------

    public void aprentissage(double x, double y, double z) {

        double delta1;
        double delta2;
        double delta3;

        double net1;
        double net2;
        double net3;
        // Récupération des valeurs desz Poids

        // Calcule la valeur de chaques neurones de la couche cachée :
        net1 = activation(p2 + x * p1 + y * p5);
        net2 = activation(p4 + x * p6 + y * p3);

        // Calcule la valeur du neurone de sortie :
        net3 = activation(p9 + net1 * p7 + net2 * p8);

        // RétroPropagation
        // -----------------------------------------------------------------------------

        // Calcul de Delta :
        delta3 = net3 * (1 - net3) * (z - net3);
        delta2 = net2 * (1 - net2) * p8 * delta3;
        delta1 = net1 * (1 - net1) * p7 * delta3;

        // *******************************************************************************

        p2 = p2 + alpha * 1 * delta1;
        p1 = p1 + alpha * x * delta1;
        p5 = p5 + alpha * y * delta1;

        p4 = p4 + alpha * 1 * delta2;
        p6 = p6 + alpha * x * delta2;
        p3 = p3 + alpha * y * delta2;

        p9 = p9 + alpha * 1 * delta3;
        p7 = p7 + alpha * net1 * delta3;
        p8 = p8 + alpha * net2 * delta3;

        System.out.println(delta3);
    }

    // ******************************************************************************
    // ******************************************************************************
    // ------------------ Fonction De
    // TEST-------------------------------------------
    public double test_rx(double x, double y) {

        double net1;
        double net2;
        double net3;

        // Calcule la valeur de chaques neurones de la couche cachée :
        net1 = activation(p2 + x * p1 + y * p5);
        net2 = activation(p4 + x * p6 + y * p3);

        // Calcule la valeur du neurone de sortie :
        net3 = activation(p9 + net1 * p7 + net2 * p8);

        return net3;
    }

    // ****************************************************************************
    // ****************************************************************************
    // ------------------- Méthodes Pour Affichage du temps------------------------

    // Lance le chronomètre
    public void start() {
        m_start.setTime(new Date());
    }

    // Arrète le chronomètre
    public void stop() {
        m_stop.setTime(new Date());
    }

    // Retourne le nombre de millisecondes séparant l'appel des méthode start() et
    // stop()
    public long getMilliSec() {
        return (m_stop.getTimeInMillis() - m_start.getTimeInMillis());
    }

    // Affiche le nombre de millisecondes séparant l'appel des méthode start() et
    // stop() sur la sortie standard
    public void printMilliSec() {

        int x = (int) getMilliSec() / 1000;
        int y = (int) getMilliSec() % 1000;

        tmp.setText("- Temps d'aprentissage     :" + x + "." + y + "s");

        System.out.println("Temps d'exécution : " + x + "." + y + "s");

    }

    // *****************************************************************************
    // *****************************************************************************

    public static void main(String args[]) {
        perceptron mm = new perceptron();
        mm.setVisible(true);

    }
}
