package Model;

import java.util.Date;

/**
 * set the start- and end date of the game
 */
public class GameTime {
    private Date startDate;
    private Date endDate;

    public GameTime(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
