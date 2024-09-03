package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ControllerPingServidores {
	private String servidor;
	private String nomeServidor;
	private int successfulPings;
	 
	public ControllerPingServidores(String servidor, String nomeServidor) {
		this.servidor = servidor;
		this.nomeServidor = nomeServidor;
		this.successfulPings = 0;
	}
	
	public int PingServidores (int iteracao) {
		 try {
	            for (int i = 0; i < iteracao; i++) {
	                long tempoInicial = System.nanoTime();
	                
	                String operacao = "ping -4 -c 1 " + servidor;
	                Process process = Runtime.getRuntime().exec(operacao);
	                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	                String line;
	                double tempoResposta = 0;
	                boolean success = false;
	                
	                while ((line = reader.readLine()) != null) {
	                    if (line.contains("time=")) {
	                        int startIndex = line.indexOf("time=") + 5; 
	                        int endIndex = line.indexOf(" ms", startIndex);
	                        
	                        String tempo = line.substring(startIndex, endIndex);
	                        
	                        double time = Double.parseDouble(tempo);
	                        tempoResposta += time;
	                        success = true;
	                    }
	                }
	                
	                long tempoFinal = System.nanoTime();
	                double tempoTotal = (tempoFinal - tempoInicial)/Math.pow(10,9);
	       
	                if (success) {
	                    successfulPings++;
	                    System.out.println(nomeServidor  + " - Iteração " + (i + 1) + ": " + tempoResposta + " ms, Tempo de execução: " + tempoTotal +"s.\n");
	                } else {
	                    System.out.println(nomeServidor  + " - Iteração " + (i + 1) + ": Falha no ping, Tempo de execução: " + tempoTotal +"s.\n");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return successfulPings;
	    }
		
	}


