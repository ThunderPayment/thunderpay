/**
 * @file DOTBuilder.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton.dot;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

//import org.thunderpay.commons.utils.MapJoiner;
//import org.thunderpay.utils.

public class DOTBuilder {

    private static final String INDENT = "    ";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String EQUAL = "=";
    private static final MapJoiner MAP_JOINER = new MapJoiner(EQUAL, SPACE);
    private static final String SEMI_COLON = ";";

    private final String name;
    private final StringBuilder output;
    private int nextNodeId;
    private int nextClusterId;
    private int currentIndent;
    private String currentIndentString;

    public DOTBuilder(final String name) {
        this.name = name;
        this.output = new StringBuilder();
        this.nextNodeId = 0;
        this.nextClusterId = 0;
        this.currentIndent = 1;
        rebuildCurrentIndent();
    }

    public int addNode(final String name) {
        return addNode(name, null);
    }

    public int addNode(final String name, @Nullable final Map<String, String> attributesOrNull) {
        final Map<String, String> attributes = new HashMap<>();
        attributes.put("label", name);
        if (attributesOrNull != null) {
            attributes.putAll(attributesOrNull);
        }

        final String id = getNodeIdSymbol(nextNodeId);
        nextNodeId++;

        output.append(currentIndentString)
                .append(id);

        addAttributesInlineWithSpace(attributes);

        return nextNodeId - 1;
    }

    public void addPath(final int fromNodeId, final int toNodeId) {
        addPath(fromNodeId, toNodeId, null);
    }

    public void addPath(final int fromNodeId, final int toNodeId, @Nullable final Map<String, String> attributes) {
        addPath(getNodeIdSymbol(fromNodeId), getNodeIdSymbol(toNodeId), true, attributes);
    }

    public void addPath(final String from, final String to, final boolean directed, @Nullable final Map<String, String> attributes) {
        final String edgeSymbol = "-" + (directed ? ">" : "-");
        output.append(currentIndentString)
                .append(from)
                .append(SPACE)
                .append(edgeSymbol)
                .append(SPACE)
                .append(to);

        addAttributesInlineWithSpace(attributes);
    }

    public void openCluster(final String name) {
        openCluster(name, null);
    }

    public void openCluster(final String name, @Nullable final Map<String, String> attributes) {
        output.append(currentIndentString)
                .append("subgraph")
                .append(SPACE)
                .append("cluster_").append(nextClusterId)
                .append(SPACE)
                .append("{")
                .append(NEW_LINE);

        nextClusterId++;
        increaseCurrentIndent();

        output.append(currentIndentString)
                .append("label")
                .append(EQUAL)
                .append("\"")
                .append(name)
                .append("\"")
                .append(SEMI_COLON)
                .append(NEW_LINE);

        addAttributes(attributes);
    }

    public void closeCluster() {
        decreaseCurrentIndent();

        output.append(currentIndentString)
                .append("}")
                .append(NEW_LINE);
    }

    public void open() {
        open(null);
    }

    public void open(@Nullable final Map<String, String> attributes) {
        output.append("digraph")
                .append(SPACE)
                .append(name)
                .append(SPACE)
                .append("{")
                .append(NEW_LINE);

        addAttributesNoBrackets(attributes);
    }

    private void addAttributes(@Nullable final Map<String, String> attributes) {
        if (attributes != null) {
            output.append(currentIndentString);
            addAttributesInlineNoSpace(attributes);
        }
    }

    private void addAttributesNoBrackets(@Nullable final Map<String, String> attributes) {
        if (attributes != null) {
            output.append(currentIndentString);
            addAttributesInline(attributes, false, false);
        }
    }

    private void addAttributesInlineNoSpace(@Nullable final Map<String, String> attributes) {
        addAttributesInline(attributes, false);
    }

    private void addAttributesInlineWithSpace(@Nullable final Map<String, String> attributes) {
        addAttributesInline(attributes, true);
    }

    private void addAttributesInline(@Nullable final Map<String, String> attributes, final boolean withSpace) {
        addAttributesInline(attributes, withSpace, true);
    }

    private void addAttributesInline(@Nullable final Map<String, String> attributes, final boolean withSpace, final boolean withBrackets) {
        if (attributes != null) {
            final String attributesSymbol = (withSpace ? SPACE : "") + (withBrackets ? "[" : "") + MAP_JOINER.join(attributes) + (withBrackets ? "]" : "");
            output.append(attributesSymbol);
        }
        output.append(SEMI_COLON)
                .append(NEW_LINE);
    }

    public void close() {
        output.append("}")
                .append(NEW_LINE);
    }

    private String getNodeIdSymbol(final int nodeId) {
        return "node_" + nodeId;
    }

    private void increaseCurrentIndent() {
        currentIndent++;
        rebuildCurrentIndent();
    }

    private void decreaseCurrentIndent() {
        currentIndent--;
        rebuildCurrentIndent();
    }

    private void rebuildCurrentIndent() {
        currentIndentString = "";
        for (int i = 0; i < currentIndent; i++) {
            currentIndentString += INDENT;
        }
    }

    @Override
    public String toString() {
        return output.toString();
    }
}