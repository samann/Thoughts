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
    public static List<ThoughtError> createErrorList() {
        List<ThoughtError> list = new ArrayList<>();
        list.add(new ThoughtError("All-or-Nothing", "Thinking of things in absolute terms, like “always”, “every” or “never”"));
        list.add(new ThoughtError("Overgeneralizing", "To take one " +
                "particular event and generalize it to the rest of our life"));
        list.add(new ThoughtError("Filtering Out the Positive","If nine good things happen, and one bad thing, sometimes we filter out the good and hone in on the bad"));
        list.add(new ThoughtError("Mind-Reading", "We can never be sure what someone else is thinking. Yet, everyone occasionally assumes they know what's going on in someone else's mind"));
        list.add(new ThoughtError("Catastrophizing", "Sometimes we think " +
                "things are much worse than they actually are"));
        list.add(new ThoughtError("Emotional Reasoning", "Our emotions " +
                "aren't always based on reality but we often assume those feelings are rational"));
        list.add(new ThoughtError("Labeling", "Labeling involves putting a " +
                "name to something"));
        list.add(new ThoughtError("Fortune-telling", "Although none of us " +
                "knows what will happen in the future, we sometimes like to try our hand at fortune-telling"));
        list.add(new ThoughtError("Personalization", "As much as we'd like " +
                "to say we don't think the world revolves around us, it's easy to personalize everything."));
        list.add(new ThoughtError("Unreal Ideal", "Making unfair comparisons" +
                " about ourselves and other people can ruin our motivation"));
        return list;
    }
}
