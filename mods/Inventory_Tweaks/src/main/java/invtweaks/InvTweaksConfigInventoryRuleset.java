/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksItemTree;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksConfigInventoryRuleset {
    private static final Pattern rulePattern = Pattern.compile("^(?:(?:[a-d1-9]r?)|(?:[a-d][1-9](?:-[a-d][1-9](?:rv?|vr?)?)?)) \\w+$", 322);
    private String name;
    private int[] lockPriorities;
    private boolean[] frozenSlots;
    private List<InvTweaksConfigSortingRule> rules;
    private List<String> autoReplaceRules;
    private InvTweaksItemTree tree;

    public InvTweaksConfigInventoryRuleset(InvTweaksItemTree tree_, @NotNull String name_) {
        int i;
        this.tree = tree_;
        this.name = name_.trim();
        this.lockPriorities = new int[36];
        for (i = 0; i < this.lockPriorities.length; ++i) {
            this.lockPriorities[i] = 0;
        }
        this.frozenSlots = new boolean[36];
        for (i = 0; i < this.frozenSlots.length; ++i) {
            this.frozenSlots[i] = false;
        }
        this.rules = new ArrayList<InvTweaksConfigSortingRule>();
        this.autoReplaceRules = new ArrayList<String>();
    }

    @Nullable
    public String registerLine(@NotNull String rawLine) throws InvalidParameterException {
        String lineText = rawLine.replaceAll("\\s+", " ");
        String[] words = lineText.split(" ");
        if (words.length == 2) {
            if (rulePattern.matcher(lineText).matches()) {
                if (words[1].equalsIgnoreCase("locked")) {
                    int[] newLockedSlots = InvTweaksConfigSortingRule.getRulePreferredPositions(words[0], 36, 9);
                    int lockPriority = InvTweaksConfigSortingRule.getRuleType(words[0], 9).getLowestPriority() - 1;
                    for (int i : newLockedSlots) {
                        this.lockPriorities[i] = lockPriority;
                    }
                    return null;
                }
                if (words[1].equalsIgnoreCase("frozen")) {
                    int[] newLockedSlots;
                    for (int i : newLockedSlots = InvTweaksConfigSortingRule.getRulePreferredPositions(words[0], 36, 9)) {
                        this.frozenSlots[i] = true;
                    }
                    return null;
                }
                String keyword = words[1];
                boolean isValidKeyword = this.tree.isKeywordValid(keyword);
                if (isValidKeyword) {
                    InvTweaksConfigSortingRule newRule = new InvTweaksConfigSortingRule(this.tree, words[0], keyword, 36, 9);
                    this.rules.add(newRule);
                    return null;
                }
                return keyword;
            }
            if (words[0].equalsIgnoreCase("autorefill") || words[0].equalsIgnoreCase("autoreplace")) {
                words[1] = words[1];
                if (this.tree.isKeywordValid(words[1]) || words[1].equalsIgnoreCase("nothing")) {
                    this.autoReplaceRules.add(words[1]);
                }
                return null;
            }
        }
        throw new InvalidParameterException();
    }

    public void finalizeRules() {
        if (this.autoReplaceRules.isEmpty()) {
            try {
                this.autoReplaceRules.add(this.tree.getRootCategory().getName());
            }
            catch (NullPointerException e) {
                throw new NullPointerException("No root category is defined.");
            }
        }
        this.rules.sort(Collections.reverseOrder());
    }

    public String getName() {
        return this.name;
    }

    public int[] getLockPriorities() {
        return this.lockPriorities;
    }

    public boolean[] getFrozenSlots() {
        return this.frozenSlots;
    }

    public List<InvTweaksConfigSortingRule> getRules() {
        return this.rules;
    }

    public List<String> getAutoReplaceRules() {
        return this.autoReplaceRules;
    }
}

