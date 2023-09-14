package subway;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.*;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static LineRepository lineRepository;
    private static StationRepository stationRepository;
    private static SectionInfoRepository sectionInfoRepository;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        boolean isQuit = false;
        while(!isQuit) {
            initLine();
            initStation();

            System.out.println("## 메인 화면\n1." +
                    "1. 경로 조회\n " +
                    "Q. 종료\n");

            System.out.println("## 원하는 기능을 선택하세요.");
            String mainMenuChoice = scanner.next();

            if(checkQuit(mainMenuChoice)){
                break;
            }

            String creteria = chooseCreteria(scanner);
            if(checkBack(creteria)){
                continue;
            }

            String startStation = getStartStation(scanner);
            String endStation = getEndStation(scanner);

            
        }
    }

    public static boolean checkQuit(String quitOrNot){
        if(quitOrNot.equals("Q")){
            return true;
        }
        return false;
    }

    public static boolean checkBack(String backOrNot){
        if(backOrNot.equals("B")){
            return true;
        }
        return false;
    }

    public static String chooseCreteria(Scanner scanner){
        System.out.println("## 경로 기준\n" +
                "1. 최단 거리\n" +
                "2. 최소 시간\n" +
                "B. 돌아가기\n");

        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.next();
    }

    public static String getStartStation(Scanner scanner){
        System.out.println("## 출발역을 입력하세요.");
        return scanner.next();
    }

    public static String getEndStation(Scanner scanner){
        System.out.println("## 도착역을 입력하세요.");
        return scanner.next();
    }

    public static void initLine(){
        lineRepository.addLine(new Line("2호선"));
        lineRepository.addLine(new Line("3호선"));
        lineRepository.addLine(new Line("신분당선"));
    }

    public static void initStation(){
        stationRepository.addStation(new Station("교대역"));
        stationRepository.addStation(new Station("강남역"));
        stationRepository.addStation(new Station("역삼역"));
        stationRepository.addStation(new Station("남부터미널역"));
        stationRepository.addStation(new Station("양재역"));
        stationRepository.addStation(new Station("양재시민의숲역"));
        stationRepository.addStation(new Station("매봉역"));
    }

    public static void initSectionInfo(){
        List<Line> lines = lineRepository.lines();
        List<Station> stations = stationRepository.stations();
        //line no.2
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(0), stations.get(0), stations.get(1), 2, 3));
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(0), stations.get(1), stations.get(2), 2, 3));

        //line no.3
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(1), stations.get(0), stations.get(3), 3, 2));
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(1), stations.get(3), stations.get(4), 6, 5));
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(1), stations.get(4), stations.get(6), 1, 1));

        //sinbundang
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(2), stations.get(1), stations.get(4), 2, 8));
        sectionInfoRepository.addSectionInfo(
                new SectionInfo(lines.get(2), stations.get(4), stations.get(5), 10, 3));
    }



}
