package controller.GameController.mapmenu;

import model.map.Tree;

import java.util.regex.Pattern;

public enum TreeType {
    DESERT_SHRUB("^desertShrub$", Tree.DESERT_SHRUB),
    CHERRY("^cherry$", Tree.CHERRY),
    OLIVE("^olive$", Tree.OLIVE),
    COCONUT("^coconut$", Tree.COCONUT),
    DATE("^date$", Tree.DATE);

    private String regex;

    private Tree tree;

    TreeType(String regex, Tree tree) {
        this.regex = regex;
        this.tree = tree;
    }

    public static boolean hasMatcher(String string, TreeType treeType) {
        if (Pattern.compile(treeType.regex).matcher(string).find())
            return true;
        return false;
    }

    public Tree getTree() {
        return tree;
    }
}
