package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar= new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    class MyComparator implements Comparator<Meeting>{

        @Override
        public int compare(Meeting o1, Meeting o2) {
            String end1=o1.getEndTime().toString().substring(0,2)+o1.getEndTime().toString().substring(3);
            String end2=o2.getEndTime().toString().substring(0,2)+o2.getEndTime().toString().substring(3);

            return end1.compareTo(end2);
        }
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.size()==0) return 0;
        Collections.sort(calendar,new MyComparator());

        int attend=1;
        String endTime=calendar.get(0).getEndTime().toString().substring(0,2)+calendar.get(0).getEndTime().toString().substring(3);

        for(int i=1;i<calendar.size();i++){
            String startTime=calendar.get(i).getStartTime().toString().substring(0,2)+calendar.get(i).getStartTime().toString().substring(3);

            //if start TIme of curr meeting > than end time of prev meeting then i can attend this meeting , so attend ++
            if(startTime.compareTo(endTime)>0){
                endTime=calendar.get(i).getEndTime().toString().substring(0,2)+calendar.get(i).getEndTime().toString().substring(3);
                attend++;
            }
        }
        return attend;
    }
}
