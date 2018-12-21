package rule;

import java.util.List;

public interface Rule {
    List<String> apply(List<String> text);
}
