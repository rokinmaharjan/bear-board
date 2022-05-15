package com.rokin.baylorboard.controller;


import com.rokin.baylorboard.domain.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {



    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public MessageBean send(@Payload MessageBean message) {
        return message;
    }


    public void fireGreeting(MessageBean m) {
        System.out.println("Fire");
        //this.template.convertAndSend("/app/user-all", m);
    }

    @MessageMapping("/user")
    public void sendNews(@Payload MessageBean message) {
        this.simpMessagingTemplate.convertAndSend("/topic/user", message);
    }

    @GetMapping("/res")
    public void fireGreeting1() {
        MessageBean tweetmsg = new MessageBean();
        tweetmsg.setName("boo");
        tweetmsg.setMessage("blaah");
        sendNews(tweetmsg);
        System.out.println("Fireeeeuu");
    }

}
