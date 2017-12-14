/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd1718.practica01.interfaces;

import bbdd1718.practica01.clases.AppFutbol;
import bbdd1718.practica01.clases.Jugador;
import javax.swing.JOptionPane;


/**
 *
 * @author Andres
 */
public class MostrarJugador extends javax.swing.JFrame {

    /**
     * Creates new form MostrarJugador
     */
    public MostrarJugador() {
        initComponents();
        this.setLocationRelativeTo(null);//centramos.
        this.setTitle("Datos del jugador");
        this.setVisible(true);
        
        //
        Jugador j = AppFutbol.lJugadores.get(new Integer(AppFutbol.datosSistema[1]));

        this.inputNombre.setText(j.nombre);
        this.inputEmail.setText(j.getEmail());
        this.inputTelefono.setText(j.getTlf());
        this.inputSalario.setText(j.getSalario().toString());
        this.inputPosicion.setText(j.getPosicion());
        this.inputTitular.setText(Boolean.toString(j.getTitular()));
        this.inputNum.setText(Integer.toString(j.getNum()));
        //Comprobramos si se pidio mostrar a todos los jugadores o solo a los disponibles
        if(AppFutbol.datosSistema[0]==0){//Se pidio mostrar todos los jugadores
            try{
                this.inputEquipos.setText(AppFutbol.lEquipos.get(j.getIdEquipo()).getNameEquipo());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "El jugador no pertenece a ningun equipo",
                            "AppFutbol",JOptionPane.ERROR_MESSAGE);
                this.inputEquipos.setText("0"); //0 indica que no pertecene a ningun equipo
            }
        }else{//Se pidio mostrar jugadores disponibles
            this.inputEquipos.setText("0"); //0 indica que no pertecene a ningun equipo
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        labelTitulo = new javax.swing.JLabel();
        labelSubTitulo = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JLabel();
        jButtonCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inputTelefono = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputSalario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputEquipos = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inputTitular = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        inputPosicion = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        inputNum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitulo.setText("App Futbol Menu");

        labelSubTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelSubTitulo.setText("Datos del Jugador");

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNombre.setText("Nombre:");

        inputNombre.setText("jLabel1");

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCerrarMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("E-mail:");

        inputEmail.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Telefono:");

        inputTelefono.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Salario:");

        inputSalario.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Equipo:");

        inputEquipos.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Titular:");

        inputTitular.setText("jLabel10");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Poscion:");

        inputPosicion.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Num:");

        inputNum.setText("jLabel14");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelSubTitulo)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputEquipos)
                                    .addComponent(inputSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputTitular)
                                    .addComponent(inputNum))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonCerrar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(labelTitulo))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSubTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(inputNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputEmail)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inputEquipos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(inputPosicion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSalario)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(inputTitular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(inputNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtonCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCerrarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCerrarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MostrarJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarJugador().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel inputEmail;
    private javax.swing.JLabel inputEquipos;
    private javax.swing.JLabel inputNombre;
    private javax.swing.JLabel inputNum;
    private javax.swing.JLabel inputPosicion;
    private javax.swing.JLabel inputSalario;
    private javax.swing.JLabel inputTelefono;
    private javax.swing.JLabel inputTitular;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelSubTitulo;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
    
   
}