package payroll.employee;

import java.util.Date;

public class Timecard{
    private String login;
    private String logout;
    private String date;

    public Timecard(String date, String login){
        this.login = login;
        this.date = date;
    }

    public Timecard(String logout){
        this.logout = logout;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Login: " + getLogin() +" | "+ "Logout: " + getLogout();
    }
}
