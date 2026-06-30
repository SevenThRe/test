/*
 * Decompiled with CFR 0.152.
 */
package optifine.json;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import optifine.json.JSONArray;
import optifine.json.JSONObject;
import optifine.json.JSONValue;

public class JSONWriter {
    private Writer writer = null;
    private int indentStep = 2;
    private int indent = 0;

    public JSONWriter(Writer writer) {
        this.writer = writer;
    }

    public JSONWriter(Writer writer, int indentStep) {
        this.writer = writer;
        this.indentStep = indentStep;
    }

    public JSONWriter(Writer writer, int indentStep, int indent) {
        this.writer = writer;
        this.indentStep = indentStep;
        this.indent = indent;
    }

    public void writeObject(Object obj) throws IOException {
        if (obj instanceof JSONObject) {
            JSONObject jObj = (JSONObject)obj;
            this.writeJsonObject(jObj);
            return;
        }
        if (obj instanceof JSONArray) {
            JSONArray jArr = (JSONArray)obj;
            this.writeJsonArray(jArr);
            return;
        }
        this.writer.write(JSONValue.toJSONString(obj));
    }

    private void writeJsonArray(JSONArray jArr) throws IOException {
        this.writeLine("[");
        this.indentAdd();
        int num = jArr.size();
        int i = 0;
        while (i < num) {
            Object val = jArr.get(i);
            this.writeIndent();
            this.writeObject(val);
            if (i < jArr.size() - 1) {
                this.write(",");
            }
            this.writeLine("");
            ++i;
        }
        this.indentRemove();
        this.writeIndent();
        this.writer.write("]");
    }

    private void writeJsonObject(JSONObject jObj) throws IOException {
        this.writeLine("{");
        this.indentAdd();
        Set keys = jObj.keySet();
        int keyNum = keys.size();
        int count = 0;
        for (String key : keys) {
            Object val = jObj.get(key);
            this.writeIndent();
            this.writer.write(JSONValue.toJSONString(key));
            this.writer.write(": ");
            this.writeObject(val);
            if (++count < keyNum) {
                this.writeLine(",");
                continue;
            }
            this.writeLine("");
        }
        this.indentRemove();
        this.writeIndent();
        this.writer.write("}");
    }

    private void writeLine(String str) throws IOException {
        this.writer.write(str);
        this.writer.write("\n");
    }

    private void write(String str) throws IOException {
        this.writer.write(str);
    }

    private void writeIndent() throws IOException {
        int i = 0;
        while (i < this.indent) {
            this.writer.write(32);
            ++i;
        }
    }

    private void indentAdd() {
        this.indent += this.indentStep;
    }

    private void indentRemove() {
        this.indent -= this.indentStep;
    }
}

