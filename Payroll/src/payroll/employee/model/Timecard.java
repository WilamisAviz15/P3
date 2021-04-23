package payroll.employee.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Timecard{
    private LocalTime login;
    private LocalTime logout;
    private LocalDate date;

    public Timecard(LocalDate date, LocalTime login){
        this.date = date;
        this.login = login;
    }

    public Timecard(LocalDate date, LocalTime login, LocalTime logout){
        this.login = login;
        this.date = date;
        this.logout = logout;
    }

    public Timecard(Timecard t){
        this( t.getDate(),t.getLogin(), t.getLogout());
    }

    public LocalTime getLogin() {
        return login;
    }

    public void setLogin(LocalTime login) {
        this.login = login;
    }

    public LocalTime getLogout() {
        return logout;
    }

    public void setLogout(LocalTime logout) {
        this.logout = logout;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Login: " + getLogin() +" | "+ "Logout: " + getLogout() + "\n";
    }
}
