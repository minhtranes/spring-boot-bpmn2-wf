package vn.minhtran.sbw.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
    
    @Input
    MessageChannel receiveCookChannel();
    
    @Output
    MessageChannel finishedReceiveCookChannel();
    
    @Input
    MessageChannel servePayChannel();
    
    @Output
    MessageChannel finishedServePayChannel();
    
}
