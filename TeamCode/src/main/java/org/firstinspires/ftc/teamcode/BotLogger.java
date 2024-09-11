package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

public class BotLogger {

    /***
     * Informational labels to track the severity of a log message.
     */
    public enum LogLevel {
        /***
         * Best for tracing individual steps of an operation/function
         */
        Trace,
        /***
         * For informational & status logging
         */
        Info,
        /***
         * For important warnings, especially unexpected occurences
         */
        Warn,
        /***
         * For errors.
         */
        Error
    }

    /***
     * Redefine as needed for memory constraints, workability, etc.
      */
    private static final int MAX_BUFFERED_ENTRIES_COUNT = Integer.MAX_VALUE;

    /***
     * These logged messages are stored for retrieval anywhere in the codebase
      */
    private static final List<String> bufferedEntries = new ArrayList<String>();

    private final String sourceName;
    private List<BiConsumer<String, LogLevel>> logCallbacks;

    /***
     * You might want to keep one per class, created statically or on start.
     * @param sourceName Identifies which class, subcomponent, etc. is generating the log messages
     */
    public BotLogger(String sourceName) {
        this.sourceName = sourceName;
    }

    public void trace(String message) {
        bufferNewEntry("|TRACE| " + message, LogLevel.Trace);
    }

    public void info(String message) {
        bufferNewEntry("[INFO] " + message, LogLevel.Info);
    }

    public void warn(String message) {
        bufferNewEntry("<WARN> " + message, LogLevel.Warn);
    }

    public void error(String message) {
        bufferNewEntry("<!ERROR!> " + message, LogLevel.Error);
    }

    /**
     * Automatically report log messages to the device telemetry for this logger instance only.
     * You can specify a minimum log level, for example, such that only warnings and errors show up.
     * This is to avoid flooding telemetry. Set it to LogLevel.Trace for everything to show up.
     * @param telemetry
     */
    public void useTelemetry(Telemetry telemetry, LogLevel minimumLogLevel) {
        logCallbacks.add((str, level) -> {

            if (level.compareTo(minimumLogLevel) >= 0)
                telemetry.addLine(str);

        });
    }

    /**
     * Provide a callback to be executed on every message logged through this logger instance.
     * @param callback Has one string parameter and returns nothing.
     */
    public void useCustomCallback(BiConsumer<String, LogLevel> callback) {
        logCallbacks.add(callback);
    }

    /**
     * Internal method that adds logged messages to the buffered list.
     * @param message
     */
    protected void bufferNewEntry(String message, LogLevel level) {
        message =
                new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss] ").format(new Date())
                + message;

        for (BiConsumer<String, LogLevel> callback : logCallbacks) {
            callback.accept(message, level);
        }
        bufferedEntries.add(message);
    }

}
