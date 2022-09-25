package com.example.demo;

import java.util.Arrays;

public class TopicContainerResponse
{
    private String[] topics;
    public TopicContainerResponse(){}
    public TopicContainerResponse(Topic[] t) {
        this.topics = Arrays.stream(t).map(t_->t_.getLabel() + "::" + t_.getXpaths()).toArray(String[]::new);
    }
    public void setTopics(Topic[] t) {
        this.topics = Arrays.stream(t).map(t_->t_.getLabel() + "::" + t_.getXpaths()).toArray(String[]::new);
    }
    public String[] getTopics() {
        return topics;
    }
}
