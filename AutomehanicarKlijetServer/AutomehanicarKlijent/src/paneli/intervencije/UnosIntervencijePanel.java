/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UnosIntervencijePanel.java
 *
 * Created on Apr 26, 2012, 1:02:54 AM
 */
package paneli.intervencije;

import kontrolerKI.intervencije.UnosIntervencijeKI;

/**
 *
 * @author Rus
 */
public class UnosIntervencijePanel extends javax.swing.JPanel {

    /**
     * Creates new form UnosIntervencijePanel
     */
    public UnosIntervencijePanel() {
        initComponents();
        UnosIntervencijeKI.vratiObjekat().postaviSveKomponente(jPanel1, jcbAutomobil, jftfDatum, jlDatum, jtblOpisIntervencije, jtblUradjeneIntervencije, jtfRadnik, jtfSifraIntervencije);
        UnosIntervencijeKI.vratiObjekat().srediKomponente();
        UnosIntervencijeKI.vratiObjekat().napuniCombo();
        UnosIntervencijeKI.vratiObjekat().popuniTabeluSvimIntervencijama();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbtUbaciIntervenciju = new javax.swing.JButton();
        jlDatum = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfRadnik = new javax.swing.JTextField();
        jcbAutomobil = new javax.swing.JComboBox();
        jftfDatum = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUradjeneIntervencije = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblOpisIntervencije = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jtfSifraIntervencije = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true), "Unos intervencije nad automobilima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Unos podataka o intervencijama:");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Radnik:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel5.setText("Datum intervencije:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel6.setText("Sve intervencije:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        jbtUbaciIntervenciju.setText("Ubaci intervenciju");
        jbtUbaciIntervenciju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtUbaciIntervencijuActionPerformed(evt);
            }
        });
        jPanel1.add(jbtUbaciIntervenciju, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 130, -1));
        jPanel1.add(jlDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 190, 20));

        jLabel2.setText("Automobil:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        jPanel1.add(jtfRadnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 300, 25));

        jPanel1.add(jcbAutomobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 300, -1));
        jPanel1.add(jftfDatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 100, 25));

        jtblUradjeneIntervencije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblUradjeneIntervencije.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblUradjeneIntervencijeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtblUradjeneIntervencije);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 300, 160));

        jLabel4.setText("Uradjene intervencije:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jtblOpisIntervencije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblOpisIntervencije.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jtblOpisIntervencije.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblOpisIntervencijeMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtblOpisIntervencije);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 320, 300));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 400, 780, -1));

        jLabel7.setText("Šifra intervencije:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        jPanel1.add(jtfSifraIntervencije, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 300, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtUbaciIntervencijuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtUbaciIntervencijuActionPerformed
        UnosIntervencijeKI.vratiObjekat().jbtUbaciIntervencijuActionPerformed();
    }//GEN-LAST:event_jbtUbaciIntervencijuActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        jtfRadnik.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained

    private void jtblOpisIntervencijeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblOpisIntervencijeMousePressed
        UnosIntervencijeKI.vratiObjekat().jtblOpisIntervencijeMousePressed(evt);
    }//GEN-LAST:event_jtblOpisIntervencijeMousePressed

    private void jtblUradjeneIntervencijeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblUradjeneIntervencijeMousePressed
        UnosIntervencijeKI.vratiObjekat().jtblUradjeneIntervencijeMousePressed(evt);
    }//GEN-LAST:event_jtblUradjeneIntervencijeMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtUbaciIntervenciju;
    private javax.swing.JComboBox jcbAutomobil;
    private javax.swing.JFormattedTextField jftfDatum;
    private javax.swing.JLabel jlDatum;
    private javax.swing.JTable jtblOpisIntervencije;
    private javax.swing.JTable jtblUradjeneIntervencije;
    private javax.swing.JTextField jtfRadnik;
    private javax.swing.JTextField jtfSifraIntervencije;
    // End of variables declaration//GEN-END:variables
}