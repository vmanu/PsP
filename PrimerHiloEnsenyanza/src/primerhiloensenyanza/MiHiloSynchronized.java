/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerhiloensenyanza;

/**
 *
 * @author dam2
 */
public class MiHiloSynchronized implements Runnable {

    private int id;
    private StringBuffer sb;
    private String text;
    private StringBuffer out;
    private Turno turno;

    public MiHiloSynchronized(int id, String text, StringBuffer sb, StringBuffer out, Turno turno) {
        this.id = id;
        this.sb = sb;
        this.text = text;
        this.out = out;
        this.turno = turno;
    }

    @Override
    public void run() {
        boolean hecho = false;
        synchronized (sb) {//Obliga a pasar por aqui, de 1 en 1
            try {
                while (!hecho) {
                    if (turno.getTurno() == id) {
                        sb.append(text);
                        System.out.println("hilo " + id + sb.toString());
                        turno.setTurno(++id);
                        hecho=true;
                        sb.notifyAll();
                    } else {
                        sb.wait();
                    }
                }
            } catch (Exception ex) {

            }
            out.append("hello world " + id + sb.toString() + "\n");
        }
    }
}
