package com.example.demo;

public class TopicMessage
{
    private String topicLabel;

    private String topicOp;

    private String topicXpath;

    public TopicMessage(){}

    public TopicMessage(String tLabel, String tOp, String tAttr) {
        topicLabel = tLabel;
        topicOp = tOp;
        topicXpath = tAttr;
    }

    public String getTopicLabel()
    {
        return topicLabel;
    }

    public void setTopicLabel(String topicLabel) { this.topicLabel = topicLabel; }

    public String getTopicOp()
    {
        return topicOp;
    }

    public void setTopicOp(String topicOp)
    {
        this.topicOp = topicOp;
    }

    public String getTopicXpath()
    {
        return topicXpath;
    }

    public void setTopicXpath(String topicXpath)
    {
        this.topicXpath = topicXpath;
    }
}
