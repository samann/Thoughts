package droidowl.thoughts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by droidowl on 3/1/16.
 */
public class Utils {
    public static final String THOUGHT_ERROR_EXTRAS = "extras_thought_errors";
    public static final String THOUGHT_RECORD_EXTRAS = "extras_thought_record";
    public static final String THOUGHT_RECORD_FIREBASE = "thoughts";
    public static final String THOUGHT_VALUE_FIREBASE = "value";
    public static final int MILLI_IN_HOUR = 36000000;
    public static final int MILLI_IN_MINUTE = 60000;
    public static final int MILLI_IN_DAY = 1000 * 60 * 60 * 24;

    public static List<ThoughtError> createErrorList() {
        List<ThoughtError> list = new ArrayList<>();
        list.add(new ThoughtError("All-or-Nothing", "Thinking of things in " +
                "absolute terms, like “always”, “every” or “never”", false));
        list.add(new ThoughtError("Overgeneralizing", "To take one " +
                "particular event and generalize it to the rest of our " +
                "life", false));
        list.add(new ThoughtError("Filtering Out the Positive","If nine good" +
                " things happen, and one bad thing, sometimes we filter out " +
                "the good and hone in on the bad", false));
        list.add(new ThoughtError("Mind-Reading", "We can never be sure what" +
                " someone else is thinking. Yet, everyone occasionally " +
                "assumes they know what's going on in someone else's mind",
                false));
        list.add(new ThoughtError("Catastrophizing", "Sometimes we think " +
                "things are much worse than they actually are", false));
        list.add(new ThoughtError("Emotional Reasoning", "Our emotions " +
                "aren't always based on reality but we often assume those " +
                "feelings are rational", false));
        list.add(new ThoughtError("Labeling", "Labeling involves putting a " +
                "name to something", false));
        list.add(new ThoughtError("Fortune-telling", "Although none of us " +
                "knows what will happen in the future, we sometimes like to " +
                "try our hand at fortune-telling", false));
        list.add(new ThoughtError("Personalization", "As much as we'd like " +
                "to say we don't think the world revolves around us, it's " +
                "easy to personalize everything", false));
        list.add(new ThoughtError("Unreal Ideal", "Making unfair comparisons" +
                " about ourselves and other people can ruin our motivation",
                false));
        return list;
    }

    protected static boolean checkTime(int hour, int min) {
        if (!(hour < 0 || hour > 24) && !(min < 0 || min >= 60)) {
            return  true;
        }
        return false;
    }
}
