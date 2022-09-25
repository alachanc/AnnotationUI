package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopicContainer {
    ArrayList<Topic> topics;

    public TopicContainer(Topic[] t) {
        topics = new ArrayList<>(Arrays.asList(t));
    }
    public Topic[] getTopics() { return topics.toArray(Topic[]::new); }
    public Topic[] switchTopic(String tl, String x) {
        for(int ind = 0; ind < topics.size(); ind++) {
            if(topics.get(ind).getLabel().equals(tl)) {
                Topic temp = topics.get(ind);
                topics.set(ind, topics.get(0));
                topics.set(0, temp);
            }
        }
        return getTopics();
    }
    public Topic[] addTopic(String tl, String x) {
        if(tl.equals("")) {
            topics.get(0).xpaths.push(x);
        } else {
            Topic t = new Topic();
            t.setLabel(tl);
            t.setXpaths(new Stack<>());
            t.xpaths.push(x);
            Topic temp = topics.get(0);
            topics.set(0, t);
            topics.add(temp);
        }
        return getTopics();
    }
    public Topic[] deleteTopic(String tl, String x) {
        if(x.equals("")) {
            topics.remove(0);
        } else {
            topics.get(0).xpaths.pop();
        }
        return getTopics();
    }
    public Topic[] editTopic(String tl, String x) {
        if(x.equals("")) {
            topics.get(0).setLabel(tl);
        } else {
            topics.get(0).xpaths.pop();
            topics.get(0).xpaths.push(x);
        }
        return getTopics();
    }

}

class Topic {
    String label;
    Stack<String> xpaths;

    public Topic() {
        label = "";
        xpaths = new Stack<String>();
        xpaths.push("");
    }
    public Topic(String l) {
        label = l;
        xpaths = new Stack<String>();
        xpaths.push("");
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getXpaths()
    {
        return xpaths.peek();
    }

    public String getXpath() { return xpaths.peek(); }

    public void setXpaths(Stack<String> xpaths)
    {
        this.xpaths = xpaths;
    }
}