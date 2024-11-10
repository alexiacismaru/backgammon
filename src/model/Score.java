package model;

public class Score {
    long startTime=0;
    long timePassed=0;

    public Score() {//empty constructor
    }

    public void startChronometer(){//method that starts the chronometer by saving the current date
        startTime=System.currentTimeMillis();
    }

    public void stopChronometer(){//method that stops the chronometer by calculating the time passed since it was first started and stores the value
        timePassed=System.currentTimeMillis()-startTime;
    }

    public void loadScore(long passedTime) {//method that loads the time that passed during a previous gameplay by resetting the startTime to the current time
        this.startTime = System.currentTimeMillis() - passedTime;       //minus the previous interval saved
    }

    public void getValue(){//method that prints out how much time have passed in a human-readable format MM:SS
        int displayMinutes = 0;
        long secondsPassed=timePassed/1000;//time is stored in milliseconds, so we need to convert it to seconds by dividing it by 1000 first
        while(secondsPassed >= 60){//while the number of seconds is greater than 60
            displayMinutes++;//add 1 to the minutes
            secondsPassed-=60;//subtract 60 from the seconds
        }
        System.out.println("It took you "+displayMinutes+" min and "+secondsPassed+" sec to finish to game !");//message that is displayed
    }


    public String getTimeValue(long time){//method that prints out a given time interval in a human-readable format MM:SS
        int displayMinutes = 0;
        long secondsPassed=time/1000;//the value is converted from milliseconds to seconds
        while(secondsPassed >= 60){//while the number of seconds is greater than 60
            displayMinutes++;//add 1 to the minutes
            secondsPassed-=60;//subtract 60 from the seconds
        }
        return displayMinutes+":"+secondsPassed;
    }

    public long getScore(){//getter that returns the recorded time in milliseconds
        stopChronometer();
        return this.timePassed;
    }
}
