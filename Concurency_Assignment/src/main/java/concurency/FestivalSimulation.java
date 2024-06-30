package concurency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FestivalSimulation {
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();
        Random random = new Random();
        List<Thread> attendees = new ArrayList<>();

        // Create and start 100 attendee threads
        for (int i = 0; i < 100; i++) {
            TicketType ticketType = TicketType.values()[random.nextInt(TicketType.values().length)];
            Thread attendee = new FestivalAttendeeThread(ticketType, gate);
            attendees.add(attendee);
            attendee.start();
        }

        // Create and start the statistics thread
        FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(gate);
        statisticsThread.start();

        // Join all attendee threads
        for (Thread attendee : attendees) {
            try {
                attendee.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Indicate that no more attendees will arrive
        statisticsThread.stopRunning();
    }
}
