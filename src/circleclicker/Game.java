/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleclicker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author lordstorm
 */
public class Game 
{
    
    public CopyOnWriteArrayList<CircleC> circles = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<CircleC> circles_to_remove = new CopyOnWriteArrayList<>();
    int points = 0;
    int clicks = 0;
    int time_between_tick_in_millisecons = 1;
    int tick_between_new_circle = 3000;
    int start_delay_in_milliseconds = 3000;
    int counter = 0;
    boolean game_continue = true;
    Timer timer;

    public Game() 
    {
        points = 0;
        game_continue = false;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() 
        {
            @Override
            public void run() 
            {
                if(game_continue)
                {
                if(clicks-points < 10)
                {
                counter++;
                if(counter % tick_between_new_circle == 0)
                {
                    circles.add(new CircleC(20, 100, 800, 2000, 800, 800));
                }
                for(CircleC c : circles)
                {
                    c.setLife(c.getLife() -1);
                    if(c.getLife() < 0)
                    {
                        circles_to_remove.add(c);
                        tick_between_new_circle -= 10;
                        clicks++;
                    }
                }
                circles.removeAll(circles_to_remove);
                }
                }
            }
        }, start_delay_in_milliseconds, time_between_tick_in_millisecons);
    }
    public void reset()
    {
        points = 0;
        time_between_tick_in_millisecons = 1;
        tick_between_new_circle = 3000;
        start_delay_in_milliseconds = 3000;
        counter = 0;
        game_continue = false;
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() 
        {
            @Override
            public void run() 
            {
                if(game_continue)
                {
                if(clicks-points < 10)
                {
                counter++;
                if(counter % tick_between_new_circle == 0)
                {
                    circles.add(new CircleC(20, 100, 800, 2000, 800, 800));
                }
                for(CircleC c : circles)
                {
                    c.setLife(c.getLife() -1);
                    if(c.getLife() < 0)
                    {
                        circles_to_remove.add(c);
                        tick_between_new_circle -= 10;
                        clicks++;
                    }
                }
                circles.removeAll(circles_to_remove);
                }
                }
            }
        }, start_delay_in_milliseconds, time_between_tick_in_millisecons);
    }
    public void pause_start()
    {
        game_continue = !game_continue;
    }
    
}
