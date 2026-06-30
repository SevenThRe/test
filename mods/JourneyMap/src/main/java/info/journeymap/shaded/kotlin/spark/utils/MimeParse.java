/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MimeParse {
    public static final String NO_MIME_TYPE = "";

    private static ParseResults parseMimeType(String mimeType) {
        int slashIndex;
        String[] parts = mimeType.split(";");
        ParseResults results = new ParseResults();
        results.params = new HashMap<String, String>();
        for (int i = 1; i < parts.length; ++i) {
            String p = parts[i];
            String[] subParts = p.split("=");
            if (subParts.length != 2) continue;
            results.params.put(subParts[0].trim(), subParts[1].trim());
        }
        String fullType = parts[0].trim();
        if (fullType.equals("*")) {
            fullType = "*/*";
        }
        if ((slashIndex = fullType.indexOf(47)) != -1) {
            results.type = fullType.substring(0, slashIndex);
            results.subType = fullType.substring(slashIndex + 1);
        } else {
            results.type = fullType;
            results.subType = "*";
        }
        return results;
    }

    private static ParseResults parseMediaRange(String range) {
        ParseResults results = MimeParse.parseMimeType(range);
        String q = results.params.get("q");
        float f = MimeParse.toFloat(q, 1.0f);
        if (MimeParse.isBlank(q) || f < 0.0f || f > 1.0f) {
            results.params.put("q", "1");
        }
        return results;
    }

    private static FitnessAndQuality fitnessAndQualityParsed(String mimeType, Collection<ParseResults> parsedRanges) {
        int bestFitness = -1;
        float bestFitQ = 0.0f;
        ParseResults target = MimeParse.parseMediaRange(mimeType);
        for (ParseResults range : parsedRanges) {
            if (!target.type.equals(range.type) && !range.type.equals("*") && !target.type.equals("*") || !target.subType.equals(range.subType) && !range.subType.equals("*") && !target.subType.equals("*")) continue;
            for (String k : target.params.keySet()) {
                int paramMatches = 0;
                if (!k.equals("q") && range.params.containsKey(k) && target.params.get(k).equals(range.params.get(k))) {
                    ++paramMatches;
                }
                int fitness = range.type.equals(target.type) ? 100 : 0;
                fitness += range.subType.equals(target.subType) ? 10 : 0;
                if ((fitness += paramMatches) <= bestFitness) continue;
                bestFitness = fitness;
                bestFitQ = MimeParse.toFloat(range.params.get("q"), 0.0f);
            }
        }
        return new FitnessAndQuality(bestFitness, bestFitQ);
    }

    public static String bestMatch(Collection<String> supported, String header) {
        LinkedList<ParseResults> parseResults = new LinkedList<ParseResults>();
        LinkedList<FitnessAndQuality> weightedMatches = new LinkedList<FitnessAndQuality>();
        for (String r : header.split(",")) {
            parseResults.add(MimeParse.parseMediaRange(r));
        }
        for (String s : supported) {
            FitnessAndQuality fitnessAndQuality = MimeParse.fitnessAndQualityParsed(s, parseResults);
            fitnessAndQuality.mimeType = s;
            weightedMatches.add(fitnessAndQuality);
        }
        Collections.sort(weightedMatches);
        FitnessAndQuality lastOne = (FitnessAndQuality)weightedMatches.get(weightedMatches.size() - 1);
        return Float.compare(lastOne.quality, 0.0f) != 0 ? lastOne.mimeType : NO_MIME_TYPE;
    }

    private static boolean isBlank(String s) {
        return s == null || NO_MIME_TYPE.equals(s.trim());
    }

    private static float toFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        }
        catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    private MimeParse() {
    }

    private static class FitnessAndQuality
    implements Comparable<FitnessAndQuality> {
        int fitness;
        float quality;
        String mimeType;

        private FitnessAndQuality(int fitness, float quality) {
            this.fitness = fitness;
            this.quality = quality;
        }

        @Override
        public int compareTo(FitnessAndQuality o) {
            if (this.fitness == o.fitness) {
                if (this.quality == o.quality) {
                    return 0;
                }
                return this.quality < o.quality ? -1 : 1;
            }
            return this.fitness < o.fitness ? -1 : 1;
        }
    }

    private static class ParseResults {
        String type;
        String subType;
        Map<String, String> params;

        private ParseResults() {
        }

        public String toString() {
            StringBuffer s = new StringBuffer("('" + this.type + "', '" + this.subType + "', {");
            for (String k : this.params.keySet()) {
                s.append("'" + k + "':'" + this.params.get(k) + "',");
            }
            return s.append("})").toString();
        }
    }
}

