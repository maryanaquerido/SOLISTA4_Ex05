package view;

import controller.ControllerPingServidores;

public class PrincipalPingServidores {
    public static void main(String[] args) {
  
        String sName = System.getProperty("os.name");
        if (!sName.contains("linux")) {
            System.out.println("Somente Linux! Outros Sistemas Operacionais devem ser descartados.\n");
        }

        String[] servidores = {"www.uol.com.br", "www.terra.com.br", "www.google.com.br"};
        String[] nomeServidores = {"UOL", "Terra", "Google"};

        for (int i = 0; i < servidores.length; i++) {
            String servidor = servidores[i];
            String nomeServidor = nomeServidores[i];
            ControllerPingServidores controller = new ControllerPingServidores(servidor, nomeServidor);
            new Thread(() -> controller.PingServidores(10)).start();
        }
    }
}