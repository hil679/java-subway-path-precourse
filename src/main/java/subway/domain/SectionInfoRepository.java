package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectionInfoRepository {
    private static final List<SectionInfo> sectionInfos = new ArrayList<>();

    public static List<SectionInfo> sectionInfos() {
        return Collections.unmodifiableList(sectionInfos);
    }

    public static void addSectionInfo(SectionInfo sectionInfo) {
        sectionInfos.add(sectionInfo);
    }

}
