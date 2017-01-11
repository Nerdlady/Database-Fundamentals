package softuni.transformer;

public class TagTransformer {
    public static String transform(String tag) {
        if (!tag.startsWith("#")) {
            tag = "#" + tag;
        }

        tag = tag.replace(" ", "");

        int length = tag.length();

        if (length > 20){
            length = 20;
        }

        tag = tag.substring(0,length);

        return tag;
    }
}
