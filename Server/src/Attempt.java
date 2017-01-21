/**
 * Created by Alex on 1/20/2017.
 */
public class Attempt {
    private String ip;
    private int attempts;
    private String first_attempt;
    private String last_attempt;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getFirst_attempt() {
        return first_attempt;
    }

    public void setFirst_attempt(String first_attempt) {
        this.first_attempt = first_attempt;
    }

    public String getLast_attempt() {
        return last_attempt;
    }

    public void setLast_attempt(String last_attempt) {
        this.last_attempt = last_attempt;
    }
}
