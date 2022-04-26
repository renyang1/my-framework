package com.ry.designpatterns.snapshot;

/**
 * @author ryang
 * @Description
 * @date 2022年04月26日 6:57 下午
 */
public class InputText {
    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }
}
