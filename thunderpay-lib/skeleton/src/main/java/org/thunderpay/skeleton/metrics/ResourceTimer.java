/**
 * @file ResourceTimer.java
 * @author Krisna Pranav
 * @brief Resource Timer
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.skeleton.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.thunderpay.metrics.api.MetricRegistry;
import org.thunderpay.metrics.api.Timer;
import org.thunderpay.commons.utils.Joiner;

public class ResourceTimer {
    private static final Joiner METRIC_NAME_JOINER = Joiner.on('.');
    private final String resourcePath;
    private final String name;
    private final String httpMethod;
    private final Map<String, Object> tags;
    private final MetricRegistry registry;

    public ResourceTimer(final String resourcePath, final String name, final String httpMethod, @Nullable final Map<String, Object> tags, final MetricRegistry registry) {
        this.resourcePath = resourcePath;
        this.name = name;
        this.httpMethod = httpMethod;
        this.tags = tags == null ? null : new HashMap<>(tags);
        this.registry = registry;
    }

    public void update(final int responseStatus, final long duration, final TimeUnit unit) {
        final String metricName;
        if (tags != null && !tags.isEmpty()) {
            final String tags = METRIC_NAME_JOINER.join(getTagsValues());
            metricName = METRIC_NAME_JOINER.join(
                    escapeMetrics("kb_resource", resourcePath, name, httpMethod, tags, responseStatusGroup(responseStatus), responseStatus));
        } else {
            metricName = METRIC_NAME_JOINER.join(
                    escapeMetrics("kb_resource", resourcePath, name, httpMethod, responseStatusGroup(responseStatus), responseStatus));
        }

        final Timer timer = registry.timer(metricName);
        timer.update(duration, unit);
    }

    public String responseStatusGroup(final int responseStatus) {
        return String.format("%sxx", responseStatus / 100);
    }
}
