package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestResultLogger implements TestWatcher {
    private static final Logger logger = LogManager.getLogger(TestResultLogger.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("TEST PASSED: [{}] - {}", context.getRequiredTestClass().getSimpleName(), context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("TEST FAILED: [{}] - {}", context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName());
        logger.error("Reason: {}", cause.getMessage());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("TEST ABORTED: [{}] - {}", context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.warn("TEST DISABLED: [{}] - {} with reason: {}", context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName(), reason.orElse("No reason"));
    }
}
