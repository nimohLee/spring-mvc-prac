package controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ListCommand {

    // @DateTimeFormat(pattern = "yyyyMMddHH")을 해놓으면 문자열을 해당 패턴의 LocalDateTime으로 변환해줌
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;

    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime to;



    public LocalDateTime getFrom() {
        return from;
    }


    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
