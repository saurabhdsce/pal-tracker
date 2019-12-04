package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository {

    Long counter =0L;
    Map<Long,TimeEntry> store = new HashMap<>();


    @Override
    public TimeEntry create(TimeEntry any) {
        TimeEntry timeEntry =any;

        counter++;
        System.out.println("Counter: " + counter);
        timeEntry.setId(counter);
        store.put(counter, timeEntry);
       System.out.println("any = [" + timeEntry + "]");
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return store.get(timeEntryId);


    }

    @Override
    public List<TimeEntry> list() {
        ArrayList<TimeEntry> timeEntryList= new ArrayList<TimeEntry>(store.values());
        for (TimeEntry te :timeEntryList){
            System.out.println(te);
        }

        return timeEntryList;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {

        TimeEntry te =any;

        if (store.containsKey(eq)) {
            te.setId(eq);
            store.put(eq, te);
            System.out.println(te);
            return te;
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        System.out.println("Before delete "+store.get(timeEntryId));
        store.remove(timeEntryId);
        System.out.println("After delete " + store.get(timeEntryId));


    }

}
