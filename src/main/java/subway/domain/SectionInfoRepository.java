package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SectionInfoRepository {
    private static final List<SectionInfo> sectionInfos = new ArrayList<>();

    public static List<SectionInfo> sectionInfos() {
        return Collections.unmodifiableList(sectionInfos);
    }

    public static void addSectionInfo(SectionInfo sectionInfo) {
        sectionInfos.add(sectionInfo);
    }

    public static Optional<SectionInfo> findSectionInfoByFirstStationAndSecondStation(Station firstStation, Station secondStation){
        return sectionInfos.stream().filter(
                sectionInfo -> sectionInfo.firstStation.equals(firstStation)
                        && sectionInfo.secondStation.equals(secondStation))
                .findAny();

    }

}
