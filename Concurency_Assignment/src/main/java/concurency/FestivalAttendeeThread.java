package concurency;

public class FestivalAttendeeThread extends Thread{
    TicketType ticketType;
    FestivalGate gate;
    FestivalAttendeeThread(TicketType t,FestivalGate g){
        this.ticketType=t;
        this.gate=g;
    }
    public void run(){
        try{
            Thread.sleep((long)(Math.random()*100));
            gate.addTicket(ticketType);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
