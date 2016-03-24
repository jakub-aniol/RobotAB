package com.epam.robot.util;


/**
 * The class {@code NoSubscriberException} can be thrown when no {@code Subscriber<?>} is found in {@code messageBus.MessageChannel}.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class NoSubscriberException extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NoSubscriberException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public NoSubscriberException(String message) {
        super(message);
    }
}
