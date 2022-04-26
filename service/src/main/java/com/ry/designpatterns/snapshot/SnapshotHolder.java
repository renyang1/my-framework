package com.ry.designpatterns.snapshot;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author ryang
 * @Description
 * @date 2022年04月26日 7:00 下午
 */
public class SnapshotHolder {
    private Stack<InputText> snapshots = new Stack<>();

    public InputText popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(InputText inputText) {
        InputText deepClone = new InputText();
        deepClone.setText(inputText.getText());
        snapshots.push(deepClone);
    }

    public static void main(String[] args) {
        // 当前数据
        InputText inputText = new InputText();
        // 缓存对象
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (Objects.equals(input, "list")) {
                System.out.println(inputText.getText());
            } else if (Objects.equals(input, "undo")) {
                InputText snapshot = snapshotHolder.popSnapshot();
                // 用缓存中数据替换当前数据
                inputText.setText(snapshot.getText());
            } else {
                // 缓存之前数据
                snapshotHolder.pushSnapshot(inputText);
                // 添加新数据
                inputText.append(input);
            }
        }
    }
}
