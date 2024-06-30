package concurency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FestivalStatisticsThread extends Thread {
    FestivalGate gate;
    boolean running=true;

    public FestivalStatisticsThread(FestivalGate g){
        this.gate=g;
    }
    public synchronized void stopRunning(){
        running=false;
    }
    public synchronized boolean isRunning(){
        return running;
    }
    public void run(){
        while(isRunning()){
            try{
                Thread.sleep(5000);
                generateStatistics();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void generateStatistics(){
        if(gate.hasTickets()){
            List<TicketType> tickets = gate.getTickets();
            int total = tickets.size();
            Map<TicketType, Long> ticketCounts = tickets.stream()
                    .collect(Collectors.groupingBy(ticket -> ticket, Collectors.counting()));

            System.out.println(total + " people entered");
            for (TicketType type : TicketType.values()) {
                long count = ticketCounts.containsKey(type) ? ticketCounts.get(type) : 0;
                System.out.println(count + " have " + type.name().toLowerCase().replace('_', ' ') + " tickets");

            }
        }
    }


}
