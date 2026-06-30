/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.tools.Logger;

public class CommandExecutor {
    final Logger logger;

    public CommandExecutor(Logger logger) {
        this.logger = logger;
    }

    public int executeCommand(List<String> command, File workingDirectory, Map<String, String> environmentVariables) throws IOException, InterruptedException {
        String platform = Loader.getPlatform();
        boolean windows = platform.startsWith("windows");
        for (int i2 = 0; i2 < command.size(); ++i2) {
            Object arg = command.get(i2);
            if (arg == null) {
                arg = "";
            }
            if (((String)arg).trim().isEmpty() && windows) {
                arg = "\"\"";
            }
            command.set(i2, (String)arg);
        }
        String text = "";
        for (String s2 : command) {
            boolean hasSpaces;
            boolean bl2 = hasSpaces = s2.indexOf(" ") > 0 || s2.isEmpty();
            if (hasSpaces) {
                text = text + (windows ? "\"" : "'");
            }
            text = text + s2;
            if (hasSpaces) {
                text = text + (windows ? "\"" : "'");
            }
            text = text + " ";
        }
        this.logger.info(text);
        ProcessBuilder pb2 = new ProcessBuilder(command);
        if (workingDirectory != null) {
            pb2.directory(workingDirectory);
        }
        if (environmentVariables != null) {
            for (Map.Entry<String, String> e2 : environmentVariables.entrySet()) {
                if (e2.getKey() == null || e2.getValue() == null) continue;
                pb2.environment().put(e2.getKey(), e2.getValue());
            }
        }
        return pb2.inheritIO().start().waitFor();
    }
}

