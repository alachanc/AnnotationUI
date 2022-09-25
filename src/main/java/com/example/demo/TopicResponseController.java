package com.example.demo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.HashMap;

@Controller
public class TopicResponseController
{
    private final HashMap<String, TopicContainer> containers = new HashMap<>(){{
        put("FIRST", new TopicContainer(new Topic[]{new Topic("API")}));
    }};
    private TopicContainer currCont = null;

    @MessageMapping("/topics")
    @SendTo("/topic/topics")
    public TopicContainerResponse respondTopic(TopicMessage tm) throws Exception
    {
        if(currCont == null) { currCont = containers.get("FIRST"); return new TopicContainerResponse(currCont.getTopics()); }
        return switch (tm.getTopicOp())
                {
                    case "ADD" -> new TopicContainerResponse(currCont.addTopic(tm.getTopicLabel(), tm.getTopicXpath()));
                    case "EDIT" -> new TopicContainerResponse(currCont.editTopic(tm.getTopicLabel(), tm.getTopicXpath()));
                    case "DELETE" -> new TopicContainerResponse(currCont.deleteTopic(tm.getTopicLabel(), tm.getTopicXpath()));
                    case "SWITCH" -> new TopicContainerResponse(currCont.switchTopic(tm.getTopicLabel(), tm.getTopicXpath()));
                    default -> new TopicContainerResponse(currCont.getTopics());
                };
    }
}