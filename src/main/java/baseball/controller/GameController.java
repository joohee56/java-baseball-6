package baseball.controller;

import baseball.view.OutputView;

public class GameController {
    OutputView outputView = new OutputView();

    public void gameStart() {
        outputView.printStartGame();
    }
}
