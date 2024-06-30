package concurency;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FestivalGate {
    Queue<TicketType> ticketQueue=new PriorityQueue<>();
    public void addTicket(TicketType ticket){
        ticketQueue.add(ticket);
    }
    public List<TicketType> getTickets(){
        List<TicketType> ticketList = new ArrayList<>();
        ticketList.addAll(ticketQueue);
        return ticketList;
    }
    public boolean hasTickets(){
        return !ticketQueue.isEmpty();
    }
}
