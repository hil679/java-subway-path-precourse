package subway;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        boolean isQuit = false;
        while(!isQuit) {
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

    

}
