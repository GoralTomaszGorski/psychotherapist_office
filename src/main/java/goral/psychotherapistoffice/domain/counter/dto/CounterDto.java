package goral.psychotherapistoffice.domain.counter.dto;

import java.time.LocalDate;
import java.util.Date;

public class CounterDto {

    private Long id;
    private String sessionId;
    private String ip;
    private int refresh;
    private int entry;
    private String url;
    private Date date;

    public CounterDto(Long id, String sessionId, String ip, int refresh, int entry, String url, LocalDate date) {
    }

    public CounterDto(Long id, String sessionId, String ip, int refresh, int entry, String url, Date date) {
        this.id = id;
        this.sessionId = sessionId;
        this.ip = ip;
        this.refresh = refresh;
        this.entry = entry;
        this.url = url;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
