package subway.domain;

public class SectionInfo {
    Line line;
    Station firstStation;
    Station secondStation;
    Integer km;
    Integer minutes;

    public SectionInfo(Line line, Station firstStation, Station secondStation, Integer km, Integer minutes) {
        this.line = line;
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        this.km = km;
        this.minutes = minutes;
    }

    public Line getLine() {
        return line;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public Station getSecondStation() {
        return secondStation;
    }

    public Integer getKm() {
        return km;
    }

    public Integer getMinutes() {
        return minutes;
    }
}
