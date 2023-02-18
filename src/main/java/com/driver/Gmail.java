package com.driver;

import java.lang.reflect.Array;
import java.util.*;


public class Gmail extends Email {

      class Mail{

        private Date date;
        private String sender;

        public Mail(Date date,String sender){
            this.date=date;
            this.sender=sender;
        }

    }
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    HashMap<String,Mail> inbox= new LinkedHashMap<>();
    HashMap<String,Mail> trash=new LinkedHashMap<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){

        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail newMail=new Mail(date,sender);
        if(inbox.size()==inboxCapacity){
            String oldestMessage = findOldestMessage();

            trash.put(oldestMessage,inbox.get(oldestMessage));
            inbox.remove(oldestMessage);
            inbox.put(message,newMail);
        }
        else{
            inbox.put(message,newMail);
        }

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(inbox.containsKey(message)){
            trash.put(message,inbox.get(message));
            inbox.remove(message);
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()) return null;

        String latestMessage="";
        for(String message:inbox.keySet()){
            latestMessage=message;
        }
        return latestMessage;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()) return null;
        else{
            String oldestMessage="";
            for(String msg:inbox.keySet()){
                oldestMessage=msg;
                break;
            }
            return oldestMessage;
        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(String message : inbox.keySet()){
            Date msgDate=inbox.get(message).date;
            if(msgDate.compareTo(start)==0 || msgDate.compareTo(end)==0 || (msgDate.compareTo(start))>0 && msgDate.compareTo(end)<0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
    //created by me
    public void seeMessageInTrash(){
        for(String message:trash.keySet()){
            System.out.println(message);
        }
    }
}
