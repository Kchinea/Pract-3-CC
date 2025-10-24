package src;

import java.util.List;

public class Trace {
    private static boolean enabled = false;

    public static void setEnabled(boolean e) {
        enabled = e;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void log(String className, List<Integer> args) {
        if (!enabled) return;
        StringBuilder sb = new StringBuilder();
        sb.append("[TRACE] ");
        sb.append(className);
        sb.append(" called with args=");
        sb.append(args);
        System.out.println(sb.toString());
    }
}
