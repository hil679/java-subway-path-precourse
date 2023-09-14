package subway;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
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
            initSectionInfo();

            System.out.println("## 메인 화면\n" +
                    "1. 경로 조회\n" +
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

            searchResult(creteria, startStation, endStation);
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
        System.out.println("\n## 경로 기준\n" +
                "1. 최단 거리\n" +
                "2. 최소 시간\n" +
                "B. 돌아가기\n");

        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.next();
    }

    public static String getStartStation(Scanner scanner){
        System.out.println("\n## 출발역을 입력하세요.");
        return scanner.next();
    }

    public static String getEndStation(Scanner scanner){
        System.out.println("\n## 도착역을 입력하세요.");
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

    public static void searchResult(String creteria, String startStation, String endStation){
        callMethodAccordingToCreteria(creteria, startStation, endStation);

    }
    public static void callMethodAccordingToCreteria(String creteria, String startStation, String endStation){
        if(creteria.equals("1")){
            minDistance(startStation, endStation);
            return;
        }
        //minTime(startStation, endStation);
    }

    public static void setGraphVertex(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        for(Station station : stationRepository.stations()){
            graph.addVertex(station.getName());
        }
    }

    public static void setGraphEdgeWithKm(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        for(SectionInfo sectionInfo : sectionInfoRepository.sectionInfos()){
            graph.setEdgeWeight(
                    graph.addEdge(sectionInfo.getFirstStation().getName(),
                            sectionInfo.getSecondStation().getName()),
                    sectionInfo.getKm());
        }
    }

    public static void minDistance(String startStation, String endStation){
        WeightedMultigraph<String, DefaultWeightedEdge> graph
                = new WeightedMultigraph(DefaultWeightedEdge.class);
        setGraphVertex(graph);
        setGraphEdgeWithKm(graph);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);

        Integer bestKm = (int) dijkstraShortestPath.getPathWeight(startStation, endStation);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation, endStation).getVertexList();

        printResult(bestKm, 0, shortestPath);
    }

    public static void printResult(Integer km, Integer minute, List<String> shortestPath){
        System.out.println("\n## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + km + "km");
        System.out.println("[INFO] 총 소요 시간: " + minute + "분");
        System.out.println("[INFO] ---");

        shortestPath.stream().forEach(
                path -> System.out.println("[INFO] " + path));
        System.out.println();
    }




}
