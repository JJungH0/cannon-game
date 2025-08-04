package com.nhnacademy.cannongame.ballWorld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // TODO: JavaFX UI 구성 코드 작성
        /**
         * Canvas 생성 (800x600)
         * -> 그림을 그릴 수 있는 캔버스(=그림을 그릴 도화지 생성)
         */
        Canvas canvas = new Canvas(800, 600);
        /**
         * GraphicsContext 가져오기
         * -> 캔버스에 그림을 그릴 때 사용하는 도구(=그리기 도구)
         * -> 도형, 선, 텍스트, 이미지 등 다양한 요소 그리기 가능
         */
        GraphicsContext gc = canvas.getGraphicsContext2D();
        /**
         * Scene 만들기
         * -> StackPane은 여러 UI 요소를 겹쳐서 올릴 수 있는 컨테이너(= 콘텐츠 컨테이너)
         * -> StackPane에 캔버스를 올리고, 해당 StackPane을 이용해 Scene(장면)을 만듦
         * -> Scene은 화면 전체 구성을 담당하는 객체
         */
        StackPane stackPane = new StackPane(canvas);
        Scene scene = new Scene(stackPane, 800, 600);
        /**
         * Stage 설정하기
         * -> .setTitle (=제목 설정)
         * -> .setScene(장면)을 넣어서 어떤 UI를 보여줄것인지 결정
         * -> .show() 화면에 띄움
         */
        stage.setTitle("Connon Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
