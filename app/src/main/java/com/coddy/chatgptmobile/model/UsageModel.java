package com.coddy.chatgptmobile.model;

public class UsageModel {
    private long promptTokens;
    private long completionTokens;
    private long totalTokens;

    public long getPromptTokens() { return promptTokens; }
    public void setPromptTokens(long value) { this.promptTokens = value; }

    public long getCompletionTokens() { return completionTokens; }
    public void setCompletionTokens(long value) { this.completionTokens = value; }

    public long getTotalTokens() { return totalTokens; }
    public void setTotalTokens(long value) { this.totalTokens = value; }
}
