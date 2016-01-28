package jewelry.http;

import java.util.Date;

/**
 * Created by yang on 16-1-26.
 */

public class httpRequest {
    private Long title;
    private String content;
    private String author;
    private Date time;

    public httpRequest() {
    }

    public Long getTitle() {
        return title;
    }

    public void setTitle(Long title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
