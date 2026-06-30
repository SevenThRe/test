/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigSortingRuleType;
import invtweaks.InvTweaksItemTree;
import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksConfigSortingRule
implements Comparable<InvTweaksConfigSortingRule> {
    private static final Pattern constraintVertical = Pattern.compile("v", 82);
    private static final Pattern constraintReverse = Pattern.compile("r", 82);
    private String constraint;
    @Nullable
    private int[] preferredPositions;
    private String keyword;
    private InvTweaksConfigSortingRuleType type;
    private int priority;
    private int containerSize;
    private int containerRowSize;

    public InvTweaksConfigSortingRule(@NotNull InvTweaksItemTree tree, String constraint_, String keyword_, int containerSize_, int containerRowSize_) {
        this.keyword = keyword_;
        this.constraint = constraint_;
        this.containerSize = containerSize_;
        this.containerRowSize = containerRowSize_;
        this.type = InvTweaksConfigSortingRule.getRuleType(this.constraint, this.containerRowSize);
        this.preferredPositions = this.getRulePreferredPositions(this.constraint);
        this.priority = this.type.getLowestPriority() + 100000 + tree.getKeywordDepth(this.keyword) * 1000 - tree.getKeywordOrder(this.keyword);
    }

    @Nullable
    public static int[] getRulePreferredPositions(@NotNull String constraint, int containerSize, int containerRowSize) {
        int[] result = null;
        int containerColumnSize = containerSize / containerRowSize;
        if (constraint.length() >= 5) {
            String[] elements;
            boolean vertical = false;
            Matcher verticalMatcher = constraintVertical.matcher(constraint);
            if (verticalMatcher.find()) {
                vertical = true;
                constraint = verticalMatcher.reset().replaceAll("");
            }
            if ((elements = constraint.split("-")).length == 2) {
                int[] slots1 = InvTweaksConfigSortingRule.getRulePreferredPositions(elements[0], containerSize, containerRowSize);
                int[] slots2 = InvTweaksConfigSortingRule.getRulePreferredPositions(elements[1], containerSize, containerRowSize);
                if (slots1.length == 1 && slots2.length == 1) {
                    int slot1 = slots1[0];
                    int slot2 = slots2[0];
                    Point point1 = new Point(slot1 % containerRowSize, slot1 / containerRowSize);
                    Point point2 = new Point(slot2 % containerRowSize, slot2 / containerRowSize);
                    result = new int[(Math.abs(point2.y - point1.y) + 1) * (Math.abs(point2.x - point1.x) + 1)];
                    int resultIndex = 0;
                    if (vertical) {
                        for (Point p : new Point[]{point1, point2}) {
                            int buffer = p.x;
                            p.x = p.y;
                            p.y = buffer;
                        }
                    }
                    int y = point1.y;
                    while (point1.y < point2.y ? y <= point2.y : y >= point2.y) {
                        int x = point1.x;
                        while (point1.x < point2.x ? x <= point2.x : x >= point2.x) {
                            result[resultIndex++] = vertical ? InvTweaksConfigSortingRule.index(containerRowSize, x, y) : InvTweaksConfigSortingRule.index(containerRowSize, y, x);
                            x += point1.x < point2.x ? 1 : -1;
                        }
                        y += point1.y < point2.y ? 1 : -1;
                    }
                    if (constraintReverse.matcher(constraint).find()) {
                        InvTweaksConfigSortingRule.reverseArray(result);
                    }
                }
            }
        } else {
            int i;
            int column = -1;
            int row = -1;
            boolean reverse = false;
            for (i = 0; i < constraint.length(); ++i) {
                char c = constraint.charAt(i);
                int digitValue = Character.digit(c, 36);
                if (digitValue >= 1 && digitValue <= containerRowSize && digitValue < 10) {
                    column = digitValue - 1;
                    continue;
                }
                if (digitValue >= 10 && digitValue - 10 <= containerColumnSize) {
                    row = digitValue - 10;
                    continue;
                }
                if (!InvTweaksConfigSortingRule.charEqualsIgnoreCase(c, 'r')) continue;
                reverse = true;
            }
            if (column != -1 && row != -1) {
                result = new int[]{InvTweaksConfigSortingRule.index(containerRowSize, row, column)};
            } else if (row != -1) {
                result = new int[containerRowSize];
                for (i = 0; i < containerRowSize; ++i) {
                    result[i] = InvTweaksConfigSortingRule.index(containerRowSize, row, reverse ? containerRowSize - 1 - i : i);
                }
            } else {
                result = new int[containerColumnSize];
                for (i = 0; i < containerColumnSize; ++i) {
                    result[i] = InvTweaksConfigSortingRule.index(containerRowSize, reverse ? i : containerColumnSize - 1 - i, column);
                }
            }
        }
        if (result == null) {
            InvTweaks.logInGameStatic("InvTweaks Config: Rule Constraint \"" + constraint + "\" was unable to be correctly determined.");
        }
        return result;
    }

    @NotNull
    public static InvTweaksConfigSortingRuleType getRuleType(@NotNull String constraint, int rowSize) {
        InvTweaksConfigSortingRuleType result = InvTweaksConfigSortingRuleType.SLOT;
        if (constraint.length() == 1 || constraint.length() == 2 && constraintReverse.matcher(constraint).find()) {
            int digitValue = Character.digit((constraint = constraintReverse.matcher(constraint).replaceAll("")).charAt(0), 10);
            result = digitValue >= 1 && digitValue <= rowSize ? InvTweaksConfigSortingRuleType.COLUMN : InvTweaksConfigSortingRuleType.ROW;
        } else if (constraint.length() > 4) {
            result = InvTweaksConfigSortingRule.charEqualsIgnoreCase(constraint.charAt(1), constraint.charAt(4)) ? InvTweaksConfigSortingRuleType.COLUMN : (InvTweaksConfigSortingRule.charEqualsIgnoreCase(constraint.charAt(0), constraint.charAt(3)) ? InvTweaksConfigSortingRuleType.ROW : InvTweaksConfigSortingRuleType.RECTANGLE);
        }
        return result;
    }

    private static boolean charEqualsIgnoreCase(char a, char b) {
        char bU;
        char aU = Character.toUpperCase(a);
        return aU == (bU = Character.toUpperCase(b)) || Character.toLowerCase(aU) == Character.toLowerCase(bU);
    }

    private static int index(int rowSize, int row, int column) {
        return row * rowSize + column;
    }

    private static void reverseArray(@NotNull int[] data) {
        int left = 0;
        for (int right = data.length - 1; left < right; ++left, --right) {
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }
    }

    public InvTweaksConfigSortingRuleType getType() {
        return this.type;
    }

    @Nullable
    public int[] getPreferredSlots() {
        return this.preferredPositions;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getContainerSize() {
        return this.containerSize;
    }

    @Override
    public int compareTo(@NotNull InvTweaksConfigSortingRule o) {
        return this.priority - o.priority;
    }

    @Nullable
    public int[] getRulePreferredPositions(@NotNull String constraint) {
        return InvTweaksConfigSortingRule.getRulePreferredPositions(constraint, this.containerSize, this.containerRowSize);
    }

    @NotNull
    public String toString() {
        return this.constraint + " " + this.keyword;
    }
}

