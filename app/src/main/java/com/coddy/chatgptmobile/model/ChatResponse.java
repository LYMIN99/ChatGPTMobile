package com.coddy.chatgptmobile.model;

import java.util.List;

public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private UsageModel usage;
    private List<ChoiceModel> choices;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getObject() { return object; }
    public void setObject(String value) { this.object = value; }

    public long getCreated() { return created; }
    public void setCreated(long value) { this.created = value; }

    public String getModel() { return model; }
    public void setModel(String value) { this.model = value; }

    public UsageModel getUsage() { return usage; }
    public void setUsage(UsageModel value) { this.usage = value; }

    public List<ChoiceModel> getChoices() { return choices; }
    public void setChoices(List<ChoiceModel> value) { this.choices = value; }
}